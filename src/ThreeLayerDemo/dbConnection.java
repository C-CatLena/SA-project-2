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
	
	 static Connection conn;  //���ݿ����Ӷ���
	 
//	 String data;
//	 String date;
	//���Ӳ������ݿ�
	public Connection getConnection(){
		 String  driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver"; //SQL���ݿ�����     
		 String  dbURL="jdbc:sqlserver://localhost:8080;DatabaseName=SA_2";//ע������Լ������ݿ�˿ں�����     
		 String  Name="cq";//��д��¼��     
		 String  Pwd="123456";  //���� 
		 //��ȡ����
		   try{   
			   Class.forName(driverName);   
			   conn =DriverManager.getConnection(dbURL,Name,Pwd);   
			   System.out.println("���ݿ����ӳɹ�"); 
			   } catch(Exception e){      
				   e.printStackTrace();      
				   System.out.println("����ʧ��");      
			   }
		return conn;
	}
	
	//select
	public ResultSet select(String column,String start_date,String end_date) throws SQLException{
		Connection conn = getConnection();
		String sql = "select [����]," + "["+ column + "]" + " from bank where [����] between " + "'" + start_date + "'" + " and "+ "'" + end_date + "'" + ";";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
//			   //ѭ�����������
//			   while(rs.next()) {
//				    String date = rs.getString(1);  //����
//				    String data = rs.getString(2); //������
//				   System.out.println("���ڣ�" + date +"    "+ column + ":"+ data);
//			   }
               //close(conn,stmt,rs);
		
               return rs;
		}	  
		
    //close
 	public void close(Connection conn,Statement stmt,ResultSet rs) throws SQLException {
 	    // �ر�ResultSet����
 		if (rs != null)
 		{
 			rs.close();
 		}
 
 		// �ر�Statement����
 		if (stmt != null)
 		{
 			stmt.close();
 		}
  
 		// �ر�Connection����
 		if (conn != null)
 		{
 			conn.close();
 		}
 		System.out.println("��ѯ�ɹ�!");
 	}
 	
 	
}