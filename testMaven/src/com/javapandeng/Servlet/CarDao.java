package com.javapandeng.Servlet;


import com.alipay.api.domain.Car;
import com.javapandeng.po.Cars;
import com.javapandeng.po.Meals;
import com.javapandeng.po.Order;
import com.solidfire.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CarDao extends DBHelper {

    public boolean delCar(String uid){
        int iRow = 0;
        Gson gson = new Gson();
        //Cars cars = gson.fromJson(car,Cars.class);
        boolean flag = false;
        try{
            getConnection();   // 取得连接信息
            String sql = "update car set isDelete=1 where usid=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setDouble(1, Integer.valueOf(uid));
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

    public List<Cars> getAllCarsList(String name){
        List<Cars> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from car where isDelete = 0 and usid =?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                Cars item = new Cars();
                item.setId(rs.getInt("id"));
                item.setUsid(rs.getInt("usid"));
                item.setPrice(rs.getString("price"));
                item.setMname(rs.getString("mname"));
                item.setMid( rs.getInt("mid"));
                item.setPrice(rs.getString("price"));
                item.setUrl(rs.getString("url"));
                item.setDescription( rs.getString("description"));
                item.setZk(rs.getInt("zk"));
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
    /**
     * 添加购物车信息
     * @param item 要添加的购物车
     * @return int 影响的行数
     */
    public boolean addCar(String item){
        int iRow = 0;
        Gson gson = new Gson();
        Cars cars = gson.fromJson(item,Cars.class);
        boolean flag = false;
        try{
            getConnection();   // 取得连接信息
            String sql2 = "select * from car where usid =? and mname=? and mid=? and isDelete=0";
            pStmt = conn.prepareStatement(sql2);
            pStmt.setInt(1, cars.getUsid());
            pStmt.setString(2, cars.getMname());
            pStmt.setInt(3, cars.getMid());
            rs = pStmt.executeQuery();
            if(rs.next()){
                flag = false;
            }else {
                String sql = "insert into car(usid, mname,mid, price, url,description,isDelete) values(?,?, ?, ?, ?, ?,?)";
                pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1, cars.getUsid());
                pStmt.setString(2, cars.getMname());
                pStmt.setInt(3, cars.getMid());
                pStmt.setString(4, cars.getPrice());
                pStmt.setString(5, cars.getUrl());
                pStmt.setString(6, cars.getDescription());
                pStmt.setInt(7, 0);
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

    /**
     * 按餐品名称用户id查询信息
     * @return Meals 实例
     */
    public List<Cars> getCarsByname(String name){
        List<Cars> list = new ArrayList<>();
        List<Cars> list2 = new ArrayList<>();
        Cars item = null;
        Gson gson = new Gson();
        Cars cars = gson.fromJson(name,Cars.class);
        item.setMname(name);
        String str = name+"";
        System.out.println("item.getMname()"+item.getMname());
        //截取_之后字符串
        String str1 = str.substring(0, str.indexOf("_"));
        System.out.println("截取_之前字符串:"+str1);
        String str2 = str.substring(str1.length()+1, str.length());
        System.out.println("截取_之后字符串:"+str2);
        System.out.println(cars);
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from car where isDelete=0 and usid=? and mid =? ";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, Integer.valueOf(str1));
            pStmt.setInt(2, Integer.valueOf(str2));
            //pStmt.setInt(2, isDelete);
            rs = pStmt.executeQuery();
            if(rs.next()){
                item.setPrice(rs.getString("price"));
                item.setUrl( rs.getString("url"));
                //item.setUsid(rs.getInt("usid"));
                list.add(item);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }

    /**
     * 按餐品名称用户id查询信息
     * @return Meals 实例
     */
    /*public Cars getCarsByname(String name){
        List<Cars> list = new ArrayList<>();
        Cars item = null;
        boolean flag = false;
        int iRow = 0;
        Gson gson = new Gson();
        Cars cars = gson.fromJson(name,Cars.class);
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from car where isDelete=0 and mname=? and usid=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, cars.getMname());
            pStmt.setString(2, cars.getUsid()+"");
            //pStmt.setInt(2, isDelete);
            rs = pStmt.executeQuery();
            if(rs.next()){
                item = null;
                flag = true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return item;
    }*/

    /**
     * 按餐品名称用户id查询信息
     * @return Meals 实例
     */
    /*public boolean getCarsByname(String name){
        Cars item = null;
        boolean flag = false;
        int iRow = 0;
        Gson gson = new Gson();
        Cars cars = gson.fromJson(name,Cars.class);
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from car where isDelete=0 and mname=? and uid=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, cars.getMname());
            pStmt.setString(2, cars.getUsid()+"");
            //pStmt.setInt(2, isDelete);
            rs = pStmt.executeQuery();
            if(rs.next()){
                flag = true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return flag;
    }*/

    // 查询上架了的食物信息
    public List<Meals> getAllMealsList(){
        List<Meals> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from meals where isDelete = 0";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                Meals item = new Meals();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getString("price"));
                item.setScNum(rs.getInt("scNum"));
                item.setGmNum( rs.getInt("gmNum"));
                item.setUrl1(rs.getString("url1"));
                item.setDesccription(rs.getString("description"));
                item.setPam1( rs.getString("pam1"));
                item.setVal1(rs.getString("val1"));
                item.setType( rs.getInt("type"));
                item.setZk( rs.getInt("zk"));
                item.setCategoryIdOne( rs.getInt("category_id_one"));
                item.setCategoryIdTwo( rs.getInt("category_id_two"));
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
// 查询上架了的食物信息
    public List<Meals> getAllMealsList2(String name,String category_id_two){
        List<Meals> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from meals where isDelete = 0";

            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                Meals item = new Meals();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getString("price"));
                item.setScNum(rs.getInt("scNum"));
                item.setGmNum( rs.getInt("gmNum"));
                item.setUrl1(rs.getString("url1"));
                item.setDesccription(rs.getString("description"));
                item.setPam1( rs.getString("pam1"));
                item.setVal1(rs.getString("val1"));
                item.setType( rs.getInt("type"));
                item.setZk( rs.getInt("zk"));
                item.setCategoryIdOne( rs.getInt("category_id_one"));
                item.setCategoryIdTwo( rs.getInt("category_id_two"));
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
    // 模糊查询食物信息
    public List<Meals> getMealsList(String name){
        List<Meals> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from meals where isDelete = 0 and name like'%"+name+"%'";
            pStmt = conn.prepareStatement(sql);
            //pStmt.setString(1, name);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                Meals item = new Meals();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getString("price"));
                item.setScNum(rs.getInt("scNum"));
                item.setGmNum( rs.getInt("gmNum"));
                item.setUrl1(rs.getString("url1"));
                item.setDesccription(rs.getString("description"));
                item.setPam1( rs.getString("pam1"));
                item.setVal1(rs.getString("val1"));
                item.setType( rs.getInt("type"));
                item.setZk( rs.getInt("zk"));
                item.setCategoryIdOne( rs.getInt("category_id_one"));
                item.setCategoryIdTwo( rs.getInt("category_id_two"));
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
    // 列表查询食物信息
    public List<Meals> getMealsList2(String category_id_two){
        List<Meals> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from meals where isDelete = 0 and category_id_two =?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, category_id_two);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                Meals item = new Meals();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getString("price"));
                item.setScNum(rs.getInt("scNum"));
                item.setGmNum( rs.getInt("gmNum"));
                item.setUrl1(rs.getString("url1"));
                item.setDesccription(rs.getString("description"));
                item.setPam1( rs.getString("pam1"));
                item.setVal1(rs.getString("val1"));
                item.setType( rs.getInt("type"));
                item.setZk( rs.getInt("zk"));
                item.setCategoryIdOne( rs.getInt("category_id_one"));
                item.setCategoryIdTwo( rs.getInt("category_id_two"));
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

    /**
     * 按餐品名称查询用户信息
     * @return Meals 实例
     */
    public Meals getMealsByname(String name){
        Meals item = null;
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from meals where isDelete=0 and name=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            //pStmt.setInt(2, isDelete);
            rs = pStmt.executeQuery();
            if(rs.next()){
                item = new Meals();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getString("price"));
                item.setScNum(rs.getInt("scNum"));
                item.setGmNum( rs.getInt("gmNum"));
                item.setUrl1(rs.getString("url1"));
                item.setDesccription(rs.getString("description"));
                item.setPam1( rs.getString("pam1"));
                item.setVal1(rs.getString("val1"));
                item.setType( rs.getInt("type"));
                item.setZk( rs.getInt("zk"));
                item.setCategoryIdOne( rs.getInt("category_id_one"));
                item.setCategoryIdTwo( rs.getInt("category_id_two"));
                item.setIsDelete( rs.getInt("isDelete"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return item;
    }

    /**
     * 修改用户信息 U
     * @return int 影响的行数
     */
    public int editMeals(int type,int id ){
        int iRow = 0;
        try{
            getConnection();   // 取得连接信息
            String sql = "update meals set type=? where id=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1,type);
            pStmt.setInt(2, id);
            iRow = pStmt.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }
}