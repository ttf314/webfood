package com.javapandeng.Servlet;

import com.javapandeng.po.Evaluates;
import com.javapandeng.po.Info;
import com.javapandeng.po.UserInfo;
import com.solidfire.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class InfoDao extends DBHelper {
    // 查询餐品类别信息
    public String getinfo(){
        String info = null;
        try{
            getConnection();   // 取得连接信息
            String sql = "SELECT * FROM info ORDER BY ID DESC LIMIT 1";
            //String sql = "select * from item_category where isDelete = 0 and pid like'%"+name+"%'";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                info=rs.getString("xingxi");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return info;
    }
    public boolean addinfo(String item){
        int iRow = 0;

        boolean flag = false;
        try{
                getConnection();   // 取得连接信息
                String sql = "insert into info(xingxi) values(?)";
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, item);
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
}
