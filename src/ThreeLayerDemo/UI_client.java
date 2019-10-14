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
	
	//����
	static String data[] = {"���̼�(Ԫ)","���̼�(Ԫ)","��߼�(Ԫ)","��ͼ�(Ԫ)"};
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
	
	//���
	JComboBox jcb_year1 = new JComboBox();
	JComboBox jcb_month1 = new JComboBox();
	JComboBox jcb_day1 = new JComboBox();
	JComboBox jcb_year2 = new JComboBox();
	JComboBox jcb_month2 = new JComboBox();
	JComboBox jcb_day2 = new JComboBox();
	JComboBox jcb_data = new JComboBox();
	
	JTextArea jta = new JTextArea();
	JTextField jt_avg = new JTextField();
	
	//������Ϣ�ͷ�����Ϣ
	private InputStream in;
	private OutputStream out;
	private DataOutputStream Dout;
	private DataInputStream Din;
	
	public static void main(String[] args) {
		new UI_client();
	}
	
	//����
	public UI_client() {
		//��������
		JFrame frame = new JFrame("�ַ��������ݷ�������ϵͳ");
	    frame.setSize(700, 800);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//����
	    JPanel panel = new JPanel(); 
		frame.add(panel);
		panel.setLayout(null);
				

		//������ʼ����label
		JLabel label_date1 = new JLabel("��ѡ����ʼ����:");
		label_date1.setFont(new Font("����",Font.BOLD,25));
		label_date1.setBounds(35,25,200,25);
		panel.add(label_date1);
				
		//year
		//JComboBox jcb_year1 = new JComboBox();
		jcb_year1.setFont(new Font("����",Font.BOLD,20));
		jcb_year1.setBounds(230, 25, 80, 30);
		for(int i = 1999;i <= 2016;i++) {
			String s = Integer.toString(i);
			jcb_year1.addItem(s);
		}
		panel.add(jcb_year1);
		JLabel year1 = new JLabel("��");
		year1.setFont(new Font("����",Font.BOLD,25));
		year1.setBounds(320, 25, 40, 25);
		panel.add(year1);
			    
		//month
		//JComboBox jcb_month1 = new JComboBox();
		jcb_month1.setFont(new Font("����",Font.BOLD,20));
		jcb_month1.setBounds(360, 25, 70, 30);
		for(int i = 1;i <= 12;i++) {
			String s = Integer.toString(i);
			jcb_month1.addItem(s);
		}
		panel.add(jcb_month1);
		JLabel month1 = new JLabel("��");
		month1.setFont(new Font("����",Font.BOLD,25));
		month1.setBounds(440, 25, 40, 25);
		panel.add(month1);
				
		//day
		//JComboBox jcb_day1 = new JComboBox();
		jcb_day1.setFont(new Font("����",Font.BOLD,20));
		jcb_day1.setBounds(480, 25, 70, 30);
		for(int i = 1;i <= 31;i++) {
			String s = Integer.toString(i);
			jcb_day1.addItem(s);
		}
		panel.add(jcb_day1);
		JLabel day1 = new JLabel("��");
		day1.setFont(new Font("����",Font.BOLD,25));
		day1.setBounds(560, 25, 40, 25);
		panel.add(day1);
				
				
		//������ֹ����label
		JLabel label_date2 = new JLabel("��ѡ����ֹ����:");
		label_date2.setFont(new Font("����",Font.BOLD,25));
		label_date2.setBounds(35,70,200,25);
		panel.add(label_date2);
				
		//year
		//JComboBox jcb_year2 = new JComboBox();
		jcb_year2.setFont(new Font("����",Font.BOLD,20));
		jcb_year2.setBounds(230, 70, 80, 30);
		for(int i = 1999;i <= 2016;i++) {
			String s = Integer.toString(i);
			jcb_year2.addItem(s);
		}
		panel.add(jcb_year2);
		JLabel year2 = new JLabel("��");
		year2.setFont(new Font("����",Font.BOLD,25));
		year2.setBounds(320, 70, 40, 25);
		panel.add(year2);
				
		//month
		//JComboBox jcb_month2 = new JComboBox();
		jcb_month2.setFont(new Font("����",Font.BOLD,20));
		jcb_month2.setBounds(360, 70, 70, 30);
		for(int i = 1;i <= 12;i++) {
			String s = Integer.toString(i);
			jcb_month2.addItem(s);
		}
		panel.add(jcb_month2);
		JLabel month2 = new JLabel("��");
		month2.setFont(new Font("����",Font.BOLD,25));
		month2.setBounds(440, 70, 40, 25);
		panel.add(month2);
					
		//day
		//JComboBox jcb_day2 = new JComboBox();
		jcb_day2.setFont(new Font("����",Font.BOLD,20));
		jcb_day2.setBounds(480, 70, 70, 30);
		for(int i = 1;i <= 31;i++) {
			String s = Integer.toString(i);
			jcb_day2.addItem(s);
		}
		panel.add(jcb_day2);
		JLabel day2 = new JLabel("��");
		day2.setFont(new Font("����",Font.BOLD,25));
		day2.setBounds(560, 70, 40, 25);
		panel.add(day2);
				
		//data
		JLabel label_data = new JLabel("��ѡ��������:");
		label_data.setFont(new Font("����",Font.BOLD,25));
		label_data.setBounds(50,120,200,25);
		panel.add(label_data);
		//JComboBox jcb_data = new JComboBox();
		jcb_data.setFont(new Font("����",Font.BOLD,20));
		jcb_data.setBounds(230, 120, 200, 30);
		for(int i = 0;i < data.length;i++) {
			String s = data[i];
			jcb_data.addItem(s);
		}
		panel.add(jcb_data);

		//AVG
		JButton jb_calculate = new JButton("����ƽ��ֵ");
		jb_calculate.setFont(new Font("����",Font.BOLD,20));
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
		jt_avg.setFont(new Font("����",Font.BOLD,20));
		jt_avg.setBounds(220,170,200,40);
		panel.add(jt_avg);
		JLabel yuan = new JLabel("(Ԫ)");
		yuan.setFont(new Font("����",Font.BOLD,25));
		yuan.setBounds(420, 180, 70, 25);
		panel.add(yuan);
				
		//select
		JButton jb_select = new JButton("��ѯ");
		jb_select.setFont(new Font("����",Font.BOLD,20));
		jb_select.setBounds(470, 120, 150, 30);
		panel.add(jb_select);		
				
		//��ʾ��
		//JTextArea jta = new JTextArea();
		jta.setFont(new Font("����",Font.BOLD,15));
		JScrollPane jsp = new JScrollPane(jta);//newһ��������
		jsp.setBounds(40, 230, 600, 400);
		jta.setVisible(true);
		panel.add(jsp);//�����ı���(������������󣬼���ľͲ������ı�����)
	    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				
		//��ͼ��ť
	    JButton jb_paint = new JButton("����ͼ��");
		jb_paint.setFont(new Font("����",Font.BOLD,20));
		jb_paint.setBounds(50, 650, 150, 40);
		panel.add(jb_paint);
		jb_paint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 LineChart lc = new LineChart();
				 lc.column = column;
				 lc.createDataset(n,day_arr, month_arr, year_arr,price_arr);
				lc.DrawChart();//������ͼ
			}
		});
		
		frame.setVisible(true);
		
		//��������
		Connection();
		
		//������Ϣ
		jb_select.addActionListener(new PostInformation());
		
		//�������߳̽�����Ϣ
		new Thread(new getReceive()).start();
	}

	
	//connection
	public void Connection(){
		try {
			Socket socket = new Socket("127.0.0.1",100);
			System.out.println("�ͻ����Ѵ�������!");
			
			//׼��������Ϣ
            in = socket.getInputStream();
            Din = new DataInputStream(in);

            //׼��������Ϣ
            out = socket.getOutputStream();
            Dout = new DataOutputStream(out);     
			
		} catch (UnknownHostException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	
	//������Ϣ����������
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
//                      jta.append("�ѳɹ�����!\n");
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

	//�����߳̽�����Ϣ����ֹ����
	public class getReceive implements Runnable{
        @Override
        public void run() { 
        		while(true){   					
        			try {
        				//���շ�������Ϣ
        				
                             	String content;
                             	content = Din.readUTF();

                      //���ı�����ʾ���յ���Ϣ	
                             		jta.append(content+"\n");
                             		
                             		//��ȡ����
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
                             	//System.out.println("�������������ر�!");
        				} catch (Exception b) {
        					
        				}
        			}
        		}                   
        }
	
}

//	@Override
//	public void run() {
//		// TODO �Զ����ɵķ������
//		Connection();
//	}

	
}
