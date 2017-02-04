package annFramework;
import java.util.Vector;

import annFramework.Layer;

public class Network {
	
	//Layer inputLayer;
	
	Layer[] network;
	
	Network(int numOfLayers){
		network = new Layer[numOfLayers];
		
	}
	
	
	void buildInputLayerValues(double[] inputArray){
		System.out.println("BUILDING NETWORK -------------------------------------------------------");
		this.network[0] = new Layer(inputArray);	
	}
	
	void buildHiddenLayers(int[] hiddenLayerSizes){
		//System.out.println(network.length);
		// build each of the hidden layers using the int array of sizes above
		
		for(int k = 1; k < network.length-1; k++){ // skip 0 because index 0 is made in the buildInputLayer() function	
			System.out.println("nueron Layer: " + k);
			this.network[k] = new Layer(this.network[k-1], hiddenLayerSizes[k-1]); // k - 1 is for inputing the previous layer for the connections
		}
		
	}
	
	void buildOutputLayer(int outputLayerSize){
		System.out.println("nueron Layer (output): " + (network.length-1));
		network[network.length-1] = new Layer(network[network.length-2], outputLayerSize);
	}
	
	void runNetwork(double[] inputData){
		System.out.println();
		System.out.println("RUNNING NETWORK -----------------------------------------------");
		
		for(int k = 0; k < inputData.length;k++){
			this.network[0].layerVector[k] = new Neuron();
			this.network[0].layerVector[k].value = inputData[k];
		}
		
		for(int k = 1; k < network.length - 1; k++){ // run through hidden layers			
			System.out.println("run/ feedforward layer: " + k);
			network[k].run(network[k-1]);
		}
		System.out.println("run/ feedforward OUTPUTlayer: " + (network.length - 1));
		network[network.length-1].run(network[network.length-2]); // run the output layer
		
	}
	
	public void interpretOutputLayer(){
		
		/*// might want to make this function a part of the interface
		 * Takes the array of numbers from the output array and interpretes them into 
		 * useful information. For example if the output can only be a 0 or a 1 
		 * then this function will 
		 */
	}
	
	
	
}
