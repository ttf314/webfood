package com.javapandeng.Servlet;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBconn {
	private String url;  //dbc:mysql://
	private String serverName; //数据库地�?比如：localhost
	private String portNumber; //数据库端�?3306
	private String databaseName; //数据库名
	private String username;  //数据库用户名
	private String password;// 数据库链接密�?
	
	public DBconn(){
		url="jdbc:mysql://";
		serverName="localhost";
		portNumber="3306";
		databaseName="kesedb?characterEncoding=utf-8";
		username="t";
		password="1";
	}
//	 jdbc:mysql://localhost:3306/
    public String getConnnectUrl(){
    	return  url+serverName+":"+portNumber+"/"+databaseName;
    }
    public Connection getConnection(){
    	Connection con=null;
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection(getConnnectUrl(), username, password);
    		System.out.println("连接数据库成功！");
    	}catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("连接数据库失败："+e.getMessage());
    	}
    	
    	return con;
    }
    
    
    
}
