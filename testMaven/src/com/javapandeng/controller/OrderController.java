package com.javapandeng.controller;

import com.javapandeng.Servlet.ItemCategoryDao;
import com.javapandeng.Servlet.MealsDao;
import com.javapandeng.Servlet.OrderDao;
import com.javapandeng.base.BaseController;
import com.javapandeng.po.*;

import com.javapandeng.service.*;

import com.javapandeng.service.impl.Order2ServiceImpl;
import com.javapandeng.utils.Pager;
import com.javapandeng.utils.SystemContext;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.javapandeng.controller.LoginforselController.m;

/**
 * 登录控制器
 * */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController {
    private String sql;
    private Pager<Order> pagers;
    private Pager<Order2> pagers2;
    /*private Order2 order2;*/
    @Autowired
    private OrderService orderService;

    @Autowired
    private Order2Service order2Service;

    @Autowired
    private EmployeeinfoService employeeinfoService;

    private ItemCategoryService itemCategoryService;
    /**
     * 分页查询商品列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(Model model, Order order){


        String uid2=String.valueOf(m.get("id"));
        /*String sql0 = "select * from item_category where pid ="+uid+"";
        Pager<ItemCategory> pagers0 = itemCategoryService.findBySqlRerturnEntity(sql0);*/
        List<Meals> itemCategories = new ArrayList<>();//数据集合
        MealsDao mealsDao=new MealsDao();//操作
        itemCategories = mealsDao.getmealsname(uid2+"");   // 获取搜索栏的食物数据
        if(uid2.equals("null")){
            sql= "select * from orderinfo where isDelete = 0";
            if(!isEmpty(order.getNo())){
                sql += " and uid like '%" + order.getNo() + "%' ";
                sql += " order by date desc";
            }else {
                sql += " order by date desc";
            }
            pagers = orderService.findBySqlRerturnEntity(sql);
            // 构造第二个查询语句，并将第一个查询语句的结果作为搜索条件
            model.addAttribute("pagers",pagers);
            model.addAttribute("obj",order);
            return "order/orderforad";
        }else {
            String nameCondition = "";
            String nameCondition2 = "";
            for (int i = 0; i < itemCategories.size(); i++) {
                if (i != 0) {
                    nameCondition += " or ";
                }
                nameCondition += "detel like '%" + itemCategories.get(i).getName() + "%'";
            }
            if(!isEmpty(order.getNo())){
                nameCondition2 += " and uid like '%" + order.getNo() + "%' ";
                nameCondition2 += " order by date desc";
            }else {
                nameCondition2 += " order by date desc";
            }
            sql= "select * from orderinfo where isDelete = 0 and (" + nameCondition + ")"+nameCondition2;
            pagers = orderService.findBySqlRerturnEntity(sql);
            // 构造第二个查询语句，并将第一个查询语句的结果作为搜索条件
            model.addAttribute("pagers",pagers);
            model.addAttribute("obj",order);
            return "order/order";
        }
    }
    /**
     * 分页查询商品列表
     */
    @RequestMapping("/findBySql2")
    public String findBySql2(Model model, Order2 order2){
        String uid2=String.valueOf(m.get("id"));
        List<Meals> itemCategories = new ArrayList<>();//数据集合
        MealsDao mealsDao=new MealsDao();//操作
        itemCategories = mealsDao.getmealsname(uid2+"");   // 获取搜索栏的食物数据
        if(uid2.equals("null")){
            sql= "select * from orderinfo2 where isDelete = 1";
            if(!isEmpty(order2.getNo())){
                sql += " and uid like '%" + order2.getNo() + "%' ";
                sql += " order by date desc";
            }else {
                sql += " order by date desc";
            }
            pagers2 = order2Service.findBySqlRerturnEntity(sql);
        }else {
            String nameCondition = "";
            String nameCondition2 = "";
            for (int i = 0; i < itemCategories.size(); i++) {
                if (i != 0) {
                    nameCondition += " or ";
                }
                nameCondition += "detel like '%" + itemCategories.get(i).getName() + "%'";
            }
            if(!isEmpty(order2.getNo())){
                nameCondition2 += " and uid like '%" + order2.getNo() + "%' ";
                nameCondition2 += " order by date desc";
            }else {
                nameCondition2 += " order by date desc";
            }
            sql= "select * from orderinfo2 where isDelete = 1 and (" + nameCondition + ")"+nameCondition2;
            pagers2 = order2Service.findBySqlRerturnEntity(sql);
        }
        // 构造第二个查询语句，并将第一个查询语句的结果作为搜索条件
        model.addAttribute("pagers",pagers2);
        model.addAttribute("obj",order2);
        return "order/order2";
    }


    /**
     * 分页查询商品列表
     */
    @RequestMapping("/findBySql3")
    public String findBySql3(Model model, Order2 order2){
        String uid2=String.valueOf(m.get("id"));
        List<Meals> itemCategories = new ArrayList<>();//数据集合
        MealsDao mealsDao=new MealsDao();//操作
        itemCategories = mealsDao.getmealsname(uid2+"");   // 获取搜索栏的食物数据
        if(uid2.equals("null")){
            sql= "select * from orderinfo2 where isDelete = 0";
            if(!isEmpty(order2.getNo())){
                sql += " and uid like '%" + order2.getNo() + "%' ";
                sql += " order by date desc";
            }else {
                sql += " order by date desc";
            }
            pagers2 = order2Service.findBySqlRerturnEntity(sql);
        }else {
            String nameCondition = "";
            String nameCondition2 = "";
            for (int i = 0; i < itemCategories.size(); i++) {
                if (i != 0) {
                    nameCondition += " or ";
                }
                nameCondition += "detel like '%" + itemCategories.get(i).getName() + "%'";
            }
            if(!isEmpty(order2.getNo())){
                nameCondition2 += " and uid like '%" + order2.getNo() + "%' ";
                nameCondition2 += " order by date desc";
            }else {
                nameCondition2 += " order by date desc";
            }
            sql= "select * from orderinfo2 where isDelete = 0 and (" + nameCondition + ")"+nameCondition2;
            pagers2 = order2Service.findBySqlRerturnEntity(sql);
        }
        // 构造第二个查询语句，并将第一个查询语句的结果作为搜索条件
        model.addAttribute("pagers",pagers2);
        model.addAttribute("obj",order2);
        return "order/order3";
    }

    /**
     * 部分订单完成
     */
    @RequestMapping("/delete")
    public String update(Integer id){
        Order obj = orderService.load(id);
        System.out.println(obj.getUid());
        System.out.println(obj.getNowrmb());
        System.out.println(obj.getWeizhi());
        System.out.println(obj.getPrice());
        obj.setIsDelete(1);
        orderService.updateById(obj);
       /* order2=new Order2();
        order2.setId(0);
        order2.setUid(obj.getUid());
        order2.setLastid(obj.getId());
        order2.setCount(1);
        order2.setNowrmb(1);
        order2.setPrice(obj.getPrice());
        order2.setDate(obj.getDate());
        order2.setDetel("1111111111");
        order2.setWeizhi(obj.getWeizhi());
        //order2.setIsDelete(01);
        order2Service.insert(order2);*/
        return "redirect:/order/findBySql.action";
    }
    /**
     * 执行完成我的部分订单
     */
    @RequestMapping("/exAdd")
    public String exAdd(Integer id,Order2 order2,HttpServletRequest request) throws IOException {
        Order obj = orderService.load(id);
        obj.setIsDelete(1);
        orderService.updateById(obj);
        order2.setId(0);
        order2.setUid(obj.getUid());
        order2.setLastid(obj.getId());
        order2.setCount(obj.getCount());
        order2.setNowrmb(obj.getNowrmb());
        order2.setPrice(obj.getPrice());
        order2.setDate(obj.getDate().toString());
        // 创建SimpleDateFormat对象，指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 创建Date对象，表示当前时间
        Date now = new Date();
        // 调用format方法将当前时间按照指定格式转换为字符串
        String currentTime = sdf.format(now);
        // 输出当前时间
        System.out.println("当前时间：" + currentTime);
        order2.setDate2(currentTime);
        order2.setDetel(obj.getDetel());
        order2.setDetel2(obj.getDetel2());
        order2.setWeizhi(obj.getWeizhi());
        order2.setEmpname("自取");
        order2.setIsDelete(1);
        order2Service.insert(order2);


        // 获取文件输出流
        FileOutputStream fos = new FileOutputStream("D:\\order_info.txt", true);
        // 获取要写入的字符串
        String content = "执行完成我的部分订单\n";
        content += "订单号：" + obj.getId() + "\n";
        content += "用户编号：" + obj.getUid() + "\n";
        content += "出货数：" + obj.getCount() + "\n";
        content += "总价：" + obj.getPrice() + "\n";
        content += "本店提供：" + obj.getDetel() + "\n";
        content += "详情2：" + obj.getDetel2() + "\n";
        content += "位置：" + obj.getWeizhi() + "\n";
        content += "时间：" + currentTime + "\n";
        // 将字符串转为字节数组
        byte[] bytes = content.getBytes();
        // 写入文件
        fos.write(bytes);
        // 关闭文件输出流
        fos.close();

        return "redirect:/order/findBySql.action";
    }

    /**
     * 修改商品入口
     */
    @RequestMapping("/add")
    public String update(Integer id,Model model){
        String uid2=String.valueOf(m.get("id"));
        Order obj = orderService.load(id);
        Order2 obj2 = order2Service.load(id);
        String sql = "select * from employeeinfo where isDelete = 0 and itemid="+uid2+" order by id";
        List<Employeeinfo> listBySqlReturnEntity = employeeinfoService.listBySqlReturnEntity(sql);
        model.addAttribute("types",listBySqlReturnEntity);
        model.addAttribute("obj2",obj2);
        model.addAttribute("obj",obj);
        return "order/addo2";
    }
    /**
     * 执行修改商品
     */
    @RequestMapping("/exAddo2")
    public String exAddo2(Order order,Order2 order2,HttpServletRequest request) throws IOException {
        /*orderService.updateById(order);
        Order obj = orderService.load(id);*/
        order.setIsDelete(1);
        orderService.updateById(order);
        /*order2.setId(1);
        order2.setUid(1);
        order2.setLastid(1);
        order2.setCount(1);
        order2.setNowrmb(1);
        order2.setPrice(1);
        order2.setDate("obj.getDate()");
        order2.setDetel("obj.getDetel()");
        order2.setWeizhi("obj.getDetel()");*/
        order2.setId(0);
        order2.setDetel2(order.getDetel2());
        order2.setIsDelete(1);
        order2Service.insert(order2);

        return "redirect:/order/findBySql.action";
    }

    /**
     * 删除1
     */
    @RequestMapping("/up2")
    public String update2(Integer id){
        Order2 obj2 = order2Service.load(id);
        obj2.setIsDelete(0);
      /*  Order obj = orderService.load(id);
        obj2.setIsDelete(0);*/
        order2Service.updateById(obj2);
        /*orderService.updateById(obj);*/
        return "redirect:/order/findBySql.action";
    }

    @RequestMapping("/order/getData")
    @ResponseBody
    public String getOrderData() {
        List<Order> orderList = new ArrayList<>();//数据集合
        OrderDao orderDao=new OrderDao();//操作
        // 获取最新的订单列表数据
        orderList = orderDao.getUserOrderList2();   // 获取搜索栏的食物数据

        // 构造订单列表HTML代码
        StringBuilder sb = new StringBuilder();
        sb.append("<table class='table table-hover text-center'>");
        sb.append("<tr><th>编号</th><th>用户编号</th><th>出货数</th><th>详情</th><th>位置</th><th>日期</th></tr>");
        for (Order order : orderList) {
            sb.append("<tr id='order" + order.getId() + "'>");
            sb.append("<td>" + order.getId() + "</td>");
            sb.append("<td>" + order.getUid() + "</td>");
            sb.append("<td>" + order.getCount() + "</td>");
            sb.append("<td>" + order.getDetel() + "</td>");
            sb.append("<td>" + order.getWeizhi() + "</td>");
            sb.append("<td>" + order.getDate() + "</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        // 返回HTML代码
        return sb.toString();
    }

}
