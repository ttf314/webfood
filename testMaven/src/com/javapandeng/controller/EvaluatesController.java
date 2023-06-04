package com.javapandeng.controller;

import com.javapandeng.Servlet.ItemCategoryDao;
import com.javapandeng.Servlet.MealsDao;
import com.javapandeng.base.BaseController;
import com.javapandeng.po.Evaluates;
import com.javapandeng.po.Inbound;
import com.javapandeng.po.ItemCategory;
import com.javapandeng.po.Meals;
import com.javapandeng.service.EvaluatesService;
import com.javapandeng.service.ItemCategoryService;
import com.javapandeng.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.javapandeng.controller.LoginforselController.m;

/**
 * 雇员控制器
 * */
@Controller
@RequestMapping("evaluates")
public class EvaluatesController extends BaseController {
    private String sql;
    private String uid;
    private Pager<Evaluates> pagers;
    @Autowired
    private EvaluatesService evaluatesService;
    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping("/findBySql")
    public String findBySql(Model model, Evaluates evaluates, HttpSession session){
        String uid=String.valueOf(m.get("id"));
        /*String sql0 = "select * from item_category where pid ="+uid+"";
        Pager<ItemCategory> pagers0 = itemCategoryService.findBySqlRerturnEntity(sql0);*/
        List<Meals> meals = new ArrayList<>();//数据集合
        MealsDao mealsDao=new MealsDao();//操作
        meals = mealsDao.getmealsname(uid+"");   // 获取搜索栏的食物数据
        System.out.println(meals.size());
        if(uid.equals("null")){
            /*sql= "select * from evaluate where isDelete = 0";
            if(!isEmpty(evaluates.getNo())){
                sql += " and did like '%" + evaluates.getNo() + "%' ";
            }
            sql += " order by times desc";*/

            sql = "SELECT * FROM evaluate WHERE isDelete = 0 ";
            if (!isEmpty(evaluates.getNo())) {
                sql += "AND (did LIKE '%" + evaluates.getNo() + "%' OR uid LIKE '%" + evaluates.getNo() + "%') ";
            }
            sql += "ORDER BY times DESC";

        }else {
            String nameCondition = "";
            String nameCondition2 = "";
            for (int i = 0; i < meals.size(); i++) {
                if (i != 0) {
                    nameCondition += " or ";
                }
                nameCondition += "mealname like '%" + meals.get(i).getName() + "%'";
                System.out.println(meals.get(i).getName());
            }
            if(!isEmpty(evaluates.getNo())){
                nameCondition2 += "AND (did LIKE '%" + evaluates.getNo() + "%' OR uid LIKE '%" + evaluates.getNo() + "%') ";
                nameCondition2 += " order by times desc";
            }else {
                nameCondition2 += " order by times desc";
            }
            //sql= "select * from evaluate where isDelete = 0 and (" + nameCondition + ")";
            sql= "select * from evaluate where isDelete = 0 and (" + nameCondition + ")"+nameCondition2;
        }
        pagers = evaluatesService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",evaluates);
        return "evaluates/evaluates";
    }

    @RequestMapping("/findBySql2")
    public String findBySql2(Model model, Evaluates evaluates, HttpSession session){
        String uid=String.valueOf(m.get("id"));
        /*String sql0 = "select * from item_category where pid ="+uid+"";
        Pager<ItemCategory> pagers0 = itemCategoryService.findBySqlRerturnEntity(sql0);*/
        List<Meals> meals = new ArrayList<>();//数据集合
        MealsDao mealsDao=new MealsDao();//操作
        meals = mealsDao.getmealsname(uid+"");   // 获取搜索栏的食物数据
        System.out.println(meals.size());
        if(uid.equals("null")){
            /*sql= "select * from evaluate where isDelete = 0";
            if(!isEmpty(evaluates.getNo())){
                sql += " and did like '%" + evaluates.getNo() + "%' ";
            }
            sql += " order by times desc";*/

            sql = "SELECT * FROM evaluate WHERE isDelete = 0 ";
            if (!isEmpty(evaluates.getNo())) {
                sql += "AND (did LIKE '%" + evaluates.getNo() + "%' OR uid LIKE '%" + evaluates.getNo() + "%') ";
            }
            sql += "ORDER BY times DESC";

        }else {
            String nameCondition = "";
            String nameCondition2 = "";
            for (int i = 0; i < meals.size(); i++) {
                if (i != 0) {
                    nameCondition += " or ";
                }
                nameCondition += "mealname like '%" + meals.get(i).getName() + "%'";
                System.out.println(meals.get(i).getName());
            }
            if(!isEmpty(evaluates.getNo())){
                nameCondition2 += "AND (did LIKE '%" + evaluates.getNo() + "%' OR uid LIKE '%" + evaluates.getNo() + "%') ";
                nameCondition2 += " order by times desc";
            }else {
                nameCondition2 += " order by times desc";
            }
            //sql= "select * from evaluate where isDelete = 0 and (" + nameCondition + ")";
            sql= "select * from evaluate where isDelete = 0 and (" + nameCondition + ")"+nameCondition2;
        }
        pagers = evaluatesService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",evaluates);
        return "evaluates/evaluates2";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(int id){
        Evaluates obj = evaluatesService.load(id);
        obj.setIsDelete(1);
        evaluatesService.updateById(obj);
        return "redirect:/evaluates/findBySql.action";
    }
}
