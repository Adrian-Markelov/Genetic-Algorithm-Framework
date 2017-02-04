package annFramework;

import java.util.Vector; 

import annFramework.Network;


public class Main {

	public static void main(String[] args) {
		
		/*
		 * network variables
		 */
		int networkSize = 4;
		double[] xnorInputData = {0,1};
		int[] hiddenLayerSizes = {3,2};
		int outputLayerSize = 1;
		
		/*
		 * Network setup
		 */
		Network xnorNetwork = new Network(networkSize);
		xnorNetwork.buildInputLayerValues(xnorInputData);
		xnorNetwork.buildHiddenLayers(hiddenLayerSizes);
		xnorNetwork.buildOutputLayer(outputLayerSize);
		xnorNetwork.runNetwork(xnorInputData);                 // these two are not actually part of setup only here for testing
		xnorNetwork.interpretOutputLayer();       // runNetwork should be used in the learning program loop
		
		
		/*
		 * DO LEARNING ALGORITHMS BELOW
		 */
		
	
		
		// ex.  GA.learn(xnorNetwork); // so right now I need to get the GA framework to work then implement with this xnor problem
		
		
		
		
		
		
		
		
	}

}
