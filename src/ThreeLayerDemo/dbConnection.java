package ThreeLayerDemo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class dbConnection {
	
	 static Connection conn;  //数据库连接对象
	 
//	 String data;
//	 String date;
	//连接并打开数据库
	public Connection getConnection(){
		 String  driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver"; //SQL数据库引擎     
		 String  dbURL="jdbc:sqlserver://localhost:8080;DatabaseName=SA_2";//注意更换自己的数据库端口和名称     
		 String  Name="cq";//填写登录名     
		 String  Pwd="123456";  //密码 
		 //获取连接
		   try{   
			   Class.forName(driverName);   
			   conn =DriverManager.getConnection(dbURL,Name,Pwd);   
			   System.out.println("数据库连接成功"); 
			   } catch(Exception e){      
				   e.printStackTrace();      
				   System.out.println("连接失败");      
			   }
		return conn;
	}
	
	//select
	public ResultSet select(String column,String start_date,String end_date) throws SQLException{
		Connection conn = getConnection();
		String sql = "select [日期]," + "["+ column + "]" + " from bank where [日期] between " + "'" + start_date + "'" + " and "+ "'" + end_date + "'" + ";";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
//			   //循环遍历结果集
//			   while(rs.next()) {
//				    String date = rs.getString(1);  //日期
//				    String data = rs.getString(2); //数据项
//				   System.out.println("日期：" + date +"    "+ column + ":"+ data);
//			   }
               //close(conn,stmt,rs);
		
               return rs;
		}	  
		
    //close
 	public void close(Connection conn,Statement stmt,ResultSet rs) throws SQLException {
 	    // 关闭ResultSet对象
 		if (rs != null)
 		{
 			rs.close();
 		}
 
 		// 关闭Statement对象
 		if (stmt != null)
 		{
 			stmt.close();
 		}
  
 		// 关闭Connection对象
 		if (conn != null)
 		{
 			conn.close();
 		}
 		System.out.println("查询成功!");
 	}
 	
 	
}