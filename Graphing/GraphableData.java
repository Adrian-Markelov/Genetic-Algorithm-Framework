package Graphing;

import java.io.Serializable;

public class GraphableData implements Serializable
{

	
	private static final long serialVersionUID = 1L;
	private double x, y, z;
	
	public GraphableData(double x,double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public GraphableData(double x,double y){
		this.x=x;
		this.y=y;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getZ(){
		return z;
	}
	
	// Build Strings of Graph data information	
		// ******************************************************
	public String makeString(){
		double x = getX();
		double y = getY();
		String GraphableDataString = ("<" + x + "," + y + ">");
		return GraphableDataString;
		}
	
}
