package ThreeLayerDemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Business_Layer implements Runnable {
	
	static ServerSocket ss;
	
	//发送消息和接收消息
	private InputStream in;
    private OutputStream out;
    private DataInputStream  Din;
    private DataOutputStream Dout;
    
    //接收数据
	String startdate= null;
	String enddate = null;
	String column = null;
	
	//返回数据
	Float price = null;
	String date = null;
    ResultSet rs;
    
    ArrayList<Integer> year_arr=new ArrayList<Integer>();
    ArrayList<Integer> month_arr=new ArrayList<Integer>();
    ArrayList<Integer> day_arr=new ArrayList<Integer>();
    ArrayList<Float> price_arr = new ArrayList<Float>();
    

    
    public Business_Layer() {
    	try {
			ss = new ServerSocket(100);
			System.out.println("服务器准备已连接!");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    }
    
    
	public static void main(String[] args) {
        // TODO Auto-generated method stub
		Business_Layer bus = new Business_Layer();
		new Thread(bus).start();
    }
	
	 //开启多线程防止该线程阻塞，程序崩溃
              
        @Override
    public void run() {
        Socket s = null;
        String content = null;
        while(true){
    	   try {
				s = ss.accept();
				System.out.println("服务器端开启准备接收消息");
				
			   	//接收消息用到的流
			    in=s.getInputStream();
			    Din=new DataInputStream(in);
			    
			    //发送消息用到的流
                out=s.getOutputStream();
                Dout=new DataOutputStream(out); 
                
                //接收消息
                content = Din.readUTF();
                int len = content.length();
                	int one = content.indexOf('|');
                	column = content.substring(0,one);
                	content = content.substring(one+1,len);
                	int two = content.indexOf('|');
                	startdate = content.substring(0,two);
                	len = content.length();
                	enddate = content.substring(two+1,len);
                	
//                	System.out.println(column);
//                	System.out.println(startdate);
//                	System.out.println(enddate);
               
               //发送消息
                	
               dbConnection db = new dbConnection();
               
//               //数组下标
//               int n = 0;
               //画图
//               LineChart lc = new LineChart();
               
               try {
				rs = db.select(column, startdate, enddate);
				while(rs.next()) {
				     date = rs.getString(1);  //日期
				     price = rs.getFloat(2); //数据项
				     Dout.writeUTF("日期:" + date +" "+ column + ": "+ price);
				     
//				     price_arr.add(price);
				     int zero = date.indexOf(' ');
				     date = date.substring(0,zero);
//				     
				     int len1 = date.length();
				     int one1 = date.indexOf('-');
				     int year = Integer.parseInt(date.substring(0,one1));
//				     year_arr.add(year) ;
//				     
				     date = date.substring(one1+1,len1);
				     int two1 = date.indexOf('-');
				     int month = Integer.parseInt(date.substring(0, two1));
//				     month_arr.add(month);
//				     
				     len1 = date.length();
				     int day = Integer.parseInt(date.substring(two1+1, len1));
//				     day_arr.add(day);
				     //System.out.println(year + "  "+ month+" " + day +"  " + price);
//				     n++;
				}
				
//				lc.column = column;
//				lc.createDataset(n,day_arr, month_arr, year_arr,price_arr);
//				lc.DrawChart();//画折线图并保存成.png文件
//				n = 0;
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}  
				} catch (IOException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				}
            	   try {
            		   		Dout.close();
            		   		out.close();
                             Din.close();
                             in.close();
                             rs.close();
                             System.out.println("服务成功");
                      } catch (IOException e1) {
                             e1.printStackTrace();
                      } catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
               }
        }
        
        
    public void draw() {
    	
    }

}
