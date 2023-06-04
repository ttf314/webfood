package com.javapandeng.controller;

import com.javapandeng.Servlet.ItemCategoryDao;
import com.javapandeng.Servlet.MealsDao;
import com.javapandeng.base.BaseController;
import com.javapandeng.po.Manage;
import com.javapandeng.po.Meals;
import com.javapandeng.service.ItemCategoryService;
import com.javapandeng.service.ManageService;
import com.javapandeng.service.MealsService;
import com.javapandeng.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.Message;
import javax.servlet.http.HttpServletRequest;
import com.javapandeng.base.BaseController;
import com.javapandeng.po.ItemCategory;
import com.javapandeng.service.ItemCategoryService;
import com.javapandeng.utils.Pager;
import com.javapandeng.utils.SystemContext;
import com.javapandeng.utils.UUIDUtils;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javapandeng.controller.LoginforselController.m;

/**
 * 登录控制器
 * */
@Controller
@RequestMapping("meals")
public class MealsController extends BaseController {
    private String sql;
    private Pager<Meals> pagers;
    @Autowired
    private MealsService mealsService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 分页查询商品列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(Model model, Meals meals, HttpSession session){
        String uid=String.valueOf(m.get("id"));
        if(uid.equals("null")){
             sql= "select * from meals where isDelete = 0";
            if(!isEmpty(meals.getName())){
                sql += " and name like '%" + meals.getName() + "%' ";
            }
            sql += " order by gmNum desc";
            pagers = mealsService.findBySqlRerturnEntity(sql);
        }else {
            sql = "select * from meals where isDelete = 0 and category_id_one="+uid+"";
            if(!isEmpty(meals.getName())){
                sql += " and name like '%" + meals.getName() + "%' ";
            }
            sql += " order by gmNum desc";
            pagers= mealsService.findBySqlRerturnEntity(sql);
        }
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",meals);
        return "meals/meals";
    }

    /**
     * 分页查询商品列表2
     */
    @RequestMapping("/findBySql2")
    public String findBySql2(Model model, Meals meals, HttpSession session){
        String uid=String.valueOf(m.get("id"));
        if(uid.equals("null")){
            sql= "select * from meals where isDelete = 1";
            pagers = mealsService.findBySqlRerturnEntity(sql);

        }else {
            sql = "select * from meals where isDelete = 1 and category_id_one="+uid+"";
            if(!isEmpty(meals.getName())){
                sql += " and name like '%" + meals.getName() + "%' ";
            }
            sql += " order by gmNum desc";
            pagers= mealsService.findBySqlRerturnEntity(sql);
        }
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",meals);
        return "meals/meals2";
    }

    /**
     * 添加商品入口
     */
    @RequestMapping("/add")
    public String add(Model model){
        String uid=String.valueOf(m.get("id"));
        String sql = "select * from item_category where isDelete = 0 and pid ="+uid;

        /*String sql = "select * from item_category where isDelete = 0 and pid is not null order by id";*/
        List<ItemCategory> listBySqlReturnEntity = itemCategoryService.listBySqlReturnEntity(sql);
        model.addAttribute("types",listBySqlReturnEntity);
        return "meals/add";
    }

    /**
     * 执行添加商品
     */
    @RequestMapping("/exAdd")
    public String exAdd(Meals meals, @RequestParam("file")CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        mealsCommon(meals, files, request);
        meals.setGmNum(0);
        meals.setIsDelete(0);
        meals.setScNum(0);
        mealsService.insert(meals);
        return "redirect:/meals/findBySql.action";
    }

    /**
     * 新增和更新的公共方法
     */
    private void mealsCommon(Meals meals, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        if(files.length>0) {
            for (int s = 0; s < files.length; s++) {
                //String n = UUIDUtils.create();
                //获取上下文地址
                String path = SystemContext.getRealPath() + "\\resource\\ueditor\\upload\\" + meals.getId() + files[s].getOriginalFilename();
                File newFile = new File(path);
                //通过CommonsMultipartFile的方法直接写文件
                files[s].transferTo(newFile);
                if (s == 0) {
                    meals.setUrl1(request.getContextPath()+"\\resource\\ueditor\\upload\\" + meals.getId() + files[s].getOriginalFilename());
                }
            }
        }
        ItemCategory byId = itemCategoryService.getById(meals.getCategoryIdTwo());
        meals.setCategoryIdOne(byId.getPid());
    }

    /**
     * 修改商品入口
     */
    @RequestMapping("/update")
    public String update(Integer id,Model model){
        Meals obj = mealsService.load(id);
        String sql = "select * from item_category where isDelete = 0 and pid is not null order by id";
        List<ItemCategory> listBySqlReturnEntity = itemCategoryService.listBySqlReturnEntity(sql);
        model.addAttribute("types",listBySqlReturnEntity);
        model.addAttribute("obj",obj);
        return "meals/update";
    }

    /**
     * 执行修改商品
     */
    @RequestMapping("/exUpdate")
    public String exUpdate(Meals meals, @RequestParam("file")CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        mealsCommon(meals, files, request);
        mealsService.updateById(meals);
        return "redirect:/meals/findBySql.action";
    }

    /**
     * 商品下架
     */
    @RequestMapping("/delete")
    public String update(Integer id){
        Meals obj = mealsService.load(id);
        obj.setIsDelete(1);
        mealsService.updateById(obj);
        return "redirect:/meals/findBySql.action";
    }
    /**
     * 商品轮播图
     */
    @RequestMapping("/saveType")
    public String saveType(Integer id,Integer type){
        Meals obj = mealsService.load(id);
        obj.setType(type);
        mealsService.updateById(obj);
        return "redirect:/meals/meals3";

    }
    /**
     * 商品轮播图2
     */
    @RequestMapping("/saveType2")
    public String saveType2(Integer id,Integer type){
        Meals obj = mealsService.load(id);
        obj.setType(0);
        mealsService.updateById(obj);
        return "meals/meals3";

    }
    /**
     * 商品下架
     */
    @RequestMapping("/delete3")
    public String delete3(){
        MealsDao mealsDao=new MealsDao();//操作
        mealsDao.upall();   // 获取搜索栏的食物数据
        return "meals/meals3";
    }

    /**
     * 商品下架
     */
    @RequestMapping("/up2")
    public String update2(Integer id){
        Meals obj = mealsService.load(id);
        obj.setIsDelete(0);
        mealsService.updateById(obj);
        return "redirect:/meals/findBySql.action";
    }

    /**
     * 分页查询商品列表
     */
    @RequestMapping("/findBySql3")
    public String findBySql3(Model model, Meals meals, HttpSession session,Integer action){
        if(action==1){
            sql= "select * from meals where isDelete = 0";
            if(!isEmpty(meals.getName())){
                sql += " and name like '%" + meals.getName() + "%' ";
            }
            sql += " order by type desc";
            pagers = mealsService.findBySqlRerturnEntity(sql);
        } else {
            sql= "SELECT * FROM meals WHERE isDelete = 0 AND type IN (1, 2, 3)";
            if(!isEmpty(meals.getName())){
                sql += " and name like '%" + meals.getName() + "%' ";
            }
            sql += " order by type desc";

            pagers = mealsService.findBySqlRerturnEntity(sql);
        }
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",meals);
        return "meals/meals3";
    }

}
