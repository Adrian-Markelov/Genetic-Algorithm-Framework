package GA_1;

import GA_1.Organisms;

import GA_1.lineFinderGenerations;
import GA_1.Repopulation;

import java.util.ArrayList;
import java.util.Random;


// this will define a species of organisms that try to find coordinates that are in a 
// certain area in 2D space
// ex. x=22, y = 33.... Parameter. 20<x<30  30<y<40


/*
 * possible funcs
 * \sin \left(10\sin \left(x\right)+\cos \left(x\right)\right)-x^2+20
 * y=.8\sin \left(.8x^2\right)+\left(.8\right)\cos ^2\left(8x\right)-.4\ x^2+100
 */
public class SimpleLineSpecies {

	
	// This might be a conceptual error
	// line finder should not be defined in a single object
	//because we want to create many lineFinder objects/ organisms
	// so they may evolve take whats here make it into a generic class
	// or alteast analyze what we have here
	
	int numberOfGenes = 2; // actually number later 1 is subtracted to add index 0
	
	public boolean success = false; 
	public int health;
	boolean alive;
	
	Boolean[] gene1; // now must populate each gene with a number
	Boolean[] gene2; // now must populate each gene with a number
	
	public Organisms lineFinder;
	
	public int xPoint =5;
	public int yPoint =10;
	
	
	
	public SimpleLineSpecies(){
		this.success = false;
		this.health = 10;
		this.alive = true;
		
		int binGraphSize =  11;
		
		this.lineFinder = new Organisms(this.numberOfGenes, binGraphSize);// maybe add this for bother number of genes and lineFinder
		Random randombool = new Random();
		

		for(int i = 0; i<this.lineFinder.genome.BinaryGenome[0].GeneSize;i++){
			this.lineFinder.genome.BinaryGenome[0].BinaryString[i] = randombool.nextBoolean(); // initiate randomly to increase chance of starting close
			this.lineFinder.genome.BinaryGenome[1].BinaryString[i] = randombool.nextBoolean();

		}
		//System.out.print(booleansToInt(lineFinder.genome.BinaryGenome[0].BinaryString));
		//System.out.println("   " + booleansToInt(lineFinder.genome.BinaryGenome[1].BinaryString));
	}
	
	public static int booleansToInt(Boolean[] arr){
	    int n = 0;
	    for (Boolean b : arr)
	        n = (n << 1) | (b ? 1 : 0);
	    return n;
	}
	
	 public void evaluate(){//SimpleLineSpecies lastMember){ // here we define what location to find in 3D space
			this.xPoint = booleansToInt(this.lineFinder.genome.BinaryGenome[0].BinaryString);
			this.yPoint = booleansToInt(this.lineFinder.genome.BinaryGenome[1].BinaryString);
			
			System.out.println("x and y points: (" + this.xPoint + ",   " + this.yPoint + ")");
		 
		 	int health = 0;
		if(this.yPoint > 1006 && this.yPoint <1014 && this.xPoint>1006 && this.xPoint<1014){
			System.out.println("-----Health: 0 ----->---->----------------------> "+"Success <------<-----<----<----------------------");
			this.success = true;
			//health = yPoint - funcVal;
			//lineFinderSpecies.health = health;
		}else{
			//System.out.print("Failure: ");
			int sumOfSquares = (1000-this.xPoint)*(1000-this.xPoint) + (1000-this.yPoint)*(1000-this.yPoint); // distance formula
			double distance = Math.sqrt((double)sumOfSquares);
			//System.out.println("error distance: "+ distance);
			//double percentHealth = ((1500-distance)/1500); // max distance is 1010 * root (2) = 1500
			 //health = (int)(percentHealth*100);
			health = -(int)distance;
			this.health = health;
			System.out.println("--- Health: " + health);
		}
//			this.yPoint = (int) (-Math.abs(Math.pow((this.xPoint-3), 3)-Math.pow(this.xPoint, 2)) + 50);
//			if(this.yPoint > lastMember.yPoint){
//				this.health = lastMember.health +this.yPoint-lastMember.yPoint;
//			}else{this.health = lastMember.health - (lastMember.yPoint-this.yPoint);}
			
		
	}
	
	 //selection probability determined by the weight of the health higher health has better percentage
	 // creates more diversity in population
	// need a select/ kill function***************
	public static SimpleLineSpecies[] select(SimpleLineSpecies[] lineFinderPopulation, int selectionSize){  // takes the top x # of strongest and kills the rest
		SimpleLineSpecies[] strongestPopulation = new SimpleLineSpecies[selectionSize];// top 10 will be held in here
		// do this by sorting the array by health then selecting last strongest ones
		// here this is done by the insertion algorithm eventually switch to a merge-sort algorithm (logarithmic cost growth)
		SimpleLineSpecies key = new SimpleLineSpecies();
		int k = 0;
		for(int j = 1; j<lineFinderPopulation.length;j++){ 
			key = lineFinderPopulation[j];
			//System.out.println("KEY HEALTH: " + key.health);
			k = j - 1;
			while(k>-1 && lineFinderPopulation[k].health>key.health){
				lineFinderPopulation[k+1] = lineFinderPopulation[k];
				k--;
			}
			lineFinderPopulation[k+1] = key;
		}
		//build and populate strongest population 
		for(int i = 0; i<selectionSize;i++){
			//---- commented out is an options for dynamically selecting new generations by 
			// using weights that correlate with each organisms rank. Unfortunatly this may create more diversity
			// but right now it is not working well. Maybe it's creating TOO MUCH diversity slowing down progress
//			// Sorted population is given a weight or 
//			// a percent chance of being chosen for strongest pop
//			int selectIndex = 0;
//			Random randInt = new Random();
//			for(int i = 0; i<selectionSize;i++){
//				int rank = 1;
//				boolean notSelected = true;
//				int randomNum;
//				int count = 0; 
//				while(notSelected && count < lineFinderPopulation.length){
//					randomNum = randInt.nextInt(100);
//					if(((lineFinderPopulation.length - rank)/lineFinderPopulation.length)*100 > randomNum){
//						selectIndex = rank - 1;
//						notSelected = false;
//					}
//					rank++;
//					count ++;
//				}
//				strongestPopulation[i] = new SimpleLineSpecies();
//				strongestPopulation[i] = lineFinderPopulation[selectIndex];// picks objects of the health sorted population array
//				System.out.println("health order: "+ lineFinderPopulation[i].health);
			
			strongestPopulation[i] = new SimpleLineSpecies();
			strongestPopulation[i] = lineFinderPopulation[lineFinderPopulation.length - (selectionSize-i)];// picks last 10 objects of the health sorted population array
			System.out.println("health order: "+ strongestPopulation[i].health);
		}
		
		return strongestPopulation;
	}
	
	// need a population creator --> may need to be a new class of a function
	public static SimpleLineSpecies[] createNewPop(SimpleLineSpecies[] strongestPopulation, int size, double percentMutation){// put select function into this one
		// here take the ten selected from previous and add 10 new 
		//members to create a new population of 20 members
		SimpleLineSpecies[] newPopulation = new SimpleLineSpecies[size];	
		for(int i = 0; i<strongestPopulation.length;i++){ // initilizes first half of new pop are the top 10 strongest from last generation
			newPopulation[i] = new SimpleLineSpecies();
			newPopulation[i] = strongestPopulation[i];
		}
		//System.out.println("strongest pop length: " + strongestPopulation.length);
		int sizeOfNewLineFinders = size - strongestPopulation.length;
		Random randomNum = new Random();
		for(int i = 0; i<sizeOfNewLineFinders;i++){ // populate the rest of the population with new mutated lineFinders
			int momIndex = randomNum.nextInt(strongestPopulation.length);// eventually instead of randomly selecting out of strong population...
			int dadIndex = randomNum.nextInt(strongestPopulation.length);// mating will be guided by human or mating is done of strongest of the strong
			//below this line takes 2  random lineFinders from above and breads them then mutated the child 
			newPopulation[strongestPopulation.length+i] = new SimpleLineSpecies();// must create the rest of the new population members
			newPopulation[strongestPopulation.length+i].lineFinder.genome = 
																			Repopulation.mutateGenome(Repopulation.crossover(strongestPopulation[momIndex].lineFinder.genome,
																			strongestPopulation[dadIndex].lineFinder.genome), percentMutation);
		}
		return newPopulation;
	}
	

}



