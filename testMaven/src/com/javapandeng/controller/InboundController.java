package com.javapandeng.controller;

import com.javapandeng.Servlet.MealsDao;
import com.javapandeng.base.BaseController;
import com.javapandeng.po.*;
import com.javapandeng.service.*;
import com.javapandeng.utils.Pager;
import com.javapandeng.utils.SystemContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.javapandeng.controller.LoginforselController.m;

/**
 * 雇员控制器
 * */
@Controller
@RequestMapping("inbound")
public class InboundController extends BaseController {
    private String sql, sql2;
    private Pager<Inbound> pagers;
    private Pager<Meals> pagers2;
    @Autowired
    private InboundService inboundService;
    @Autowired
    private Item_warehouseService item_warehouseService;
    @Autowired
    private MealsService mealsService;
    @Autowired
    private EmployeeinfoService employeeinfoService;
    private int id;

    @RequestMapping("/findBySql")
    public String findBySql(Model model, Inbound inbound, HttpSession session, Meals meals) throws ParseException {
        String uid = String.valueOf(m.get("id"));
        Object value = m.get("id");
        int pid = (int) m.get("id");
        sql = "SELECT * FROM inbound WHERE isDelete = 0 AND did = " + pid;

        sql2 = "SELECT * FROM meals WHERE isDelete = 0 AND category_id_one = " + pid;
        if (!isEmpty(inbound.getGoods_name())) {
            sql += " AND goods_name LIKE '%" + inbound.getGoods_name() + "%' ";
            sql2 += " AND name LIKE '%" + inbound.getGoods_name() + "%' ";
        }
        sql += " ORDER BY inbound_date DESC";

        pagers = inboundService.findBySqlRerturnEntity(sql);
        pagers2 = mealsService.findBySqlRerturnEntity(sql2);

        // 遍历入库信息，计算当前数量预估
        for (Inbound data : pagers.getDatas()) {
            String goodsName = data.getGoods_name();
            int remaining = 0;
            boolean found = false;
            // 在餐品信息中查找该商品
            for (Meals meal : pagers2.getDatas()) {
                System.out.println(meal.getName());
                System.out.println(goodsName);
                if (meal.getName().equals(goodsName)) {
                    found = true;
                    // 计算剩余库存
                    remaining = data.getQuantity() - meal.getGmNum();
                    break;
                }
            }
            if (!found) {
                // 计算剩余库存
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date inboundDate = sdf.parse(data.getInbound_date());
                long diffInMilliseconds = new Date().getTime() - inboundDate.getTime();
                long diffInDays = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
                remaining = data.getQuantity() - ((int) (data.getQuantity() * diffInDays * 0.1));

               /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date inboundDate = sdf.parse(data.getInbound_date());
                remaining = Integer.valueOf((int) (data.getQuantity() * (new Date().getTime() - inboundDate.getTime()) * 0.6));
*/
            }
            data.setRemaining(remaining);
        }

        // 库存信息pagers
        model.addAttribute("pagers", pagers);
        // 餐品信息pagers2
        model.addAttribute("pagers2", pagers2);
        model.addAttribute("obj", inbound);
        return "inbound/inbound";
    }

   /* private String sql,sql2;
    private Pager<Inbound> pagers;
    private Pager<Meals> pagers2;
    @Autowired
    private InboundService inboundService;
    @Autowired
    private Item_warehouseService item_warehouseService;
    @Autowired
    private MealsService mealsService;
    @Autowired
    private EmployeeinfoService employeeinfoService;
    private int id;
    @RequestMapping("/findBySql")
    public String findBySql(Model model, Inbound inbound, HttpSession session,Meals meals){
        String uid=String.valueOf(m.get("id"));
        Object value = m.get("id");
        int pid = (int) m.get("id");
            sql = "select * from inbound where isDelete=0 and did="+pid;

            sql2 = "select * from meals where isDelete=0 and category_id_one="+pid;
            if(!isEmpty(inbound.getGoods_name())){
                sql += " and goods_name like '%" + inbound.getGoods_name() + "%' ";
                sql2 += " and name like '%" + inbound.getGoods_name() + "%' ";
            sql += " order by inbound_date desc";

            pagers= inboundService.findBySqlRerturnEntity(sql);
        }
        pagers= inboundService.findBySqlRerturnEntity(sql);
        pagers2= mealsService.findBySqlRerturnEntity(sql2);

        //库存信息pagers
        model.addAttribute("pagers",pagers);
        //餐品信息pagers2
        model.addAttribute("pagers2",pagers2);
        model.addAttribute("obj",inbound);
        return "inbound/inbound";
    }*/
    /**
     * 添加商品入口
     */
    @RequestMapping("/add")
    public String add(Model model){
        Object value = m.get("id");
        int pid = (int) m.get("id");
        String sql = "select * from meals where isDelete = 0 and category_id_one="+pid+" order by id";
        List<Meals> listBySqlReturnEntity = mealsService.listBySqlReturnEntity(sql);

        String sql2 = "select * from employeeinfo where isDelete = 0 and itemid="+pid+" order by id";
        List<Employeeinfo> listBySqlReturnEntity2 = employeeinfoService.listBySqlReturnEntity(sql2);

        String sql3 = "select * from item_warehouse where isDelete = 0 and pid="+pid+" order by id";
        List<Item_warehouse> listBySqlReturnEntity3 = item_warehouseService.listBySqlReturnEntity(sql3);

        id=listBySqlReturnEntity.get(0).getId();
        model.addAttribute("types",listBySqlReturnEntity);
        model.addAttribute("types2",listBySqlReturnEntity2);
        model.addAttribute("types3",listBySqlReturnEntity3);
        return "inbound/add";
    }

    /**
     * 执行添加商品
     */
    @RequestMapping("/exAdd")
    public String exAdd(Inbound inbound, HttpServletRequest request) throws IOException {
        //mealsCommon(inbound, files, request);

        // 创建SimpleDateFormat对象，指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 创建Date对象，表示当前时间
        Date now = new Date();
        // 调用format方法将当前时间按照指定格式转换为字符串
        String currentTime = sdf.format(now);
        // 输出当前时间
        System.out.println("当前时间：" + currentTime);
        String str = inbound.getGoods_name();
        str = str.replace(",", "");

        inbound.setGoods_name(str);
        inbound.setInbound_date(currentTime);
        inbound.setDid((int) m.get("id"));
        inbound.setIsDelete(0);
        inboundService.insert(inbound);
        return "redirect:/inbound/findBySql.action";
    }

    /**
     * 新增和更新的公共方法
     */
    private void mealsCommon(Inbound inbound, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
       /* if(files.length>0) {
            for (int s = 0; s < files.length; s++) {
                //String n = UUIDUtils.create();
                //获取上下文地址
                String path = SystemContext.getRealPath() + "\\resource\\ueditor\\upload\\" + meals.getId() + files[s].getOriginalFilename();
                File newFile = new File(path);
                //通过CommonsMultipartFile的方法直接写文件
                files[s].transferTo(newFile);
                if (s == 0) {
                    inbound.setUrl1(request.getContextPath()+"\\resource\\ueditor\\upload\\" + meals.getId() + files[s].getOriginalFilename());
                }
            }
        }
        ItemCategory byId = inboundService.getById(inbound.getCategoryIdTwo());
        inbound.setCategoryIdOne(byId.getPid());*/
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(int id){
        Inbound obj = inboundService.load(id);
        obj.setIsDelete(1);
        inboundService.updateById(obj);
        return "redirect:/inbound/findBySql.action";
    }

    /**
     * 修改商品入口
     */
    @RequestMapping("/update")
    public String update(Integer id,Model model){
        Inbound obj = inboundService.load(id);
        String sql = "select * from inbound where isDelete = 0 order by id";
        model.addAttribute("obj",obj);
        return "inbound/update";
    }

    /**
     * 执行修改商品
     */
    @RequestMapping("/exUpdate")
    public String exUpdate(Inbound inbound, @RequestParam("file")CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        //employeeinfoCommon(inbound, files, request);
        inboundService.updateById(inbound);
        return "redirect:/inbound/findBySql.action";
    }
    /**
     * 新增和更新的公共方法
     */
    private void employeeinfoCommon(Inbound inbound, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        if(files.length>0) {
            for (int s = 0; s < files.length; s++) {
                //String n = UUIDUtils.create();
                //获取上下文地址
                String path = SystemContext.getRealPath() + "\\resource\\ueditor\\upload\\" + inbound.getId() + files[s].getOriginalFilename();
                File newFile = new File(path);
                //通过CommonsMultipartFile的方法直接写文件
                files[s].transferTo(newFile);
                if (s == 0) {
                    inbound.setInbound_date(request.getContextPath()+"\\resource\\ueditor\\upload\\" + inbound.getId() + files[s].getOriginalFilename());
                }
                if (s == 1) {
                    inbound.setInbound_date(request.getContextPath()+"\\resource\\ueditor\\upload\\" + inbound.getId() + files[s].getOriginalFilename());
                }
            }
        }
        //ItemCategory byId = itemCategoryService.getById(employeeinfo.getCategoryIdTwo());
        //employeeinfo.setItemid(19);
    }
}
