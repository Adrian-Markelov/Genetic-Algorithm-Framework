package GAFrameWork;

public class Population {

	
	public SpeciesMember[] members;
	
	boolean success = false;

	public int populationSize;
	int selectionSize;
	double probabilityConstant;
	public double avgHealth = 0;
	int numberOfGenes; // all organisms in the same population must have the same num of genes in order to mate
	
	Population(int populationSize, int selectionSize, double probabilityConstant, int numberOfGenes){
		this.probabilityConstant = probabilityConstant;
		this.populationSize = populationSize;
		this.selectionSize = selectionSize;
		this.numberOfGenes = numberOfGenes;
		//System.out.println(populationSize);
		members = new SpeciesMember[populationSize];
		for(int i = 0; i<populationSize; i++){
			
			this.members[i] = new SpeciesMember(numberOfGenes);
			
		}		
	}
	
	// This constructer is for temporarly holding the strongest population. It does not need a selection size
	Population(Population population){
		this.probabilityConstant = population.probabilityConstant;
		this.populationSize = population.populationSize;
		this.selectionSize = population.selectionSize;
		this.numberOfGenes = population.numberOfGenes;		
		members = new SpeciesMember[population.populationSize];
		for(int i = 0; i<population.populationSize; i++){
			
			this.members[i] = new SpeciesMember(population.numberOfGenes);
			
		}		
	}
	
	
	
}
