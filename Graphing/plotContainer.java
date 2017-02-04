package Graphing;

import java.io.BufferedReader;   

import java.io.FileReader;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;

import Graphing.GraphableData;
import Graphing.GraphableDataInfo;


//PLOT BY GENERATIONS INDIVIDUALLY
//so it plots gen 1 waits a little then clears it 
//then it plots gen 2 waits clears ...

public class plotContainer extends ApplicationFrame // inherit all of Application frame
{
	private static final long serialVersionUID = 1L;
	String GraphableDataInfoFile = "GraphableDataInfo.txt";// name of file containing labels data 
	String GraphableDataFile = " "; // name of file containing data we will plot
	static GraphableDataInfo GDataInfo;
	static GraphableData GData; // Create an object that can hold the data from that file
	private  String title;
	private  String XLabel;
	private  String YLabel;
	ArrayList<String> dataArray;
	String fullLabelString;

	// New contructor takes care of more instatiation than 
	public plotContainer(String applicationTitle, String GraphableDataFile, String GraphableDataInfoFile)
	{
		super(applicationTitle);// Put this in the Application Frame Constructor
		this.GraphableDataFile = GraphableDataFile;
		this.GraphableDataInfoFile = GraphableDataInfoFile;
		createLabels();
		JFreeChart plot = ChartFactory.createScatterPlot(
	            this.title, // chart title
	            this.XLabel, // x axis label
	           this.YLabel, // y axis label
	            createDataset(), // data  
	            PlotOrientation.VERTICAL,
	            true, // include legend
	            true, // tooltips
	            false // urls
	            );
		//plot.addLegend(makeLegend());
		//ChartFrame frame = new ChartFrame("First", plot); // This maybe a option or... this
		ChartPanel chartPanel = new ChartPanel(plot);
		chartPanel.setPreferredSize(new java.awt.Dimension(560,367));
		setContentPane(chartPanel);
		}
	
	LegendTitle makeLegend(){
		
		String InfoString1 = " info 1 ";
		String InfoString2 = " info 2 ";

		LegendItem Item1 = new LegendItem(InfoString1);
		LegendItem Item2 = new LegendItem(InfoString2);

		LegendItemCollection f = new LegendItemCollection();

		f.add(Item1);
		f.add(Item2);
		
		//LegendItemSource ItemSource = Item1;
		
		//LegendTitle legend = new LegendTitle();
		return null;
		
	}
	

	// Take the txt file and import the strings until the file is finished
	private void ReadGraphableDataTxtFile(){  
		dataArray = new ArrayList<String>();
	       try{

	          //Create object of FileReader
	          FileReader inputFile = new FileReader(this.GraphableDataFile);

	          //Instantiate the BufferedReader Class
	          BufferedReader bufferReader = new BufferedReader(inputFile);

	          //Variable to hold the one line data
	          String line;
	        
	          // Read file line by line and print on the console
	          while ((line = bufferReader.readLine()) != null)   {
	        	dataArray.add(line);
	          }
	          //Close the buffer reader
	          bufferReader.close();
	       }catch(Exception e){
	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
	       }
		
	}
	private  void ReadGraphableDataInfoTxtFile(){  
		  try{

	          //Create object of FileReader
	          FileReader inputFile = new FileReader(this.GraphableDataInfoFile);

	          //Instantiate the BufferedReader Class
	          BufferedReader bufferReader = new BufferedReader(inputFile);

	          //Variable to hold the one line data
	          String line;
	        
	          // Read file line by line and print on the console
	          while ((line = bufferReader.readLine()) != null)   {
	        	this.fullLabelString = line;
	          }
	          //Close the buffer reader
	          bufferReader.close();
	       }catch(Exception e){
	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
	       }
	}
	private static double parseX(String data){
		int start = data.indexOf("<") + 1;
		int end = data.indexOf(",") - 1;
		String xString = data.substring(start, end);
		double x = Double.parseDouble(xString);
		return x;
	}
	private static double parseY(String data){
		int start = data.indexOf(",") + 1;
		int end = data.indexOf(">");
		String yString = data.substring(start, end);
		double y = Double.parseDouble(yString);
		return y;
	}
	private static String parseLabels(char c, String lData){
		int start =0;
		int end = 0;
		switch (c){
		case 'T':  start = lData.indexOf("<") + 1;
				   end = lData.indexOf(",");
		          return lData.substring(start,end);
		          
		case 'X':  start = lData.indexOf(",") + 1;
			       end = lData.indexOf(",",start);
	              return lData.substring(start,end);
	              
		case 'Y':  start = lData.lastIndexOf(',') + 1;
		  	       end = lData.indexOf(">");
		  	      return lData.substring(start,end);
		default:  return "Label parcer error";
		  	      
		}
	}

// Graphable Data Info 	
//	private static String title = "Scatter Plot of X_Y Data";
//	private static String XLabel = "X Data";
//	private static String YLabel = "Y Data";
	
	//uses GraphableData obj and makes it usable by JFreeChart
	private XYDataset createDataset() {
	    XYSeriesCollection result = new XYSeriesCollection();
	    XYSeries series = new XYSeries("Data");
	    ReadGraphableDataTxtFile();
	    for(int i =1; i<dataArray.size();i++){
	    	double x = parseX(dataArray.get(i));
		    double y = parseY(dataArray.get(i));
		    series.add(x, y);
	    }
	    result.addSeries(series);
	    return result;
	}
	
	private void createLabels(){	
		ReadGraphableDataInfoTxtFile();
		this.title = parseLabels('T',this.fullLabelString);
		this.XLabel = parseLabels('X',this.fullLabelString);
		this.YLabel = parseLabels('Y',this.fullLabelString);	
	}

	


	

}