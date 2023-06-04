package com.javapandeng.controller;

import com.javapandeng.base.BaseController;
import com.javapandeng.po.Manage;
import com.javapandeng.service.ManageService;
import com.javapandeng.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.javapandeng.controller.LoginforselController.m;

/**
 * 登录控制器
 * */
@Controller
@RequestMapping("login")
public class LoginController extends BaseController {
    @Autowired
    ManageService manageService;
    /**
     * 管理员登录前
     * @return
     */
    @RequestMapping("login")
    public String login(){
        return "/login/mLogin";
    }

    /**
     * 登录验证
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(Manage manage, HttpServletRequest request){
        String ad="admin";
        m.clear();
        Manage byEntity = manageService.getByEntity(manage);
        if(byEntity==null){
            return "redirect:/login/mtuichu";
        }
        request.getSession().setAttribute(Consts.MANAGE,byEntity);
        return "/login/mIndex";
    }

    /**
     * 管理员退出
     */
    @RequestMapping("mtuichu")
    public String mtuichu(HttpServletRequest request){
        request.getSession().setAttribute(Consts.MANAGE,null);
        return "/login/mLogin";
    }
}
