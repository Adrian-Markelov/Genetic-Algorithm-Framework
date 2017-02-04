package annFramework;

import java.util.ArrayList;

import GAFrameWork.*;

public class Network_1 extends Era{

	Network XNORNET;
	TrainingData tData;
	
	double[] instanceHealth;
	double averagedHealth;
	
	Network_1(Network XNORNET, int numberOfGenerations, int populationSize, int selectionSize, double probabilityConstant,
			double percentMutation, int searchSpace, int numberOfGenes) {
		super(numberOfGenerations, populationSize, selectionSize, probabilityConstant, percentMutation, searchSpace,
				numberOfGenes);
		this.XNORNET = XNORNET;
		
		this.tData = new TrainingData();
		instanceHealth = new double[4];
	}
	
	public class TrainingData{
		
		TrainingData(){
			inputData.add(set1);
			inputData.add(set2);
			inputData.add(set3);
			inputData.add(set4);
		}
		
		ArrayList<double[]> inputData = new ArrayList<double[]>();
		double[] solutions = {1,0,0,1};
		
		double[] set1 = {0,0};
		double[] set2 = {0,1};
		double[] set3 = {1,0};
		double[] set4 = {1,1};
		int trainingLength = 4;
	}
	
	

	protected void evaluate(int generation){
	
		
		// Evaluation takes all 14 genes and populated network starting top left then going down then top + right++
		// then runs the network
		// finally returns the health of each organism in the population
		for (int memberIndex = 0; memberIndex < this.era[generation].populationSize; memberIndex++){
			int geneCount = 0;
			for(int i = 0; i < XNORNET.network.length;i++){
				for(int j = 0; j < this.XNORNET.network[i].layerSize;j++){
					for(int k = 0; k < this.XNORNET.network[i].prevLayer.layerSize; k++){
						this.XNORNET.network[i].weightsMatrix[j][k] = this.era[generation].members[memberIndex].organism.genome.geneArray[geneCount].geneValue;
						geneCount++;
					}
				}
			}
			// assign health to every organism network based on training data
			// iterate through training data as a comparator between random weights and proven IO results 
			// health =  1 / (trainingData - networkResults)^2
			/*
			 *  [0,0] --> 1 - result
			 *  [0,1] --> 0 - result
			 *  [1,0] --> 0 - result
			 *  [1,1] --> 1 - result
			 */
			
			for(int trainingDuration = 1; trainingDuration <= tData.trainingLength; trainingDuration++){
				this.XNORNET.runNetwork(tData.inputData.get(trainingDuration));
				instanceHealth[trainingDuration-1] = 1/(Math.pow((tData.solutions[trainingDuration]-this.XNORNET.network[XNORNET.network.length].layerVector[0].value), 2));
				averagedHealth+=instanceHealth[trainingDuration-1];
			}
			averagedHealth/=tData.trainingLength;
			this.era[generation].members[memberIndex].setHealth(averagedHealth);
		}	
		this.era[generation].avgHealth/=this.era[generation].populationSize;
		this.era[generation] = sortPopulation(this.era[generation]);
	}
	
	
	
	
	
	

}
