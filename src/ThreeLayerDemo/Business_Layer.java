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
	
	//������Ϣ�ͽ�����Ϣ
	private InputStream in;
    private OutputStream out;
    private DataInputStream  Din;
    private DataOutputStream Dout;
    
    //��������
	String startdate= null;
	String enddate = null;
	String column = null;
	
	//��������
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
			System.out.println("������׼��������!");
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
    }
    
    
	public static void main(String[] args) {
        // TODO Auto-generated method stub
		Business_Layer bus = new Business_Layer();
		new Thread(bus).start();
    }
	
	 //�������̷߳�ֹ���߳��������������
              
        @Override
    public void run() {
        Socket s = null;
        String content = null;
        while(true){
    	   try {
				s = ss.accept();
				System.out.println("�������˿���׼��������Ϣ");
				
			   	//������Ϣ�õ�����
			    in=s.getInputStream();
			    Din=new DataInputStream(in);
			    
			    //������Ϣ�õ�����
                out=s.getOutputStream();
                Dout=new DataOutputStream(out); 
                
                //������Ϣ
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
               
               //������Ϣ
                	
               dbConnection db = new dbConnection();
               
//               //�����±�
//               int n = 0;
               //��ͼ
//               LineChart lc = new LineChart();
               
               try {
				rs = db.select(column, startdate, enddate);
				while(rs.next()) {
				     date = rs.getString(1);  //����
				     price = rs.getFloat(2); //������
				     Dout.writeUTF("����:" + date +" "+ column + ": "+ price);
				     
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
//				lc.DrawChart();//������ͼ�������.png�ļ�
//				n = 0;
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}  
				} catch (IOException e2) {
					// TODO �Զ����ɵ� catch ��
					e2.printStackTrace();
				}
            	   try {
            		   		Dout.close();
            		   		out.close();
                             Din.close();
                             in.close();
                             rs.close();
                             System.out.println("����ɹ�");
                      } catch (IOException e1) {
                             e1.printStackTrace();
                      } catch (SQLException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
               }
        }
        
        
    public void draw() {
    	
    }

}
