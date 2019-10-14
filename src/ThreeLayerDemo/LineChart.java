package ThreeLayerDemo;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;

public class LineChart {
	
	ChartPanel frame1;
	
	//数据

    String column = " ";
	float price;
	String date;

	static int time = 0;
	//private TimeSeries timeseries = new TimeSeries(column,Day.class);
	
    TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
    
	public void DrawChart() {
		XYDataset xydataset = timeseriescollection;
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("浦发银行", "日期", "价格",xydataset, true, true, true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MMM-dd"));
        frame1=new ChartPanel(jfreechart,true);
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=xyplot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
        
        JFrame frame=new JFrame("Java数据统计图");
		frame.setLayout(new GridLayout(1, 1));
		frame.add(frame1);
		frame.setBounds(50, 50, 800, 600);
		frame.setVisible(true);
		
//		try {
//			ChartUtilities.saveChartAsPNG(new File("C:\\picture.png"), jfreechart, 800, 600);
//		} catch (IOException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
	}
	
	public LineChart() {
		
	}

	public XYDataset createDataset(int n,ArrayList<Integer> day,ArrayList<Integer> month,ArrayList<Integer> year,ArrayList<Float> price) {
		TimeSeries timeseries = new TimeSeries(column,Day.class);
		for(int i = 0;i < n;i++) {
			 timeseries.add(new Day(day.get(i),month.get(i),year.get(i)),price.get(i));
		}
        timeseriescollection.addSeries(timeseries);
        return timeseriescollection;
	}
	
	public ChartPanel getChartPanel(){
    	return frame1;	
    }
	
}
