package annFramework;

import annFramework.Network_1;
import Graphing.*;

 public class network_1_Main {

	
public static void main(String arg[]){
		
	
		/* 
		 * Neural Network
		 */
	
		int networkSize = 4;
		double[] xnorInputData = {0,1};
		int[] hiddenLayerSizes = {3,2};
		int outputLayerSize = 1;
	
		
		Network XNORNET = new Network(networkSize);
		XNORNET.buildInputLayerValues(xnorInputData);
		XNORNET.buildHiddenLayers(hiddenLayerSizes);
		XNORNET.buildOutputLayer(outputLayerSize);
		
		
		/*
		 * GA
		 */
		int numberOfGenerations = 1000;
		int populationSize = 100;
		int selectionSize = 30;
		double probabilityConstant = .1;
		double percentMutation = .01;
		int searchSpace = 2;
		int numberOfGenes = 14;
		

		
		Network_1 Network_1Object = new Network_1(XNORNET, numberOfGenerations, populationSize, selectionSize, probabilityConstant, percentMutation, searchSpace, numberOfGenes);
		
		
		Network_1Object.run(Network_1Object);
		

	}
	
}
