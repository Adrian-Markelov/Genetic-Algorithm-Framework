package GAFrameWorkwithExperiments;

import Graphing.*;

public class sampleMain {

	
	public static void main(String arg[]){
		
		int numberOfGenerations = 1000;
		int populationSize = 100;
		int selectionSize = 15;
		double probabilityConstant = .3;
		double percentMutation = 1;
		int searchSpace = 1000;
		int numberOfGenes = 2;
		
		
		Sample speciesObject = new Sample(numberOfGenerations, populationSize, selectionSize, probabilityConstant, percentMutation, searchSpace, numberOfGenes);
		
		speciesObject.run(speciesObject);
		

		//need to rewrite success decider
		Graphing.writeAndPlotData(speciesObject.generation, speciesObject.fitness, "GAFrameWork2Data.txt","GAFrameWork2Labels.txt", "generation", "health", "GA");
		Graphing.writeAndPlotData(speciesObject.x, speciesObject.y, "GAFrameWork2OrgLocation.txt", "GAFrameWork2OrgLocationInfo.txt", "xaxis", "yaxis", "Organism Location");
	}
	
	
}
