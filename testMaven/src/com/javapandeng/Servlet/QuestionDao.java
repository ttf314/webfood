package com.javapandeng.Servlet;

import com.javapandeng.po.Question;
import com.solidfire.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QuestionDao {
    /*
     * 查询问题
     */
    public List<Question> getQuestions(String keyword,String name){
        List<Question> questions= new ArrayList<Question>();
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement pre=null;
        DBconn db= new DBconn();
        con=db.getConnection();
        String sql;
        System.out.println(name+"111111111111111111");
        if(name != null && !name.equals("") && keyword != null && !keyword.equals("")) { //name和keyword都有值
            sql="select * from question where questionContent like ? and dianid=? and isDelete=0";
            try {
                pre=con.prepareStatement(sql);
                pre.setString(1, "%"+keyword+"%");
                pre.setString(2, name);
                rs=pre.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(keyword == null || keyword.equals("")) { //keyword为空
            if(name != null && !name.equals("")) { //name有值
                sql="select * from question where dianid=? and isDelete=0";
                try {
                    pre=con.prepareStatement(sql);
                    pre.setString(1, name);
                    rs=pre.executeQuery();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else { //name为空
                sql="select * from question where dianid is null or dianid = 0 and isDelete=0";
                try {
                    pre=con.prepareStatement(sql);
                    rs=pre.executeQuery();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else { //keyword有值
            if(name != null && !name.equals("")) { //name有值
                sql="select * from question where questionContent like ? and dianid=? and isDelete=0";
                try {
                    pre=con.prepareStatement(sql);
                    pre.setString(1, "%"+keyword+"%");
                    pre.setString(2, name);
                    rs=pre.executeQuery();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else { //name为空
                sql="select * from question where questionContent like ? and  dianid IS NULL or dianid = 0 and isDelete=0";
                try {
                    pre=con.prepareStatement(sql);
                    pre.setString(1, "%"+keyword+"%");
                    rs=pre.executeQuery();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            while (rs.next()) {// 判断是否还有记录
                Question question=new Question();
                question.setId(rs.getInt("id"));
                question.setQuestionContent(rs.getString("questionContent"));
                question.setQuestionStatus(rs.getString("questionStatus"));
                question.setQuestionTime(rs.getString("questionTime"));
                question.setAnswerContent(rs.getString("answerContent"));
                question.setAnswerTime(rs.getString("answerTime"));
                question.setWhoneedanswer(rs.getString("whoneedanswer"));
                question.setDianid(rs.getInt("dianid"));
                question.setIsDelete(rs.getInt("isDelete"));
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return questions;
    }

    /*
     * 查询问题
     *//*
    public List<Question> getQuestions(String keyword,String name){
        List<Question> questions= new ArrayList<Question>();
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement pre=null;
        DBconn db= new DBconn();
        con=db.getConnection();
        String sql;
        if(keyword==null||"".equals(keyword)){//如果关键字为�?
            sql="select * from question";
            try {
                pre=con.prepareStatement(sql);
                rs=pre.executeQuery();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            sql="select * from question where questionContent like ?";
            try {
                pre=con.prepareStatement(sql);
                pre.setString(1, "%"+keyword+"%");
                rs=pre.executeQuery();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {
            while (rs.next()) {// 判断是否还有记录
                Question question=new Question();
                question.setQuestionId(rs.getInt("questionId"));
                question.setQuestionContent(rs.getString("questionContent"));
                question.setQuestionStatus(rs.getString("questionStatus"));
                question.setQuestionTime(rs.getString("questionTime"));
                question.setAnswerContent(rs.getString("answerContent"));
                question.setAnswerTime(rs.getString("answerTime"));
                question.setWhoneedanswer(rs.getString("whoneedanswer"));
                question.setDianid(rs.getInt("dianid"));
                questions.add(question);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return questions;
    }*/
    /*
     * 插入问题
     */
    public boolean insertQuestion(String questionString){
        boolean flag = false;
        Gson gson=new Gson();
        Question question=gson.fromJson(questionString, Question.class);
        Connection con=null;
        PreparedStatement pre=null;
        DBconn db= new DBconn();
        con=db.getConnection();
        String sql="insert into question(questionContent,questionTime,questionStatus,whoneedanswer,dianid,isDelete)values(?,?,?,?,?,?)";
        try {
            pre= con.prepareStatement(sql);
            pre.setString(1, question.getQuestionContent());
            pre.setString(2, question.getQuestionTime());
            pre.setString(3, "暂未回复");
            pre.setString(4, question.getWhoneedanswer());
            pre.setInt(5, question.getDianid());
            pre.setInt(6, 0);
            int i = pre.executeUpdate();// 执行更新操作
            if (i == 1) {// 如果成功，则影响1行，否则
                flag = true;
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flag;
    }
    /*
     * 更新问题
     */
    public boolean updateQuestion(String questionString){
        boolean flag = false;
        Gson gson=new Gson();
        Question question=gson.fromJson(questionString, Question.class);
        Connection con=null;
        PreparedStatement pre=null;
        DBconn db= new DBconn();
        con=db.getConnection();
        String sql="update question set answerContent=? , answerTime=? , questionStatus=? where id=?";
        try {
            pre= con.prepareStatement(sql);
            pre.setString(1, question.getAnswerContent());
            pre.setString(2, question.getAnswerTime());
            pre.setString(3, "未审核");
            pre.setInt(4, question.getId());
            int i = pre.executeUpdate();// 执行更新操作
            if (i == 1) {// 如果成功，则影响1行，否则
                flag = true;
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return flag;
    }
}
