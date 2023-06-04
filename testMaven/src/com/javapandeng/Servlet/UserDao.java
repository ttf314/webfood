package com.javapandeng.Servlet;




import com.javapandeng.po.Cars;
import com.javapandeng.po.Meals;
import com.javapandeng.po.UserInfo;
import com.solidfire.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据库操作类
 * 实现用户的CRUD操作
 */
public class UserDao extends DBHelper {

    // 查询所有的用户信息 R
    public List<UserInfo> getAllUserList(){
        List<UserInfo> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from userinfo";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                UserInfo item = new UserInfo();
                item.setId(rs.getInt("id"));
                item.setUserName(rs.getString("userName"));
                item.setPassWord(rs.getString("passWord"));
                item.setPhone(rs.getString("phone"));
                item.setRealname(rs.getString("realname"));
                item.setSex(rs.getString("sex"));
                item.setAddress(rs.getString("address"));
                item.setEmail(rs.getString("email"));
                item.setCreateDt(rs.getString("createDt"));
                item.setRmb(rs.getInt("rmb"));
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
    /**
     * 按用户名和密码查询用户信息 R
     * @return UserInfo 实例
     */
    public List<UserInfo> getUserByUnameAndUpass(String user){
        UserInfo item = null;
        int iRow = 0;
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(user,UserInfo.class);
        List<UserInfo> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from userinfo where userName=? and passWord=? and isDelete=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userInfo.getUserName());
            pStmt.setString(2, userInfo.getPassWord());
            pStmt.setInt(3, 0);
            rs = pStmt.executeQuery();
            if(rs.next()){
                item = new UserInfo();
                item.setId(rs.getInt("id"));
                item.setUserName(rs.getString("userName"));
                item.setPassWord(rs.getString("passWord"));
                item.setPhone(rs.getString("phone"));
                item.setRealname(rs.getString("realname"));
                item.setSex(rs.getString("sex"));
                item.setAddress(rs.getString("address"));
                item.setEmail(rs.getString("email"));
                item.setCreateDt(rs.getString("createDt"));
                item.setRmb(rs.getInt("rmb"));
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
    /**
     * 按用户名查询用户信息 R
     * @param userName 用户名
     * @return UserInfo 实例
     */
    public UserInfo getUserByUname(String userName){
        UserInfo item = null;
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from userinfo where userName=? and isDelete=0";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userName);
            rs = pStmt.executeQuery();
            if(rs.next()){
                item = new UserInfo();
                item.setId(rs.getInt("id"));
                item.setUserName(userName);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return item;
    }
    /**
     * 添加购物车信息
     * @param item 要添加的购物车
     * @return int 影响的行数
     */
    public boolean addUser(String item){
        int iRow = 0;
        int rmb=1000;
        int isDelete=0;
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(item,UserInfo.class);
        boolean flag = false;
        try{
            getConnection();   // 取得连接信息
            String sql2 = "select * from userInfo where userName =?";

            pStmt = conn.prepareStatement(sql2);
            pStmt.setString(1, userInfo.getUserName());
            rs = pStmt.executeQuery();
            if(rs.next()){
                flag = false;
            }else {
                getConnection();   // 取得连接信息
                String sql = "insert into userinfo(userName, passWord, createDt, rmb,isDelete) values(?, ?, ?, ?, ?)";
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, userInfo.getUserName());
                pStmt.setString(2, userInfo.getPassWord());
                pStmt.setString(3, userInfo.getCreateDt());
                pStmt.setInt(4, rmb);
                pStmt.setInt(5, isDelete);
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
     * 添加购物车信息
     * @param item 要添加的购物车
     * @return int 影响的行数
     */
    public int upUser(String item){
        int iRow = 0;
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(item,UserInfo.class);
        System.out.println(userInfo.getPhone());
        try{
                getConnection();   // 取得连接信息
                String sql = "UPDATE userinfo SET userName = ?, phone = ?, address = ?, email = ?, realname=?,sex=? WHERE id = ?;";
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, userInfo.getUserName());
                pStmt.setString(2, userInfo.getPhone());
                pStmt.setString(3, userInfo.getAddress());
                pStmt.setString(4, userInfo.getEmail());
                pStmt.setString(5, userInfo.getRealname());
                pStmt.setString(6, userInfo.getSex());
                pStmt.setInt(7, userInfo.getId());
                iRow = pStmt.executeUpdate();

        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }
}
