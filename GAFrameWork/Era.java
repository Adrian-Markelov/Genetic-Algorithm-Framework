package GAFrameWork;

import java.util.ArrayList;
import java.util.Random;

import GAFrameWork.Repopulation;


public class Era {

	protected Population[] era;
	
	int numberOfGenerations;
	int selectionSize;
	int populationSize;
	double probabilityConstant;
	double percentMutation;
	double healthTolerance=.01;
	double distanceTolerance=.01; 
	int searchSpace;
	int generationSuccessIndex;
	
	//for Graphing
	ArrayList generation;
	ArrayList fitness;
	ArrayList x;
	ArrayList y;
	ArrayList organisms;
	
	ArrayList<double[]> xMultipleSeries;
	ArrayList<double[]> yMultipleSeries;
	
	
	protected Era(int numberOfGenerations, int populationSize, int selectionSize, double probabilityConstant, double percentMutation, int searchSpace, int numberOfGenes){
		
		xMultipleSeries = new ArrayList<double[]>();
		yMultipleSeries = new ArrayList<double[]>();
		this.numberOfGenerations = numberOfGenerations;
		this.populationSize = populationSize;
		this.selectionSize = selectionSize;
		this.probabilityConstant = probabilityConstant;
		this.percentMutation = percentMutation;
		this.searchSpace = searchSpace;
			era = new Population[numberOfGenerations];
			for(int i = 0; i<numberOfGenerations;i++){
				era[i] = new Population(populationSize, selectionSize, probabilityConstant, numberOfGenes);
			}
			
		}
	
	
	/* 
	 * Population Functions
	 */
	static Population staticSelection(Population population){
		
		Population strongestPopulation = new Population(population);// top 10 will be held in here
				
		SpeciesMember key = new SpeciesMember(population.numberOfGenes);
		int k = 0;
		for(int j = 1; j<population.populationSize;j++){
			
			key = population.members[j];
			k = j - 1;
			while(k>-1 && population.members[k].health>key.health){
				population.members[k+1] = population.members[k];
				k--;
				}
				population.members[k+1] = key;
			}
				//build and populate strongest population 
				for(int i = 0; i<population.selectionSize;i++){
					strongestPopulation.members[i] = new SpeciesMember(population.numberOfGenes);
					strongestPopulation.members[i] = population.members[population.populationSize - (population.selectionSize-i)];// picks last 10 objects of the health sorted population array
					//System.out.println("health order: "+ strongestPopulation.members[i].health);
				}
				
		return strongestPopulation;
		
	}
		
	static Population probabilisticSelection(Population population){
		
		Population selectedPopulation = new Population(population);// top 10 will be held in here
		//Population sortedPopulation = sortPopulation(population);
		//System.out.println("sorted pop size: " + sortedPopulation.populationSize);
		
		for(int i = 0; i < population.selectionSize; i++){
			//System.out.println("probRank: " + getProbRank(population.populationSize, population.probabilityConstant));
			selectedPopulation.members[i] = population.members[getProbRank(population.populationSize, population.probabilityConstant)];
			//System.out.println(selectedPopulation.members[i].health);
		}
		
		return selectedPopulation;
	}
	
	static int getProbRank(int populationSize, double probabilityConstant){
		//System.out.println(populationSize);
		double[] relativeProbabilities = new double[populationSize];
		
		 
		for(int i = 0; i < populationSize; i++){
			relativeProbabilities[i] = probabilityFunction(i, probabilityConstant)*populationSize; // P(n) x populationSize = relative probability (out of population size)
			//System.out.println(i + ": " + probabilityFunction(i, probabilityConstant));
		}
		
		Random rand = new Random();
		int randomSelect = rand.nextInt(populationSize);
		double sum = relativeProbabilities[0]; 
		int j = 0;
		while(sum < (double)randomSelect && j++ < populationSize-1){
			sum += relativeProbabilities[j];
		}
		
		return Math.max(0, j-1);
		
	}
	
	static double probabilityFunction(int rank, double probalilityConstant){
		
		double probability =  Math.pow((1-probalilityConstant), (rank))*probalilityConstant; // P = k(1-k)^r
		
		return probability;
	}

	protected static Population sortPopulation(Population population){
		SpeciesMember key = new SpeciesMember(population.numberOfGenes);
		int k = 0;
		//System.out.println(population.populationSize);
		for(int j = 1; j<population.populationSize;j++){
			
			key = population.members[j];
			k = j - 1;
			while(k>-1 && population.members[k].health<key.health){
				population.members[k+1] = population.members[k];
				k--;
				}
				population.members[k+1] = key;
			}
		for(int x = 0; x<population.populationSize;x++){
			//System.out.println(population.members[x].health);
		}
		return population;
	}
	
	void createNextPopulation(int generation){ 
		  Random rand = new Random();
		  
		  for(int i = 0; i < this.selectionSize; i++){
			  this.era[generation].members[i] = probabilisticSelection(era[generation-1]).members[i];
			  System.out.println(this.era[generation-1].members[i].health);
		  }
		  
		  int selectionDifference = this.populationSize - this.selectionSize;
		  for(int j = 0; j < selectionDifference; j++){
			  int momIndex = rand.nextInt(selectionSize);
			  int dadIndex = rand.nextInt(selectionSize);
			  this.era[generation].members[this.selectionSize + j].organism.genome = Repopulation.mutateGenome(
					  								Repopulation.crossover(this.era[generation].members[momIndex].organism.genome, 
					  								this.era[generation].members[dadIndex].organism.genome), 
					  								this.percentMutation); 
		  }
	}
	
	void createNextPopulationOnlyChildren(int generation){ 
		  Random rand = new Random();		  
		  for(int j = 0; j < this.populationSize; j++){
			  int momIndex = rand.nextInt(selectionSize);
			  int dadIndex = rand.nextInt(selectionSize);
			  this.era[generation].members[j].organism.genome = Repopulation.mutateGenome(
					  								Repopulation.crossover(probabilisticSelection(era[generation-1]).members[momIndex].organism.genome, 
					  								probabilisticSelection(era[generation-1]).members[dadIndex].organism.genome), 
					  								this.percentMutation);
//			  this.era[generation].members[j].organism.genome = Repopulation.mutateGenome(
//						Repopulation.crossover(staticSelection(era[generation-1]).members[momIndex].organism.genome, 
//								staticSelection(era[generation-1]).members[dadIndex].organism.genome), 
//						this.percentMutation);
			  System.out.println(this.era[generation-1].members[j].health);
		  }
	}
	
	void createInitialPopulation(){

		Random rand = new Random();

		for(int i = 0; i < this.era[0].populationSize; i++){
			for(int j = 0; j < this.era[0].members[i].organism.genome.geneArray.length;j++){
				this.era[0].members[i].organism.genome.geneArray[j].geneValue = (double)rand.nextInt(this.searchSpace);
			}
		}
		
	}
	
	/*
	 * fitness function
	 */
	protected void evaluate(int generation){
		System.out.println("override this function in derived subclass to evaluate a particular problem");
	}
	
	/*
	 * 
	 * 
	 */
	boolean successDecider(int generation){
		
		double avgHealthDifference = 0;
		double avgDistanceDifference = 0;

		int selectionSize = this.era[generation].selectionSize;
		Random rand = new Random();
		
		for(int i = 1; i<selectionSize;i++){
			int randIndex1 =  rand.nextInt(selectionSize);
			int randIndex2 = rand.nextInt(selectionSize);
			double member1X = this.era[generation].members[randIndex1].organism.genome.geneArray[0].geneValue;
			double member1Y = this.era[generation].members[randIndex1].organism.genome.geneArray[1].geneValue;
			double member2X = this.era[generation].members[randIndex2].organism.genome.geneArray[0].geneValue;
			double member2Y = this.era[generation].members[randIndex2].organism.genome.geneArray[1].geneValue;
			avgHealthDifference += this.era[generation].members[randIndex1].health - this.era[generation].members[randIndex2].health;
			avgDistanceDifference += Math.sqrt(Math.pow((member2X-member1X), 2) + Math.pow((member2Y-member1Y), 2)) ;		
		}		
		avgHealthDifference /= this.era[generation].populationSize;
		avgDistanceDifference /= this.era[generation].populationSize;
		System.out.println("*********avgHealthDifference: " + avgHealthDifference);
	
		if((avgHealthDifference < this.healthTolerance) && (avgHealthDifference!=0) && (avgDistanceDifference<this.distanceTolerance)){
			this.era[generation].success = true;
			this.generationSuccessIndex = generation;
			return false;
		}else{ 
			this.generationSuccessIndex = this.numberOfGenerations;
			return true;
		}
		
	}
	
	/*
	 * Genetic algorithm control loop
	 */
	public void run(Era speciesObject){  
		speciesObject.createInitialPopulation();
		speciesObject.evaluate(0);
		int generation = 1;
		while(speciesObject.successDecider(generation-1) && (generation < speciesObject.numberOfGenerations-1)){
			createNextPopulationOnlyChildren(generation);
			speciesObject.evaluate(generation);
			addToMultipleSeriesArray(speciesObject, generation);
			generation++;
		}
		
		printResults(speciesObject, generation-1);
		createDataArrays(speciesObject);
	}
	
	/*
	 * Analysis functions
	 */
	
	static void addToMultipleSeriesArray(Era speciesObject, int generation){
		double[] x = new double[speciesObject.era[generation].populationSize];
		double[] y = new double[speciesObject.era[generation].populationSize];
		
		x[0] = 0;
		y[0] = 0; 
		x[1] = speciesObject.searchSpace;
		y[1] = speciesObject.searchSpace;
		for(int i = 2; i < speciesObject.era[generation].populationSize; i++){
			x[i] = speciesObject.era[generation].members[i-2].organism.genome.geneArray[0].geneValue;
			y[i] = speciesObject.era[generation].members[i-2].organism.genome.geneArray[1].geneValue;
		}
		
		speciesObject.xMultipleSeries.add(x);
		speciesObject.yMultipleSeries.add(y);
	}
	
	static void printResults(Era speciesObject, int generation){ 
		//System.out.println("Generation: " + generation + "  avg health: " + speciesObject.era[generation].avgHealth);
		//System.out.println(" x: " + speciesObject.era[generation].members[0].organism.genome.geneArray[0].geneValue+ " y: " + speciesObject.era[generation].members[0].organism.genome.geneArray[1].geneValue);
		speciesObject.era[generation] = sortPopulation(speciesObject.era[generation]);
		
		double  x = speciesObject.era[generation].members[0].organism.genome.geneArray[0].geneValue;
		double y = speciesObject.era[generation].members[0].organism.genome.geneArray[1].geneValue;
		double health = speciesObject.era[generation].members[0].health;
		System.out.println("best solution...");
		System.out.println("x: " + x + " y: " + y + " health: " + health);
	}
	
	static void createDataArrays(Era speciesObject){
		speciesObject.generation = new ArrayList();
		speciesObject.organisms = new ArrayList();
		speciesObject.fitness = new ArrayList();
		speciesObject.x = new ArrayList();
		speciesObject.y = new ArrayList();
		for(double i=1;i<speciesObject.generationSuccessIndex;i++){
			for(int w = 0; w < speciesObject.selectionSize;w++){
				speciesObject.generation.add(i);
				speciesObject.fitness.add(speciesObject.era[(int)i].members[w].health);
			}
			
		}
		int numberOfOrganisms = speciesObject.numberOfGenerations*speciesObject.populationSize;
		for(int j = 0; j < speciesObject.numberOfGenerations;j++){
			for (int k = 0; k < speciesObject.populationSize;k++){
				speciesObject.x.add(speciesObject.era[j].members[k].organism.genome.geneArray[0].geneValue);
				speciesObject.y.add(speciesObject.era[j].members[k].organism.genome.geneArray[1].geneValue);
			}
		}
	}
	
	public static void main(String args[]){ 
		//get prob rank test
		for(int i = 0; i < 20; i++){
			System.out.println(getProbRank(100, .99));

		}
		
		
	//	sort by health test
		int[] array = {2,1,3,1,6,3,4,8,4,9};
		int key;
		int k = 0;
		//System.out.println(population.populationSize);
		for(int j = 1; j<array.length;j++){
			
			key = array[j];
			k = j - 1;
			while(k>-1 && array[k]<key){
				array[k+1] = array[k];
				k--;
				}
				array[k+1] = key;
			}
		for (int c = 0; c<array.length; c++){
			//System.out.println(array[c]);

		}
		
		
	}
	
}
