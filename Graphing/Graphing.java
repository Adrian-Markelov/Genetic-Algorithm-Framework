package Graphing;

import java.io.FileNotFoundException;    
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import Graphing.GraphableData;
import Graphing.GraphableDataInfo;

public class Graphing {

	
	
	static void writingFile(ArrayList dataArrayX, ArrayList dataArrayY, String dataFileName, String dataInfoFileName, String xLabel, String yLabel, String title){
		
		
		// Create Files	
		// ******************************************************
			PrintWriter dataWriter = null;
			PrintWriter dataInfoWriter = null;
			try {
				dataWriter = new PrintWriter(dataFileName, "UTF-8");
				dataInfoWriter = new PrintWriter(dataInfoFileName, "UTF-8");

			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// Text Prints
			 // ******************************************************
			GraphableData gData;
			for(int i = 1; i<dataArrayX.size(); i++){
				gData = new GraphableData((double)dataArrayX.get(i), (double)dataArrayY.get(i), 1);
				dataWriter.println(gData.makeString()); // make data into string then print to file
			}
			
			GraphableDataInfo gDataInfo;
			gDataInfo = new GraphableDataInfo(title,xLabel,yLabel);
			dataInfoWriter.println(gDataInfo.makeString());
			
			
			dataWriter.close();
			dataInfoWriter.close();
		
	}
	
	
	static void PlottingGraph(String GraphableDataFile, String GraphableDataInfoFile){
		
        // create and display a frame...
        plotContainer locationChart = new plotContainer("Application", GraphableDataFile, GraphableDataInfoFile);
        locationChart.pack();
        locationChart.setVisible(true);
		}
	
	public static void writeAndPlotData(ArrayList dataArrayX, ArrayList dataArrayY, String dataFileName, String dataInfoFileName, String xLabel, String yLabel, String title){

		// Create Files	
		// ******************************************************
			PrintWriter dataWriter = null;
			PrintWriter dataInfoWriter = null;
			try {
				dataWriter = new PrintWriter(dataFileName, "UTF-8");
				dataInfoWriter = new PrintWriter(dataInfoFileName, "UTF-8");

			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// Text Prints
			 // ******************************************************
			GraphableData gData;
			for(int i = 1; i<dataArrayX.size(); i++){
				gData = new GraphableData((double)dataArrayX.get(i), (double)dataArrayY.get(i), 1);
				dataWriter.println(gData.makeString()); // make data into string then print to file
			}
			
			GraphableDataInfo gDataInfo;
			gDataInfo = new GraphableDataInfo(title,xLabel,yLabel);
			dataInfoWriter.println(gDataInfo.makeString());
			
			
			dataWriter.close();
			dataInfoWriter.close();
		
		
	        // create and display a frame...
	        plotContainer locationChart = new plotContainer("Application", dataFileName, dataInfoFileName);
	        locationChart.pack();
	        locationChart.setVisible(true);
	}
	
	public static void multipleSeriesScrollPlotter(String ApplicationTitle , String GraphTitle, String xLabel, String yLabel, ArrayList<double[]> x, ArrayList<double[]> y)
	{
		multipleSeriesScrollPlot plot = new multipleSeriesScrollPlot(ApplicationTitle, GraphTitle, xLabel, yLabel, x, y);
        plot.pack();
        plot.setVisible(true);
	}
		
	
public static void main(String args[]){
		ArrayList x = new ArrayList();
		ArrayList y = new ArrayList();
		for(double i = 1; i < 100; i++){
			x.add(i);
			y.add(i);
			
		}
		
//		Graphing.writingFile(x,y, "data.txt","info.txt", "x","y", "data");
//		
//		PlottingGraph("data.txt","info.txt");
//		PlottingGraph("data.txt","info.txt");
		
		writeAndPlotData(x,y, "data.txt","info.txt", "x","y", "data");
		
	}
	
	
	
	}
	

	
	

