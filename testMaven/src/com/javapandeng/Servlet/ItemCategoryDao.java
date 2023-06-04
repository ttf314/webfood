package com.javapandeng.Servlet;



import com.javapandeng.po.ItemCategory;
import com.javapandeng.po.UserInfo;
import com.solidfire.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ItemCategoryDao extends DBHelper {
    // 查询餐品类别信息
    public List<ItemCategory> getItemCategoryList(){
        List<ItemCategory> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from item_category where isDelete = 0 and pid is not null";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                ItemCategory item = new ItemCategory();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPid(rs.getInt("pid"));
                item.setIsDelete(rs.getInt("isDelete"));
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
            String sql = "UPDATE  item_category set pass=123456 where pid is null";
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
    // 查询餐品类别信息
    public List<ItemCategory> getItemCategoryList2(String name){
        List<ItemCategory> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from item_category where isDelete = 0 and pid like'%"+name+"%'";

            //String sql = "select * from item_category where isDelete = 0 and pid like'%"+name+"%'";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                ItemCategory item = new ItemCategory();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPid(rs.getInt("pid"));
                item.setIsDelete(rs.getInt("isDelete"));
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
    public List<ItemCategory> getItemCategoryList3(String id){
        List<ItemCategory> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select name from item_category where isDelete = 0 and pid like'"+id+"'";

            //String sql = "select * from item_category where isDelete = 0 and pid like'%"+name+"%'";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                ItemCategory item = new ItemCategory();
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
    /**
     * 添加购物车信息
     * @param id 要添加的购物车
     * @return int 影响的行数
     */
    public boolean findyn(String id){
        int iRow = 0;
        int isDelete=0;
        boolean flag = false;
        try{
            getConnection();   // 取得连接信息
            String sql2 = "select id from item_category where id =?";
            pStmt.setString(1, id);
            rs = pStmt.executeQuery();
            if (rs.next()) {   // 这里原来用的是if，查询数据集合时应使用while
                flag=true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return flag;
    }

}