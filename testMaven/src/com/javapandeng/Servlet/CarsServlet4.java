package com.javapandeng.Servlet;


import com.javapandeng.po.Cars;
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
@WebServlet("/CarsServlet4")
public class CarsServlet4 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CarsServlet4() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean result;
        CarDao carDao=new CarDao();//操作
        result = carDao.delCar(request.getParameter("uid"));   // 获取搜索栏的食物数据


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
