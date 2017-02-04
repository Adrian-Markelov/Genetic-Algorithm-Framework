package annFramework;

import java.util.Random;
import java.util.Vector;

public class Neuron { 
	
	
	double value = 0;
	double sum = 0;
	
	
	/*
	 * note for enn there can still be a vector
	 * for connections and weights but it will be a vector of random addresses 
	 * to other neurons not a vector of the previous layers 
	 */		
	
	
	
	static double sigmoidActivation(double sum){		
			return 1/(1+Math.pow(Math.E,-sum));
		}
	
	static double tanhActivation(double sum){		
		return (Math.pow(Math.E, 2*sum)-1)/(Math.pow(Math.E, 2*sum)+1);
		
	}
	
	
}
