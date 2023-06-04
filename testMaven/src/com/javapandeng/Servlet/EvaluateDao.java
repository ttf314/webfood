package com.javapandeng.Servlet;




import com.javapandeng.po.Evaluate;
import com.javapandeng.po.Evaluates;
import com.javapandeng.po.Meals;
import com.javapandeng.po.UserInfo;
import com.solidfire.gson.Gson;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据库操作类
 * 实现用户的CRUD操作
 */
public class EvaluateDao extends DBHelper {

    /**
     * 添加信息
     * @param item 要添加的
     * @return int 影响的行数
     */
    public boolean addEval(String item){
        int iRow = 0;
        Gson gson = new Gson();
        Evaluate evaluate = gson.fromJson(item,Evaluate.class);
        boolean flag = false;
        try{
                getConnection();   // 取得连接信息
                String sql = "insert into evaluate(uid, did,mealname, times,vdedtel,isDelete) values(?, ?, ?,?, ?, ?)";
                pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1, evaluate.getUid());
                pStmt.setInt(2, evaluate.getDid());
                pStmt.setString(3, evaluate.getMealname());
                pStmt.setString(4, evaluate.getTimes());
                pStmt.setString(5, evaluate.getVdedtel());
                pStmt.setInt(6, 0);
            iRow = pStmt.executeUpdate();
            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }
    /*
    public boolean addEval(String item) {
        int iRow = 0;
        Gson gson = new Gson();
        Evaluate evaluate = gson.fromJson(item, Evaluate.class);
        try {
            getConnection(); // 取得连接信息
            String sql = "insert into evaluate(uid, did, mealname, times,vdedtel,isDelete) values(?, ?, ?,?, ?, ?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, evaluate.getUid());
            pStmt.setInt(2, evaluate.getDid());
            pStmt.setString(3, evaluate.getMealname());
            pStmt.setString(4, evaluate.getTimes());

            // 使用IKAnalyzer进行敏感词检查
            String vdedtel = evaluate.getVdedtel();
            Analyzer analyzer = new IKAnalyzer();
            StringReader reader = new StringReader(vdedtel);
            TokenStream ts = analyzer.tokenStream("", reader);
            CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
            ts.reset();
            while (ts.incrementToken()) {
                String word = term.toString();
                // 判断是否存在敏感词
                if (isSensitive(word)) {
                    // 存在敏感词，返回false
                    return false;
                }
            }
            ts.end();
            ts.close();
            reader.close();
            analyzer.close();

            // 不存在敏感词，插入数据库
            pStmt.setString(5, vdedtel);
            pStmt.setInt(6, 0);
            iRow = pStmt.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
<dependency>
            <groupId>org.wltea.ik-analyzer</groupId>
            <artifactId>ik-analyzer</artifactId>
            <version>3.2.8</version>
        </dependency>
        return iRow > 0;
    }*/

    // 判断是否为敏感词
    private boolean isSensitive(String word) {
        // TODO: 实现敏感词判断逻辑
        return false;
    }

    // 模糊查询食物信息
    public List<Evaluate> getEvaluateListByname(String name){
        List<Evaluate> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select * from evaluate where mealname like'"+name+"'";
            pStmt = conn.prepareStatement(sql);
            //pStmt.setString(1, name);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                Evaluate item = new Evaluate();
                item.setUid(rs.getInt("uid"));
                item.setVdedtel(rs.getString("vdedtel"));
                item.setTimes(rs.getString("times"));
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
    public List<Evaluates> getevsdid(String id){
        List<Evaluates> list = new ArrayList<>();
        try{
            getConnection();   // 取得连接信息
            String sql = "select did from evaluate where isDelete = 0 and did like'"+id+"'";
            //String sql = "select * from item_category where isDelete = 0 and pid like'%"+name+"%'";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next()){   // 这里原来用的是if，查询数据集合时应使用while
                Evaluates item = new Evaluates();
                item.setDid(rs.getInt("did"));
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
