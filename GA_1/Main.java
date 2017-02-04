package GA_1;

import GA_1.lineFinderGenerations;

import GA_1.SimpleLineSpecies;



public class Main {

	//NOTES***********
		
		// Have control here over number of possible generations and 
		//number of organisms in each generation
		// the higher the population in generation the faster it solves the problem
		// Also in the selection function you have control over the number of organisms
		// you use and save for the next generation the smaller this is the better in some cases
		// finally you have control over the MATHIMATICAL FUNCTION which is in the evaluation function
		// ****KEY Variable
		//------int funcVal/ spacial boundries --> this is also specified as the area in space which you are looking for (includes health variable)
		//------int numberOfGenerations
		//------int populationSize 
		//------int selectionSize
		//------int binGraphSize  --> this creates a domain/ range of 2^(binGraphSize) ex. if 10 then 2^10 is 1024
		
		
		
		//******check if mutations are working properly and selection seems like 
	
		// consider adding a setup GUI that opens up just for a little and asks some questions then clases everything in itself so save memory
	
	    // NOTE when GA gets closs to it's target if the target is very small then the precent mutation needs to be very!!! small because
		// it is based off of a MULTIPLIED factor when it gets close it will correct itself too much and bounce past and behind the target
		// but it its too small that it will never change because it will always be rounded to the nearest integer
		// also depends what number it starts at if it randomly starts far away the numbers are big and
		//the mutations have no impact on them due to int rounding, but if they start closs the "percent mutation" impacts
		// the numbers and solved the problem assumming the mutation is small enough to target to goal.
		// this proves that dynamic mutation is necessary to solve any kind of problem
		// good news though every generation is always learning when you look at the print data of the health even if it only continuously by 1 or 2
	
		// int time is x variable and mutation should increase with time passing 
		
		public static void main(String arg[]){
			
			int numberOfGenerations = 10000;
			int populationSize = 40;
			int selectionSize = 10;
			double percentMutation = 99.6;
			int tolerance = 1;
			int time = 0;
			
			
			lineFinderGenerations[] Generation = new lineFinderGenerations[numberOfGenerations]; // x possible generations
			
			int count = 0;// this initialization population could be done in a func to clean up space
			Generation[count] = new lineFinderGenerations(populationSize);// must initialize firstGen for problemNotSolved() to work
			
	
			while (Generation[count].problemNotSolved() && count < (numberOfGenerations-1)){	
				System.out.print("**********************  past generation: "+ count + " *****************");
				Generation[count].printGenerationSum();
				count++;
				Generation[count] = new lineFinderGenerations(populationSize);
				  //**************** NOT TAKING FROM THE LAST GENERATION JUST CREATE NEW GENS WITH NEW RANDOM DATA
					// ALSO WE ARE RUNNING EACH GENERATION BEFORE IT IS ACTUALLY BUILT******!!!!!!!
				Generation[count].lineFinderPopulation = SimpleLineSpecies.createNewPop(SimpleLineSpecies.select(Generation[count-1].lineFinderPopulation,selectionSize),populationSize,percentMutation);// create new population of 20 memb using the selected members from last gen
				for(int i = 1; i< Generation[count].lineFinderPopulation.length;i++){
					Generation[count].lineFinderPopulation[i].evaluate();//Generation[count].lineFinderPopulation[i-1]);
					//Generation[count].lineFinderPopulation[i].evaluate(Generation[count].lineFinderPopulation[i-1]);
					time++;
				}
				
			}
			
			int actualGenerationNum = count;
			
			int xCord = SimpleLineSpecies.booleansToInt(Generation[actualGenerationNum].lineFinderPopulation[Generation[actualGenerationNum].successIndex].lineFinder.genome.BinaryGenome[0].BinaryString);
			int yCord = SimpleLineSpecies.booleansToInt(Generation[actualGenerationNum].lineFinderPopulation[Generation[actualGenerationNum].successIndex].lineFinder.genome.BinaryGenome[1].BinaryString);

			if(Generation[actualGenerationNum].lineFinderPopulation[Generation[actualGenerationNum].successIndex].success){
				System.out.println("GA SOLVED THE PROBLEM");
				System.out.println("Done lineFinder Found: (" + xCord +", "+ yCord + ")");
				System.out.println("Generation number: " + actualGenerationNum);
				System.out.println("Organism number: " + Generation[actualGenerationNum].successIndex);
				
			}else{
				System.out.println("GA DID NOT SOLVE PROBLEM NEED MORE GENERATIONS TO SOLVE PROBLEM");
				System.out.println("Best Pair found is: (" + xCord +", "+ yCord + ")");
				System.out.println("Generation number: " + actualGenerationNum);
				}
				
		}
		
}
