package Graphing;

import java.io.Serializable;

public class GraphableDataInfo implements Serializable
{


private static final long serialVersionUID = 1L;
private String title;
private String XLabel;
private String YLabel;
	
	public GraphableDataInfo(String title, String XLabel, String YLabel)  
	{
		this.title=title;
		this.XLabel=XLabel;
		this.YLabel=YLabel;
	}
	public String getTitle(){
		return title;
	}
	public String getXLabel(){
		return XLabel;
	}
	public String getYLabel(){
		return YLabel;
	}
	// Build Strings of Graph data information	
	// ******************************************************
	public String makeString(){
		String title = getTitle();
		String xlabel = getXLabel();
		String ylabel = getYLabel();
		String GraphableDataInfoString = ("<" + title + "," + xlabel + "," + ylabel + ">");
		return GraphableDataInfoString;
	}
}
