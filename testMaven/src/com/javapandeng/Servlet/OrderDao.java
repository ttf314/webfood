package com.javapandeng.Servlet;


import com.javapandeng.po.*;
import com.solidfire.gson.Gson;
import org.aspectj.weaver.ast.Or;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDao extends DBHelper {

    public boolean addOder(String order){
        int iRow = 0;
        int rmb=1000;
        int isDelete=0;
        Gson gson = new Gson();
        Order order1 = gson.fromJson(order,Order.class);
        boolean flag = false;
        try{
            String inputString = order1.getDetel();
            String[] fields = inputString.split("\\|");
            for (String field : fields) {
                String[] parts = field.split("_");
                String name = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                System.out.println("name:"+name);
                System.out.println("quantity:"+quantity);
                // 你可以将这些字段存入数据库中
                // 例如：调用一个保存到数据库的方法
                //saveToDatabase(name, quantity);
                getConnection();   // 取得连接信息
                String sql = "insert into orderinfo(uid,count,nowrmb,price,date,detel,detel2,weizhi,isDelete) values(?,?,?,?,?,?,?,?,?)";
                pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1, order1.getUid());
                pStmt.setInt(2, quantity);
                pStmt.setDouble(3, order1.getNowrmb());
                pStmt.setDouble(4, order1.getPrice());
                pStmt.setString(5, order1.getDate());
                pStmt.setString(6, name);
                pStmt.setString(7, order1.getDetel());
                pStmt.setString(8, order1.getWeizhi());
                pStmt.setInt(9, 0);
                flag=true;
                iRow = pStmt.executeUpdate();
            }



        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        if (iRow == 1){
            flag=true;
        }
        return flag;

    }
    public boolean upUser(String order){
        int iRow = 0;
        Gson gson = new Gson();
        Order order1 = gson.fromJson(order,Order.class);
        boolean flag = false;
        try{
            getConnection();   // 取得连接信息
            String sql = "update userinfo set rmb=? where id=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setDouble(1, order1.getNowrmb());
            pStmt.setInt(2, order1.getUid());
            //iRow = pStmt.executeUpdate();

           /* String sql2 = "update userinfo set rmb=? where id=?";
            pStmt2 = conn.prepareStatement(sql2);
            pStmt2.setDouble(1, order1.getNowrmb());
            pStmt2.setInt(2, order1.getUid());*/
            flag=true;
            iRow = pStmt.executeUpdate();

        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        if (iRow == 1){
            flag=true;
        }
        return flag;

    }
    public boolean upMeals(String order){
        int iRow = 0;
        Gson gson = new Gson();
        Order order1 = gson.fromJson(order,Order.class);
        String inputString = order1.getDetel();
        //String inputString = "烤韭菜_1_|重庆麻辣牛油锅_2_|麻辣拌_3_|小份薯条_4_";
        System.out.println(inputString);
        String[] fields = inputString.split("\\|");
        boolean flag = false;
        try{
            for (String field : fields) {
                getConnection();   // 取得连接信息
                String sql = "update meals set gmNum=? where name=?";
                String selectSql = "select gmNum from meals where name=?";
                PreparedStatement selectStmt = conn.prepareStatement(selectSql);
                String[] parts = field.split("_");
                String name = parts[0];
                selectStmt.setString(1, name);
                // 执行SELECT查询操作
                ResultSet rs = selectStmt.executeQuery();
// 获取原来的gmNum值
                int oldGmNum = 0;
                if (rs.next()) {
                    oldGmNum = rs.getInt("gmNum");
                }
                pStmt = conn.prepareStatement(sql);
                int quantity = Integer.parseInt(parts[1]);
                System.out.println("name:"+name);
                System.out.println("quantity:"+quantity);
                // 你可以将这些字段存入数据库中
                // 例如：调用一个保存到数据库的方法
                //saveToDatabase(name, quantity);
                pStmt.setInt(1, quantity+oldGmNum);
                pStmt.setString(2, name);
                pStmt.executeUpdate();
            }
            //pStmt.setInt(1, 3);
            //pStmt.setString(2, "烤韭菜");
            //iRow = pStmt.executeUpdate();
           /* String sql2 = "update userinfo set rmb=? where id=?";
            pStmt2 = conn.prepareStatement(sql2);
            pStmt2.setDouble(1, order1.getNowrmb());
            pStmt2.setInt(2, order1.getUid());*/
            flag=true;
            iRow = pStmt.executeUpdate();

        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        if (iRow == 1){
            flag=true;
        }
        return flag;
    }

    // 查询用户订单信息
    public List<Order> getUserOrderList(String uid){
        List<Order> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from orderinfo where isDelete = 0 and uid =?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, uid);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                Order item = new Order();
                item.setId(rs.getInt("id"));
                item.setUid(rs.getInt("uid"));
                item.setCount(rs.getInt("count"));
                item.setPrice((float) rs.getDouble("price"));
                item.setDate( rs.getString("date"));
                item.setDetel(rs.getString("detel"));
                item.setWeizhi(rs.getString("weizhi"));
                item.setIsDelete( rs.getInt("isDelete"));
                list.add(item);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }
    // 查询用户订单信息
    public List<Order> getUserOrderList2(){
        List<Order> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from orderinfo where isDelete = 0 ";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                Order item = new Order();
                item.setId(rs.getInt("id"));
                item.setUid(rs.getInt("uid"));
                item.setCount(rs.getInt("count"));
                item.setPrice((float) rs.getDouble("price"));
                item.setDate( rs.getString("date"));
                item.setDetel(rs.getString("detel"));
                item.setWeizhi(rs.getString("weizhi"));
                item.setIsDelete( rs.getInt("isDelete"));
                list.add(item);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }
    // 查询用户订单信息
    public List<Order2> getFinshUserOrderList(String uid){
        List<Order2> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from orderinfo2 where isDelete = 0 and uid =?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, uid);
            rs = pStmt.executeQuery();
            System.out.println("1111111111111");
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                Order2 item = new Order2();
                item.setId(rs.getInt("id"));
                item.setUid(rs.getInt("uid"));
                item.setCount(rs.getInt("count"));
                item.setPrice((float) rs.getDouble("price"));
                item.setNowrmb((float) rs.getDouble("nowrmb"));
                item.setDate( rs.getString("date"));
                item.setDate2(rs.getString("date2"));
                item.setDetel(rs.getString("detel"));
                item.setDetel2(rs.getString("detel2"));
                item.setWeizhi(rs.getString("weizhi"));
                item.setLastid( rs.getInt("lastid"));
                item.setEmpname(rs.getString("empname"));
                item.setIsDelete( rs.getInt("isDelete"));

                list.add(item);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }



}