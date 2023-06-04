package com.javapandeng.controller;

import com.javapandeng.Servlet.InfoDao;
import com.javapandeng.Servlet.MealsDao;
import com.javapandeng.base.BaseController;
import com.javapandeng.po.ItemCategory;
import com.javapandeng.po.Manage;
import com.javapandeng.service.ItemCategoryService;
import com.javapandeng.service.ManageService;
import com.javapandeng.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 * */
@Controller
@RequestMapping("login2")
public class LoginforselController extends BaseController {

    public static Map m = new HashMap();
    private String xingxi="山川是不卷收的文章，日月为你掌灯伴读。";
    @Autowired
    ItemCategoryService itemCategoryService;
    /**
     * 商家登录前
     * @return
     */
    @RequestMapping("login2")
    public String login(){
        return "/login2/mLogin";
    }

    /**
     * 登录验证
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(ItemCategory itemCategory, HttpServletRequest request, Model model){
        ItemCategory byEntity = itemCategoryService.getByEntity(itemCategory);


        if(byEntity==null){
            return "redirect:/login2/mtuichu";
        }
        //request.getSession().setAttribute("uid", itemCategory.getId());
        InfoDao infoDao=new InfoDao();//操作
        xingxi=infoDao.getinfo();   // 获取搜索栏的食物数据

        request.getSession().setAttribute(Consts.MANAGE,byEntity);
        model.addAttribute("id",byEntity.getId());
        System.out.println(xingxi+"zhi");
        model.addAttribute("xingxi",xingxi+"");
        m.put("id", byEntity.getId());
        return "/login2/mIndex";
    }

    /**
     * 管理员退出
     */
    @RequestMapping("mtuichu")
    public String mtuichu(HttpServletRequest request){
        request.getSession().setAttribute(Consts.MANAGE,null);
        return "/login2/mLogin";
    }

}
