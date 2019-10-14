package ThreeLayerDemo;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;


public class UI_client {
	
	//数据
	static String data[] = {"开盘价(元)","收盘价(元)","最高价(元)","最低价(元)"};
	String start_date = null;
	String end_date = null;
	String column = null;
	
    ArrayList<Integer> year_arr=new ArrayList<Integer>();
    ArrayList<Integer> month_arr=new ArrayList<Integer>();
    ArrayList<Integer> day_arr=new ArrayList<Integer>();
    ArrayList<Float> price_arr = new ArrayList<Float>();
	String date;
	String price;
	int n = 0;
	
	//组件
	JComboBox jcb_year1 = new JComboBox();
	JComboBox jcb_month1 = new JComboBox();
	JComboBox jcb_day1 = new JComboBox();
	JComboBox jcb_year2 = new JComboBox();
	JComboBox jcb_month2 = new JComboBox();
	JComboBox jcb_day2 = new JComboBox();
	JComboBox jcb_data = new JComboBox();
	
	JTextArea jta = new JTextArea();
	JTextField jt_avg = new JTextField();
	
	//接收消息和发送消息
	private InputStream in;
	private OutputStream out;
	private DataOutputStream Dout;
	private DataInputStream Din;
	
	public static void main(String[] args) {
		new UI_client();
	}
	
	//界面
	public UI_client() {
		//创建窗口
		JFrame frame = new JFrame("浦发银行数据分析处理系统");
	    frame.setSize(700, 800);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//画布
	    JPanel panel = new JPanel(); 
		frame.add(panel);
		panel.setLayout(null);
				

		//创建起始日期label
		JLabel label_date1 = new JLabel("请选择起始日期:");
		label_date1.setFont(new Font("黑体",Font.BOLD,25));
		label_date1.setBounds(35,25,200,25);
		panel.add(label_date1);
				
		//year
		//JComboBox jcb_year1 = new JComboBox();
		jcb_year1.setFont(new Font("黑体",Font.BOLD,20));
		jcb_year1.setBounds(230, 25, 80, 30);
		for(int i = 1999;i <= 2016;i++) {
			String s = Integer.toString(i);
			jcb_year1.addItem(s);
		}
		panel.add(jcb_year1);
		JLabel year1 = new JLabel("年");
		year1.setFont(new Font("黑体",Font.BOLD,25));
		year1.setBounds(320, 25, 40, 25);
		panel.add(year1);
			    
		//month
		//JComboBox jcb_month1 = new JComboBox();
		jcb_month1.setFont(new Font("黑体",Font.BOLD,20));
		jcb_month1.setBounds(360, 25, 70, 30);
		for(int i = 1;i <= 12;i++) {
			String s = Integer.toString(i);
			jcb_month1.addItem(s);
		}
		panel.add(jcb_month1);
		JLabel month1 = new JLabel("月");
		month1.setFont(new Font("黑体",Font.BOLD,25));
		month1.setBounds(440, 25, 40, 25);
		panel.add(month1);
				
		//day
		//JComboBox jcb_day1 = new JComboBox();
		jcb_day1.setFont(new Font("黑体",Font.BOLD,20));
		jcb_day1.setBounds(480, 25, 70, 30);
		for(int i = 1;i <= 31;i++) {
			String s = Integer.toString(i);
			jcb_day1.addItem(s);
		}
		panel.add(jcb_day1);
		JLabel day1 = new JLabel("日");
		day1.setFont(new Font("黑体",Font.BOLD,25));
		day1.setBounds(560, 25, 40, 25);
		panel.add(day1);
				
				
		//创建终止日期label
		JLabel label_date2 = new JLabel("请选择终止日期:");
		label_date2.setFont(new Font("黑体",Font.BOLD,25));
		label_date2.setBounds(35,70,200,25);
		panel.add(label_date2);
				
		//year
		//JComboBox jcb_year2 = new JComboBox();
		jcb_year2.setFont(new Font("黑体",Font.BOLD,20));
		jcb_year2.setBounds(230, 70, 80, 30);
		for(int i = 1999;i <= 2016;i++) {
			String s = Integer.toString(i);
			jcb_year2.addItem(s);
		}
		panel.add(jcb_year2);
		JLabel year2 = new JLabel("年");
		year2.setFont(new Font("黑体",Font.BOLD,25));
		year2.setBounds(320, 70, 40, 25);
		panel.add(year2);
				
		//month
		//JComboBox jcb_month2 = new JComboBox();
		jcb_month2.setFont(new Font("黑体",Font.BOLD,20));
		jcb_month2.setBounds(360, 70, 70, 30);
		for(int i = 1;i <= 12;i++) {
			String s = Integer.toString(i);
			jcb_month2.addItem(s);
		}
		panel.add(jcb_month2);
		JLabel month2 = new JLabel("月");
		month2.setFont(new Font("黑体",Font.BOLD,25));
		month2.setBounds(440, 70, 40, 25);
		panel.add(month2);
					
		//day
		//JComboBox jcb_day2 = new JComboBox();
		jcb_day2.setFont(new Font("黑体",Font.BOLD,20));
		jcb_day2.setBounds(480, 70, 70, 30);
		for(int i = 1;i <= 31;i++) {
			String s = Integer.toString(i);
			jcb_day2.addItem(s);
		}
		panel.add(jcb_day2);
		JLabel day2 = new JLabel("日");
		day2.setFont(new Font("黑体",Font.BOLD,25));
		day2.setBounds(560, 70, 40, 25);
		panel.add(day2);
				
		//data
		JLabel label_data = new JLabel("请选择数据项:");
		label_data.setFont(new Font("黑体",Font.BOLD,25));
		label_data.setBounds(50,120,200,25);
		panel.add(label_data);
		//JComboBox jcb_data = new JComboBox();
		jcb_data.setFont(new Font("黑体",Font.BOLD,20));
		jcb_data.setBounds(230, 120, 200, 30);
		for(int i = 0;i < data.length;i++) {
			String s = data[i];
			jcb_data.addItem(s);
		}
		panel.add(jcb_data);

		//AVG
		JButton jb_calculate = new JButton("计算平均值");
		jb_calculate.setFont(new Font("黑体",Font.BOLD,20));
		jb_calculate.setBounds(50, 170, 150, 40);
		panel.add(jb_calculate);
		jb_calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float sum = 0,ans;
				for(int i = 0;i < n;i++) {
					sum += price_arr.get(i);
				}
				ans = sum/n;
				jt_avg.setText(String.valueOf(ans));
			}
		});
		
		//JTextField jt_avg = new JTextField();
		jt_avg.setFont(new Font("黑体",Font.BOLD,20));
		jt_avg.setBounds(220,170,200,40);
		panel.add(jt_avg);
		JLabel yuan = new JLabel("(元)");
		yuan.setFont(new Font("黑体",Font.BOLD,25));
		yuan.setBounds(420, 180, 70, 25);
		panel.add(yuan);
				
		//select
		JButton jb_select = new JButton("查询");
		jb_select.setFont(new Font("黑体",Font.BOLD,20));
		jb_select.setBounds(470, 120, 150, 30);
		panel.add(jb_select);		
				
		//显示区
		//JTextArea jta = new JTextArea();
		jta.setFont(new Font("黑体",Font.BOLD,15));
		JScrollPane jsp = new JScrollPane(jta);//new一个滚动条
		jsp.setBounds(40, 230, 600, 400);
		jta.setVisible(true);
		panel.add(jsp);//放入文本框(当加入滚动条后，加入的就不在是文本框了)
	    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				
		//绘图按钮
	    JButton jb_paint = new JButton("绘制图像");
		jb_paint.setFont(new Font("黑体",Font.BOLD,20));
		jb_paint.setBounds(50, 650, 150, 40);
		panel.add(jb_paint);
		jb_paint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 LineChart lc = new LineChart();
				 lc.column = column;
				 lc.createDataset(n,day_arr, month_arr, year_arr,price_arr);
				lc.DrawChart();//画折线图
			}
		});
		
		frame.setVisible(true);
		
		//建立连接
		Connection();
		
		//接收消息
		jb_select.addActionListener(new PostInformation());
		
		//开启多线程接收消息
		new Thread(new getReceive()).start();
	}

	
	//connection
	public void Connection(){
		try {
			Socket socket = new Socket("127.0.0.1",100);
			System.out.println("客户端已创建连接!");
			
			//准备接收消息
            in = socket.getInputStream();
            Din = new DataInputStream(in);

            //准备发送消息
            out = socket.getOutputStream();
            Dout = new DataOutputStream(out);     
			
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	//发送消息给服务器端
	public class PostInformation implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
        		
        		String st_year = (String)jcb_year1.getSelectedItem();
        		String st_month = (String)jcb_month1.getSelectedItem();
        		String st_day = (String)jcb_day1.getSelectedItem();
        		String end_year = (String)jcb_year2.getSelectedItem();
        		String end_month = (String)jcb_month2.getSelectedItem();
        		String end_day = (String)jcb_day2.getSelectedItem();
        		
        		column = (String)jcb_data.getSelectedItem();
        		start_date = st_year+'-'+st_month+'-'+st_day+" 00:00:00.000";
        		end_date = end_year+'-'+end_month+'-'+end_day+" 00:00:00.000";    
        		
               try {
                      Dout.writeUTF(column + "|" + start_date + "|" + end_date);  
//                      jta.append("已成功发送!\n");
               } catch (IOException e1) {           
                      try {
                             Dout.close();
                             out.close();
                      } catch (IOException e2) {
                             e2.printStackTrace();
                      }
               }
        }

   
 }

	//开多线程接收消息，防止阻塞
	public class getReceive implements Runnable{
        @Override
        public void run() { 
        		while(true){   					
        			try {
        				//接收服务器消息
        				
                             	String content;
                             	content = Din.readUTF();

                      //在文本区显示接收的消息	
                             		jta.append(content+"\n");
                             		
                             		//获取数据
                             		int length = content.length();
                             		int zero = content.indexOf(":");
                             		int one = content.indexOf(" ");
                             		date = content.substring(zero+1, one);
                             		//System.out.println(date);
                             		
                             		
                             		int zero1 =content.indexOf(": ");
                             		price =content.substring(zero1+2, length);
                             		//System.out.println(price);
                             		price_arr.add(Float.parseFloat(price));
                             		
                             		String column0 = content.substring(one+1, zero1);
                             		int a = column0.indexOf(" ");
                             		int b = column0.length();
                             		column = column0.substring(a+1,b);
                             		//System.out.println(column);
                             		
                             		int len1 = date.length();
                  				     int one1 = date.indexOf('-');
                  				     int year = Integer.parseInt(date.substring(0,one1));
                  				   //System.out.println(year);
                  				     year_arr.add(year) ;
                  				     
                  				     date = date.substring(one1+1,len1);
                  				     int two1 = date.indexOf('-');
                  				     int month = Integer.parseInt(date.substring(0, two1));
                  				   //System.out.println(month);
                  				     month_arr.add(month);
                  				     
                  				     len1 = date.length();
                  				     int day = Integer.parseInt(date.substring(two1+1, len1));
                  				   //System.out.println(day);
                  				     day_arr.add(day);               				     
                             	
               				     n++;
        			}catch (Exception e) {
        				try {
                             	Din.close();
                             	in.close();
                             	//System.out.println("本次连接正常关闭!");
        				} catch (Exception b) {
        					
        				}
        			}
        		}                   
        }
	
}

//	@Override
//	public void run() {
//		// TODO 自动生成的方法存根
//		Connection();
//	}

	
}
