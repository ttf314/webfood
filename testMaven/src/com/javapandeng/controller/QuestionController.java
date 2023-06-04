package com.javapandeng.controller;

import com.javapandeng.base.BaseController;
import com.javapandeng.po.Meals;
import com.javapandeng.po.Order;
import com.javapandeng.po.Question;
import com.javapandeng.po.UserInfo;
import com.javapandeng.service.QuestionService;
import com.javapandeng.service.UserInfoService;
import com.javapandeng.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 登录控制器
 * */
@Controller
@RequestMapping("question")
public class QuestionController extends BaseController {
    private String sql;
    @Autowired
    private QuestionService questionService;
    /**
     * 分页查询商品列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(Model model, Order order){
        sql= "select * from question where isDelete=0";
        Pager<Question> pagers = questionService.findBySqlRerturnEntity(sql);
        //model.addAttribute("pagers0",pagers0);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",order);
        return "question/question";
    }
    /**
     * 分页查询商品列表
     */
        @RequestMapping("/findBySql2")
        public String findBySql2(Model model, Order order,Integer id){
        Question obj = questionService.load(id);
        sql= "select * from question where isDelete=0 and id ="+id;
        Pager<Question> pagers = questionService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",order);
        return "question/question2";
    }
    /**
     * 删除大的
     */
    @RequestMapping("/up")
    public String up(Integer id){
        Question obj = questionService.load(id);
        obj.setIsDelete(1);
        questionService.updateById(obj);
        return "redirect:/question/findBySql.action";
    }
    /**
     * 删除小的
     */
    @RequestMapping("/delet")
    public String delet(String answerContent,String answerTime,Integer id){
        /*System.out.println(answerContent+"");
        System.out.println(answerTime+"");
        System.out.println(id+"");*/
        Question obj = questionService.load(id);
        String str = obj.getAnswerContent();
        String str2 = obj.getAnswerTime();
        String[] arr = str.split("#"); // 将字符串分割成一个字符串数组
        String[] arr2 = str2.split("#"); // 将字符串分割成一个字符串数组
        List<String> list = new ArrayList<>(Arrays.asList(arr)); // 将字符串数组转换成一个动态数组
        List<String> list2 = new ArrayList<>(Arrays.asList(arr2)); // 将字符串数组转换成一个动态数组
        list.remove(answerContent); // 删除需要删除的元素
        list2.remove(answerTime); // 删除需要删除的元素
        String result = String.join("#", list); // 将动态数组中的元素拼接成一个新的字符串
        String result2 = String.join("#", list2); // 将动态数组中的元素拼接成一个新的字符串
        System.out.println(result); // 输出结果：test1huida#test3#test4
        System.out.println(result2); // 输出结果：test1huida#test3#test4

        obj.setAnswerTime(result2);
        obj.setAnswerContent(result);
        questionService.updateById(obj);
        return "redirect:/question/findBySql.action";



    }
}
