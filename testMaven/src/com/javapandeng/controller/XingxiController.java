package com.javapandeng.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.javapandeng.Servlet.InfoDao;
import com.javapandeng.Servlet.MealsDao;
import com.javapandeng.base.BaseController;
import com.javapandeng.po.Evaluates;
import com.javapandeng.po.Info;
import com.javapandeng.po.Meals;
import com.javapandeng.service.EvaluatesService;
import com.javapandeng.service.ItemCategoryService;
import com.javapandeng.utils.Pager;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javapandeng.controller.LoginforselController.m;

/**
 * 雇员控制器
 * */
@Controller
@RequestMapping("xingxi")
public class XingxiController extends BaseController {
    private String sql;
    private String uid;
    private Pager<Evaluates> pagers;
    @Autowired
    private EvaluatesService evaluatesService;
    @Autowired
    private ItemCategoryService itemCategoryService;


    @RequestMapping("/findBySql")
    public String findBySql(Model model, Evaluates evaluates, HttpSession session){

        return "xingxi/xingxi";
    }


  /*  @RequestMapping("/upinfo")
    public String upinfo(Integer id, String xingxi){

        InfoDao infoDao=new InfoDao();//操作
        infoDao.addinfo(xingxi);
        return "redirect:/xingxi/findBySql.action";
    }*/
  @RequestMapping("/upinfo")
  public String upinfo(@RequestParam("xingxi") String xingxi) {
      InfoDao infoDao = new InfoDao();
      infoDao.addinfo(xingxi);
      return "redirect:/xingxi/findBySql.action";
  }

    @RequestMapping("/findBySql2")
    public String findBySql2(Model model, Evaluates evaluates, HttpSession session,int id) {

        model.addAttribute("id",id);
        return "xingxi/erweima";
    }

}
