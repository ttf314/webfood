package com.javapandeng.Servlet;


import com.javapandeng.po.Question;
import com.solidfire.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String flag=request.getParameter("flag");
        QuestionDao qi=new QuestionDao();
        if("insert".equals(flag)){//提交问题
            boolean result=qi.insertQuestion(request.getParameter("question"));
            out.write(result+"");
        }else if("update".equals(flag)){//更新问题
            boolean result=qi.updateQuestion(request.getParameter("question"));
            out.write(result+"");
        }/*else if (request.getParameter("name").equals("")){
            List<Question> questions=qi.getQuestions(request.getParameter("keyword"),request.getParameter("name"));
            Gson gson=new Gson();
            String result=gson.toJson(questions);
            out.write(result);
        }*/else{//查询问题
            System.out.println(request.getParameter("name"));
            List<Question> questions=qi.getQuestions(request.getParameter("keyword"),request.getParameter("name"));
            Gson gson=new Gson();
            String result=gson.toJson(questions);
            out.write(result);
        }
    }

}