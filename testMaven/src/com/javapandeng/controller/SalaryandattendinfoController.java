package com.javapandeng.controller;

import com.javapandeng.base.BaseController;
import com.javapandeng.po.Employeeinfo;
import com.javapandeng.po.ItemCategory;
import com.javapandeng.po.Salaryandattendinfo;
import com.javapandeng.service.EmployeeinfoService;
import com.javapandeng.service.ItemCategoryService;
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
import java.util.Date;
import java.util.List;

import static com.javapandeng.controller.LoginforselController.m;

/**
 * 店员控制器
 * */
@Controller
@RequestMapping("salaryandattendinfo")
public class SalaryandattendinfoController extends BaseController {
    private String sql;
    private String uid;
    private Pager<Employeeinfo> pagers;
    private Pager<Salaryandattendinfo> pagers2;
    @Autowired
    private EmployeeinfoService employeeinfoService;

    @Autowired
    private SalaryandattendinfoService salaryandattendinfoService;

    @Autowired
    private ItemCategoryService itemCategoryService;
    /**
     * 分页查询店员列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(Model model, Employeeinfo employeeinfo, HttpSession session){
         uid=String.valueOf(m.get("id"));
        Employeeinfo obj = employeeinfoService.load(uid);
        String sql = "select * from employeeinfo where isDelete = 0 and itemid="+uid+"";

        List<ItemCategory> listBySqlReturnEntity = itemCategoryService.listBySqlReturnEntity(sql);
        //model.addAttribute("types",listBySqlReturnEntity);
        model.addAttribute("obj",obj);

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
            return "salaryandattendinfo/salaryandattendinfo";
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
        return "salaryandattendinfo/add";
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
        //employeeinfo.setItemid(Integer.valueOf(uid));
        employeeinfoService.insert(employeeinfo);
        return "redirect:/salaryandattendinfo/findBySql.action";
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
        return "salaryandattendinfo/update";
    }

    /**
     * 执行修改商品
     */
    @RequestMapping("/exUpdate")
    public String exUpdate(Employeeinfo employeeinfo, @RequestParam("file")CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        employeeinfoCommon(employeeinfo, files, request);
        employeeinfoService.updateById(employeeinfo);
        return "redirect:/salaryandattendinfo/findBySql.action";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(int id){
        Employeeinfo obj = employeeinfoService.load(id);
        obj.setIsDelete(1);
        employeeinfoService.updateById(obj);
        return "redirect:/salaryandattendinfo/findBySql.action";
    }
}
