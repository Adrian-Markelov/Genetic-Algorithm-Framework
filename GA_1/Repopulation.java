package GA_1;

import GA_1.Genome;

import GA_1.Gene;
import java.util.Random;
public class Repopulation {

	// overload  higher level sex user desired sex
	// ex. small desired gene in a bad crowd of genes
	public static Genome crossover(Genome chromosome1, Genome chromosome2){  
		Random randombool = new Random();								
		Genome smallestChromosome = null;
		int ChromsomeLength1 = chromosome1.length(); // 
		int ChromsomeLength2 = chromosome2.length();
		//System.out.println(chromosome1.length());
		//System.out.println(chromosome2.length());

		if(ChromsomeLength1<=ChromsomeLength2){
			smallestChromosome = chromosome1;
		}else{
			smallestChromosome = chromosome2;
		}
		int smallestLength = smallestChromosome.length();
		//System.out.println(smallestLength);
		
		Boolean randomBool;
		Genome newChromosome = new Genome(smallestLength);
		for(int i = 0; i<smallestLength;i++){
			randomBool = randombool.nextBoolean();
			if(randomBool){
				newChromosome.BinaryGenome[i] = chromosome1.BinaryGenome[i];
			}else{
				newChromosome.BinaryGenome[i] = chromosome2.BinaryGenome[i];
			}	
		}
		return newChromosome;
	}
	
	// asexual reproduction // maybe rename instead of overloading
	public Genome crossover(Genome chromosome){  
		return chromosome;
	}
	
	// create different types of mutation either in func of with other funcs
	// ex. soft and hard mutation
	public static Genome mutateGenome(Genome chromosome, double percentRandom){  
		Genome mutatedChromosome = new Genome(chromosome.length());                         
		for(int i = 0; i<chromosome.length();i++){
			mutatedChromosome.BinaryGenome[i] = mutateGene(chromosome.BinaryGenome[i], percentRandom); //same or random with x percent ex 70% same 30% random
		}
		return mutatedChromosome;
	}
	
	// here there is an x percent chance that the gene will mutate
	//and an x percent chance that a single nucleotide/ characteristic will mutate
	// mutation should be addition or subtraction not random flopping
	private static Gene mutateGene(Gene gene,double percentMutation){ 
		Random randomNum = new Random();                  
		Gene mutatedGene = new Gene(gene.GeneSize);
		//System.out.println(gene.GeneSize);
		int randomNumber = randomNum.nextInt(100);
		if(randomNumber < (percentMutation)){ // we want most of them to mutate so we do < instead
			int geneValue = booleansToInt(gene.BinaryString);
			boolean addOrSubtract = randomNum.nextBoolean();
			double factor = 0;
			double dGene = (double)geneValue; // double gene
			if(addOrSubtract){
				factor = (percentMutation)/100.0;
				geneValue = (int)(dGene*factor);
			}else{
				factor = 1.0 + ((100.0-(percentMutation))/100.0);
				geneValue = (int)(dGene*factor);
				}
			mutatedGene.BinaryString = intToBooleans(geneValue);
		}else{ mutatedGene = gene;}
		
		return mutatedGene;
	}

	static int booleansToInt(Boolean[] arr){
	    int n = 0;
	    for (Boolean b : arr)
	        n = (n << 1) | (b ? 1 : 0);
	    return n;
	}
	

	  public static Boolean[] intToBooleans(int b){
		  int BinarySize = 20;
		    Boolean[] binArray = new Boolean[BinarySize];
		    Boolean bin;
		    for(int i = (BinarySize-1); i >= 0; i--) {
		      if (b%2 == 1) bin = true;
		      else bin = false;
		      binArray[i] = bin;
		      b/=2;
		    }
		    return binArray;
		  }
	
	public static void main(String arg[]){
		
		Genome chromosome1 = new Genome(10);
		
		System.out.println(chromosome1.length());
		
		for(int i = 0; i<9; i++){
			//System.out.println(intToBooleans(100*i));
			chromosome1.BinaryGenome[i].BinaryString = intToBooleans(100*i);
		}
		
		Genome child;
		child = mutateGenome(chromosome1, 50);
		
		for( int i = 0; i< 10; i++){
			System.out.println("Gene " + i + ": " + booleansToInt(child.BinaryGenome[i].BinaryString) + "  non-mutate: " + 100*i);

		}
		
	}
	
}

