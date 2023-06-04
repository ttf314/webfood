package com.javapandeng.Servlet;


import com.javapandeng.po.ItemCategory;
import com.javapandeng.po.Manage;
import com.javapandeng.po.Meals;

import java.util.ArrayList;
import java.util.List;

public class MealsDao extends DBHelper {
    // 查询上架了的食物信息
    public List<Meals> getAllMealsList(){
        List<Meals> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from meals where isDelete = 0 order by gmNum desc";
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

    // 模糊查询食物信息
    public List<Meals> getMealsListByType(String name){
        List<Meals> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from meals where isDelete = 0 and type like'%"+name+"%'";
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
     * 按餐品名称查询信息
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

    // 列表查询食物信息
    public List<Meals> getMealsListlunbotu(){
        List<Meals> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from meals where isDelete = 0 and type !=0 order by type asc ";
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

    // 查询餐品类别信息
    public List<Meals> getmealsname(String id){
        List<Meals> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select name from meals where isDelete = 0 and category_id_one like'"+id+"'";
            //String sql = "select * from item_category where isDelete = 0 and pid like'%"+name+"%'";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                Meals item = new Meals();
                item.setName(rs.getString("name"));
                list.add(item);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }

    // 查询餐品类别信息
    public int  upall(){
        int iRow = 0;
        try{
            getConnection();   // 取得连接信息
            String sql = "UPDATE  meals set type=NULL";
            //String sql = "select * from item_category where isDelete = 0 and pid like'%"+name+"%'";
            pStmt = conn.prepareStatement(sql);
            iRow = pStmt.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return  iRow ;
    }
}