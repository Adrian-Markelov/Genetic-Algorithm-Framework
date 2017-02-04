package Graphing;

import java.awt.BorderLayout;    
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


//references http://www.jfree.org/phpBB2/viewtopic.php?t=18917

public class multipleSeriesScrollPlot extends ApplicationFrame {

	   public multipleSeriesScrollPlot(String ApplicationTitle, String graphTitle, String xLabel, String yLabel, ArrayList xData, ArrayList yData) {
	        super(ApplicationTitle);
	        System.out.println("Hello");
	        setContentPane(new Panel(graphTitle, xLabel, yLabel, xData, yData));
	    }

	private static final long serialVersionUID = 1L;

	private static class Panel extends JPanel implements ChangeListener {
		
		private static final long serialVersionUID = 1L;
       
		private JSlider slider;
		private static int SLIDER_INITIAL_VALUE = 1;

        XYSeriesCollection SeriesCollection;
        XYSeries[] Series;
        
        private int maxXValue = 50; // what is the total range of the data in units of x (this is the max
        private int frameRange = 10;// visible range of frame in the units of x not data samplings
        private int dataSize = 0; // number of points in data
        private int numberOfSeries = 0; 
        
        private int lastSliderValue = 1;

        

        //Data Variabales
        private ArrayList<double[]> xDataArray;
        private ArrayList<double[]> yDataArray;

		private String title;
		private String XLabel;
		private String YLabel;
        
        //functions for building and updating the plot
		
		
        public Panel(String graphTitle, String xLabel, String yLabel, ArrayList<double[]> xData, ArrayList<double[]> yData) { 
            super(new BorderLayout());            
            
            this.title = graphTitle;
            this.XLabel = xLabel;
            this.YLabel = yLabel;
            this.xDataArray = xData;
            this.yDataArray = yData;
            this.dataSize = xData.get(1).length;
            this.numberOfSeries = xData.size();
            this.maxXValue = xData.size()-1;
            
            JFreeChart chart = createChart();
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
            chartPanel.setDomainZoomable(true);
            chartPanel.setRangeZoomable(true);
            Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(4, 4, 4, 4),
                BorderFactory.createEtchedBorder()
            );
            chartPanel.setBorder(border);
            add(chartPanel);

            JPanel dashboard = new JPanel(new BorderLayout());
            dashboard.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));   

            this.slider = new JSlider(0, maxXValue, SLIDER_INITIAL_VALUE);
            this.slider.addChangeListener(this);
            dashboard.add(this.slider);
            add(dashboard, BorderLayout.SOUTH);
            
        }

		private JFreeChart createChart() {        	
          
			this.SeriesCollection = createXYSeriesCollection(numberOfSeries, dataSize);
			//this.SeriesCollection.addChangeListener(this);
            JFreeChart chart = ChartFactory.createScatterPlot(
    	            this.title, // chart title
    	            this.XLabel, // x axis label
    	            this.YLabel, // y axis label
    	            this.SeriesCollection, // data  
    	            PlotOrientation.VERTICAL,
    	            true, // include legend
    	            true, // tooltips
    	            false // urls
    	            );
            
            chart.setAntiAlias(false);
            return chart;
        }

        private XYSeriesCollection createXYSeriesCollection(int numberOfSeries, int dataSize) { 
        	XYSeriesCollection SeriesCollection = new XYSeriesCollection();
        	this.Series = new XYSeries[numberOfSeries];
        	for(int i = 0; i <numberOfSeries; i++){
        		this.Series[i] = new XYSeries("series_" + Integer.toString(i));
        		for(int j = 0; j < dataSize; j++){
        			this.Series[i].add(this.xDataArray.get(i)[j], this.yDataArray.get(i)[j]);
        			//System.out.println(Series[i].getX(j));
        		}
        }
        		SeriesCollection.addSeries(this.Series[0]);
        	
            return SeriesCollection;
        }

        @Override
        public void stateChanged(ChangeEvent event) {
        	
        	//this.SeriesCollection.removeSeries(this.lastSliderValue);
        	this.SeriesCollection.removeAllSeries(); // this clears all series data we need to re-add data from double array
        	int sliderValue = this.slider.getValue();
        	this.SeriesCollection.addSeries(this.Series[sliderValue]);
        	this.lastSliderValue = sliderValue;
        	//System.out.println(sliderValue);
        	//System.out.println(this.Series[sliderValue].getX(1)); // proof data is not changing but series is changing
        }
    	
		
        
       

    }

    public static void main(String[] args) {
    	ArrayList<double[]> x = new ArrayList<double[]>();
    	ArrayList<double[]> y = new ArrayList<double[]>();
    	Random rand = new Random();
    	double[][] doublesX = new double[100][10];
    	double[][] doublesY = new double[100][10];
    	for (int i = 0; i < 100; i++){
    		for(int j = 0; j < 10; j++){
    			doublesX[i][j] = rand.nextDouble()*100;
    			doublesY[i][j] = rand.nextDouble()*100;
    			//System.out.print(doublesX[j] + "  ");
    		}
    		//System.out.println();
    		x.add(doublesX[i]);
    		y.add(doublesY[i]);
    	}
    	
    	
    	multipleSeriesScrollPlot plot = new multipleSeriesScrollPlot("Sliding Plot", "title", "x", "y", x, y);
        plot.pack();
        plot.setVisible(true);
        System.out.println("GoodBye");
    }

} 
