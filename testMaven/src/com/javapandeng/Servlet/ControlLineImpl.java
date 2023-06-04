package com.javapandeng.Servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/*
 * 从数据库获取省控�?
 */
public class ControlLineImpl {
	
	public List<ControlLine> getControLine(int controlYear,int areaId,int categoryId,int batchId ){
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement pre=null;
		DBconn db= new DBconn();
		con=db.getConnection();
		List<ControlLine> controlLine= new ArrayList<ControlLine>();
		/*
		 * 
		 */
		String sql="select controlYear,controlLine,areaName,batchName,categoryName from control_line l,area a,batch b,category c where l.areaId=a.areaId and l.batchId=b.batchId and l.categoryId=c.categoryId and controlYear=? and l.areaId=?";
		if(batchId==0||batchId==-1){//批次不限
			if(categoryId==0||categoryId==-1){//类别不限
				sql+=" order by l.categoryId";//查询结果按照类别排序�?
				try {
					
					pre=con.prepareStatement(sql);
					pre.setInt(1, controlYear);
					pre.setInt(2, areaId);
					rs=pre.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{				
				sql+=" and l.categoryId=? order by l.categoryId";
				try {
					pre=con.prepareStatement(sql);
					pre.setInt(1, controlYear);
					pre.setInt(2, areaId);
					pre.setInt(3, categoryId);
					rs=pre.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}else{
			sql+=" and l.batchId=? order by l.categoryId";	
			try {
				pre=con.prepareStatement(sql);
				pre.setInt(1, controlYear);
				pre.setInt(2, areaId);
				pre.setInt(3, batchId);
				rs=pre.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(categoryId==0||categoryId==-1){//类别不限
			}else{				
				sql+=" and l.categoryId=? order by l.categoryId";
				try {
					pre=con.prepareStatement(sql);
					pre.setInt(1, controlYear);
					pre.setInt(2, areaId);
					pre.setInt(3, batchId);
					pre.setInt(4, categoryId);
					rs=pre.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}//根据请求参数，创建对应的SQL语句
		
		try {
			while (rs.next()) {// 判断是否还有记录
				ControlLine line = new ControlLine();// 将一行记录转换成�?��Control对象
				line.setAreaName(rs.getString("areaName"));
				line.setBatchName(rs.getString("batchName"));
				line.setCategoryName(rs
						.getString("categoryName"));
				line.setControlYear(rs.getInt("controlYear"));
				line.setControlLine(rs.getInt("controlLine"));
				controlLine.add(line);
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
		
		return controlLine;
	}
}
