package com.javapandeng.controller;

import com.javapandeng.base.BaseController;
import com.javapandeng.po.*;
import com.javapandeng.service.EmployeeinfoService;
import com.javapandeng.service.ItemCategoryService;
import com.javapandeng.service.MealsService;
import com.javapandeng.service.SalaryandattendinfoService;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.javapandeng.controller.LoginforselController.m;

/**
 * 店员控制器
 * */
@Controller
@RequestMapping("employeeinfo")
public class EmployeeinfoController extends BaseController {
    private String sql;
    private String uid;
    private int distinctDatesCount;
    private Pager<Employeeinfo> pagers;
    @Autowired
    private EmployeeinfoService employeeinfoService;
    @Autowired
    private ItemCategoryService itemCategoryService;
    @Autowired
    private SalaryandattendinfoService salaryandattendinfoService;
    /**
     * 分页查询店员列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(Model model, Employeeinfo employeeinfo, HttpSession session){
        uid=String.valueOf(m.get("id"));
        if(uid.equals("null")){
            sql= "select * from employeeinfo where isDelete = 0";
            if(!isEmpty(employeeinfo.getEmployeename())){
                sql += " and employeename like '%" + employeeinfo.getEmployeename() + "%' ";
            }
            pagers = employeeinfoService.findBySqlRerturnEntity(sql);
            model.addAttribute("pagers",pagers);
            model.addAttribute("obj",employeeinfo);
            return "employeeinfo/employeeinfo";
        }else {
            sql = "select * from employeeinfo where isDelete = 0 and itemid="+uid+"";
            if(!isEmpty(employeeinfo.getEmployeename())){
                sql += " and employeename like '%" + employeeinfo.getEmployeename() + "%' ";
            }
            sql += " order by id desc";
            pagers= employeeinfoService.findBySqlRerturnEntity(sql);
            model.addAttribute("pagers",pagers);
            model.addAttribute("obj",employeeinfo);
            return "employeeinfo/employeeinfo";
        }

    }

    /**
     * 分页查询店员列表
     */
    @RequestMapping("/findBySql2")
    public String findBySql2(Model model, Employeeinfo employeeinfo, HttpSession session){
        uid=String.valueOf(m.get("id"));
        if(uid.equals("null")){
            sql= "select * from employeeinfo where isDelete = 0";
            if(!isEmpty(employeeinfo.getEmployeename())){
                sql += " and employeename like '%" + employeeinfo.getEmployeename() + "%' ";
            }
            pagers = employeeinfoService.findBySqlRerturnEntity(sql);
        }else {
            sql = "select * from employeeinfo where isDelete = 0 and itemid="+uid+"";
            if(!isEmpty(employeeinfo.getEmployeename())){
                sql += " and employeename like '%" + employeeinfo.getEmployeename() + "%' ";
            }
            sql += " order by id desc";
            pagers= employeeinfoService.findBySqlRerturnEntity(sql);
        }

        // 遍历查询结果，计算并设置每个记录的rmb3值
        for(Employeeinfo employeeinfo1 : pagers.getDatas()) {
            int rmb2 = employeeinfo1.getRmb2();
            int rmb = employeeinfo1.getRmb();
            String data2 = employeeinfo1.getData2();
            String data2Str = data2;
            Set<String> dateSet = new HashSet<>();
            for (String dateStr : data2Str.split("#")) {
                String date = dateStr.split(" ")[0];
                dateSet.add(date);
            }
            distinctDatesCount = dateSet.size();
            System.out.println("distinct dates count: " + distinctDatesCount);

            // 计算rmb3并设置到当前记录
            int rmb3 = distinctDatesCount * rmb + rmb2;
            employeeinfo1.setRmb3(rmb3);
        }

        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",employeeinfo);
        model.addAttribute("distinctDatesCount",distinctDatesCount);
        return "employeeinfo/employeeinfo2";
    }

    /*@RequestMapping("/findBySql2")
    public String findBySql2(Model model, Employeeinfo employeeinfo, HttpSession session){
        uid=String.valueOf(m.get("id"));
        if(uid.equals("null")){
            sql= "select * from employeeinfo where isDelete = 0";
            if(!isEmpty(employeeinfo.getEmployeename())){
                sql += " and employeename like '%" + employeeinfo.getEmployeename() + "%' ";
            }
            pagers = employeeinfoService.findBySqlRerturnEntity(sql);
            model.addAttribute("pagers",pagers);
            model.addAttribute("obj",employeeinfo);
            return "employeeinfo/employeeinfo2";
        }else {
            sql = "select * from employeeinfo where isDelete = 0 and itemid="+uid+"";
            if(!isEmpty(employeeinfo.getEmployeename())){
                sql += " and employeename like '%" + employeeinfo.getEmployeename() + "%' ";
            }
            sql += " order by id desc";
            pagers= employeeinfoService.findBySqlRerturnEntity(sql);

            model.addAttribute("pagers",pagers);
            model.addAttribute("obj",employeeinfo);

            return "employeeinfo/employeeinfo2";
        }

    }*/

    /**
     * 分页查询店员列表
     */
    @RequestMapping("/findBySql3")
    public String findBySql3(Model model, Employeeinfo employeeinfo, HttpSession session){
        uid=String.valueOf(m.get("id"));
        if(uid.equals("null")){
            sql= "select * from employeeinfo where isDelete = 0";
            if(!isEmpty(employeeinfo.getEmployeename())){
                sql += " and employeename like '%" + employeeinfo.getEmployeename() + "%' ";
            }
            pagers = employeeinfoService.findBySqlRerturnEntity(sql);
            model.addAttribute("pagers",pagers);
            model.addAttribute("obj",employeeinfo);
            return "employeeinfo/employeeinfo2";
        }else {
            sql = "select * from employeeinfo where isDelete = 0 and itemid="+uid+"";
            if(!isEmpty(employeeinfo.getEmployeename())){
                sql += " and employeename like '%" + employeeinfo.getEmployeename() + "%' ";
            }
            sql += " order by id desc";
            pagers= employeeinfoService.findBySqlRerturnEntity(sql);

            model.addAttribute("pagers",pagers);
            model.addAttribute("obj",employeeinfo);

            return "employeeinfo/employeeinfo3";
        }

    }

    /**
     * 添加店员入口
     */
    @RequestMapping("/add")
    public String add(Model model){
        String sql = "select * from employeeinfo where isDelete = 0";
        List<ItemCategory> listBySqlReturnEntity = itemCategoryService.listBySqlReturnEntity(sql);
        model.addAttribute("types",listBySqlReturnEntity);
        return "employeeinfo/add";
    }

    /**
     * 执行添加店员
     */
    @RequestMapping("/exAdd")
    public String exAdd(Employeeinfo employeeinfo, @RequestParam("file")CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        employeeinfoCommon(employeeinfo, files, request);
        employeeinfo.setIsDelete(0);
        employeeinfo.setItemid(Integer.valueOf(uid));
        // 创建SimpleDateFormat对象，指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 创建Date对象，表示当前时间
        Date now = new Date();
        // 调用format方法将当前时间按照指定格式转换为字符串
        String currentTime = sdf.format(now);
        // 输出当前时间
        System.out.println("当前时间：" + currentTime);
        employeeinfo.setData1(currentTime);
        employeeinfo.setData2(currentTime);
        employeeinfo.setRmb2(0);
        employeeinfo.setRmb3(0);
        //employeeinfo.setItemid(Integer.valueOf(uid));
        employeeinfoService.insert(employeeinfo);
        return "redirect:/employeeinfo/findBySql.action";
    }
    /**
     * 新增和更新的公共方法
     */
    private void employeeinfoCommon(Employeeinfo employeeinfo, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        if(files.length>0) {
            for (int s = 0; s < files.length; s++) {
                //String n = UUIDUtils.create();
                //获取上下文地址
                String path = SystemContext.getRealPath() + "\\resource\\ueditor\\upload\\" + employeeinfo.getId() + files[s].getOriginalFilename();
                File newFile = new File(path);
                //通过CommonsMultipartFile的方法直接写文件
                files[s].transferTo(newFile);
                if (s == 0) {
                    employeeinfo.setFaceurl(request.getContextPath()+"\\resource\\ueditor\\upload\\" + employeeinfo.getId() + files[s].getOriginalFilename());
                }
                if (s == 1) {
                    employeeinfo.setHealthcertificateurl(request.getContextPath()+"\\resource\\ueditor\\upload\\" + employeeinfo.getId() + files[s].getOriginalFilename());
                }
            }
        }
        //ItemCategory byId = itemCategoryService.getById(employeeinfo.getCategoryIdTwo());
        //employeeinfo.setItemid(19);
    }
    /**
     * 修改商品入口
     */
    @RequestMapping("/update")
    public String update(Integer id,Model model){
        Employeeinfo obj = employeeinfoService.load(id);
        String sql = "select * from employeeinfo where isDelete = 0 order by id";
        List<ItemCategory> listBySqlReturnEntity = itemCategoryService.listBySqlReturnEntity(sql);
        //model.addAttribute("types",listBySqlReturnEntity);
        model.addAttribute("obj",obj);
        return "employeeinfo/update";
    }

    /**
     * 执行修改商品
     */
    @RequestMapping("/exUpdate")
    public String exUpdate(Employeeinfo employeeinfo, @RequestParam("file")CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        employeeinfoCommon(employeeinfo, files, request);
        employeeinfoService.updateById(employeeinfo);
        return "redirect:/employeeinfo/findBySql.action";
    }


    /**
     * 修改商品入口
     */
    @RequestMapping("/update4")
    public String update4(Integer id,Model model){
        Employeeinfo obj = employeeinfoService.load(id);
        String sql = "select * from employeeinfo where isDelete = 0 order by id";
        List<ItemCategory> listBySqlReturnEntity = itemCategoryService.listBySqlReturnEntity(sql);
        //model.addAttribute("types",listBySqlReturnEntity);
        model.addAttribute("obj",obj);
        return "employeeinfo/update2";
    }

    /**
     * 执行修改商品
     */
    @RequestMapping("/exUpdate2")
    public String exUpdate2(Employeeinfo employeeinfo, @RequestParam("file")CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        employeeinfoCommon(employeeinfo, files, request);
        employeeinfoService.updateById(employeeinfo);
        return "redirect:/employeeinfo/findBySql.action";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(int id){
        Employeeinfo obj = employeeinfoService.load(id);
        obj.setIsDelete(1);
        employeeinfoService.updateById(obj);
        return "redirect:/employeeinfo/findBySql.action";
    }

    /**
     * 删除
     */
    @RequestMapping("/update2")
    public String update2(int id){
        Employeeinfo obj = employeeinfoService.load(id);
        // 用当前时间创建一个 LocalDateTime 对象
        LocalDateTime now = LocalDateTime.now();
        // 将 LocalDateTime 格式化为指定格式的字符串
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = formatter.format(now);
        // 将 # 和当前时间连接在一起
        String result = "#" + formattedNow;
        obj.setData2(obj.getData2()+result);
        employeeinfoService.updateById(obj);
        return "redirect:/employeeinfo/findBySql.action";
    }

    /**
     *
     */
    @RequestMapping("/update3")
    public String update3(Integer id,int rmb2){
        Employeeinfo obj = employeeinfoService.load(id);
        int now =obj.getRmb2();
        obj.setRmb2(rmb2+now);
        employeeinfoService.updateById(obj);
        return "redirect:/employeeinfo/findBySql";

    }
}
