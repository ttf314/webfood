package com.javapandeng.controller;

import com.javapandeng.Servlet.ItemCategoryDao;
import com.javapandeng.base.BaseController;
import com.javapandeng.po.*;
import com.javapandeng.service.ItemCategoryService;
import com.javapandeng.service.OrderService;
import com.javapandeng.service.UserInfoService;
import com.javapandeng.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static com.javapandeng.controller.LoginforselController.m;

/**
 * 登录控制器
 * */
@Controller
@RequestMapping("userinfo")
public class UserInfoController extends BaseController {
    private String sql;
    @Autowired
    private UserInfoService userInfoService;
    /**
     * 分页查询商品列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(Model model, UserInfo userInfo){
        sql= "select * from userinfo where isDelete = 0";
        if(!isEmpty(userInfo.getUserName())){
            sql += " and userName like '%" + userInfo.getUserName() + "%' ";
        }
        sql += " order by rmb desc";
        Pager<UserInfo> pagers = userInfoService.findBySqlRerturnEntity(sql);
        //model.addAttribute("pagers0",pagers0);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",userInfo);
        return "userinfo/userinfo";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(int id){
        UserInfo obj = userInfoService.load(id);
        obj.setIsDelete(1);
        userInfoService.updateById(obj);
        return "redirect:/userinfo/findBySql.action";
    }
    /**
     * 充值
     */
    @RequestMapping("/update")
    public String update(int id){
        UserInfo obj = userInfoService.load(id);
        double now = obj.getRmb();

        obj.setIsDelete(0);
        userInfoService.updateById(obj);
        return "redirect:/userinfo/findBySql.action";
    }
    /**
     *
     */
    @RequestMapping("/update2")
    public String update2(Integer id,int rmb){
        UserInfo obj = userInfoService.load(id);
        int now =obj.getRmb();
        obj.setRmb(rmb+now);
        userInfoService.updateById(obj);
        return "redirect:/userinfo/findBySql";

    }


}
