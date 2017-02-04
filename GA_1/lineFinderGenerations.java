package GA_1;


import GA_1.SimpleLineSpecies;

public class lineFinderGenerations {

	public SimpleLineSpecies[] lineFinderPopulation;
	
	int PopulationSize;
	public int successIndex;
	

	public lineFinderGenerations(int PopulationSize){
		this.PopulationSize = PopulationSize;
		this.lineFinderPopulation = new SimpleLineSpecies[this.PopulationSize];
		for(int i = 0; i<this.lineFinderPopulation.length;i++){
			this.lineFinderPopulation[i] = new SimpleLineSpecies();
		}
	}
	
	public boolean problemNotSolved(){
		
		//System.out.println(singleGeneration.lineFinderPopulation.length);
		//System.out.println(singleGeneration.lineFinderPopulation[0].success);
		for(int i = 0; i<this.lineFinderPopulation.length;i++){
			if(this.lineFinderPopulation[i].success){
				this.successIndex = i;
				return false;
				}
			}
				return true;// if entire generation has no success return false and move to nextGen	
	}
	
	public void printGenerationSum(){
		int sum = 0;
		for(int i = 0; i<this.lineFinderPopulation.length;i++){
			sum +=this.lineFinderPopulation[i].health;
		}
		System.out.println("sum of this gen health: " + sum);
	}
	
	
}
