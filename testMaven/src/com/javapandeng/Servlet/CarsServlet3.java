package com.javapandeng.Servlet;


import com.javapandeng.po.Cars;
import com.javapandeng.po.Meals;
import com.solidfire.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ControlLineServlet
 */
@WebServlet("/CarsServlet3")
public class CarsServlet3 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CarsServlet3() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      /*  List<Cars> cars = new ArrayList<>();//数据集合
        CarDao carDao=new CarDao();//操作
        cars = carDao.getAllCarsList();   // 获取搜索栏的食物数据
        //List<Meals> controlLines= cl.getMealsList();
        Gson gson=new Gson();//创建GSON对象
        String result=gson.toJson(cars);//将查询结果对象转换成json格式字符串
        //往客户端(这里的客户端指的是浏览器)回传 字符串数据
        //设置服务器字符集为utf-8
        response.setCharacterEncoding("utf-8");
        //通过响应头，设置浏览器也使用utf-8字符集
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();//获取对应的额输出流
        out.write(result);//输出结果内容*/
        String name=request.getParameter("name");
        List<Cars> cars = new ArrayList<>();//数据集合
        CarDao carDao=new CarDao();//操作
        cars = carDao.getAllCarsList(name);   // 获取搜索栏的食物数据

        //List<Meals> controlLines= cl.getMealsList();
        Gson gson=new Gson();//创建GSON对象
        String result=gson.toJson(cars);//将查询结果对象转换成json格式字符串
        //往客户端(这里的客户端指的是浏览器)回传 字符串数据
        //设置服务器字符集为utf-8
        response.setCharacterEncoding("utf-8");
        //通过响应头，设置浏览器也使用utf-8字符集
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();//获取对应的额输出流
        out.write(result);//输出结果内容
    }
    public static int getInt(HttpServletRequest request,String name){//根据请求参数获取对应的值
        int result;
        String nameString=request.getParameter(name);
        if(nameString==null||"".equals(nameString)){//如果没有传递参数，默认为-1
            result=-1;
        }else{
            try{
                result=Integer.parseInt(nameString);
            }catch(NumberFormatException ne){
                result=-1;
            }
        }
        return result;
    }
}
