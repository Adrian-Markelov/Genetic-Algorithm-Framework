package GAFrameWork;

import java.util.Random; 

import GAFrameWork.Gene;
import GAFrameWork.Genome;
public class Repopulation {


	public static Genome crossover(Genome chromosome1, Genome chromosome2){  
		Random randombool = new Random();								
		Genome smallestChromosome = null;
		int ChromsomeLength1 = chromosome1.length(); // 
		int ChromsomeLength2 = chromosome2.length();
		

		if(ChromsomeLength1<=ChromsomeLength2){
			smallestChromosome = chromosome1;
		}else{
			smallestChromosome = chromosome2;
		}
		int smallestLength = smallestChromosome.length();
		
		Boolean randomBool;
		Genome newChromosome = new Genome(smallestLength);
		for(int i = 0; i<smallestLength;i++){
			randomBool = randombool.nextBoolean();
			if(randomBool){
				newChromosome.geneArray[i] = chromosome1.geneArray[i];
			}else{
				newChromosome.geneArray[i] = chromosome2.geneArray[i];
			}	
		}
		return newChromosome;
	}
	
	public Genome crossover(Genome chromosome){  
		return chromosome;
	}
	

	public static Genome mutateGenome(Genome chromosome, double percentMutation){  
		Genome mutatedChromosome = new Genome(chromosome.length());                         
		for(int i = 0; i<chromosome.length();i++){
			mutatedChromosome.geneArray[i] = mutateGene(chromosome.geneArray[i], percentMutation); //same or random with x percent ex 70% same 30% random
		}
		return mutatedChromosome;
	}
	
	// here there is an x percent chance that the gene will mutate
	//and an x percent chance that a single nucleotide/ characteristic will mutate
	// mutation should be addition or subtraction not random flopping
	private static Gene mutateGene(Gene gene,double percentMutation){ 
		Random randomNum = new Random();                  
		Gene mutatedGene = new Gene();
		int randomNumber = randomNum.nextInt(100);
		double geneValue = 0;
		if(randomNumber > (percentMutation)){ // we want most of them to mutate so we do < instead
			boolean addOrSubtract = randomNum.nextBoolean();
			double factor = 0;
			double dGene = gene.geneValue; // double gene
			if(addOrSubtract){
				factor = (100+percentMutation)/100.0;
				geneValue = (int)(dGene*factor);
			}else{
				factor =  (100-percentMutation)/100;
				geneValue = (dGene*factor);
				}
			mutatedGene.geneValue = geneValue;
		}else{ mutatedGene = gene;}
		
		return mutatedGene;
	}

	public static void main(String arg[]){
		
		Genome chromosome1 = new Genome(10);
		
		System.out.println(chromosome1.length());
		
		
	}
	
}

