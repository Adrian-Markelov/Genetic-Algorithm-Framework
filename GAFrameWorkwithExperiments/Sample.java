package GAFrameWorkwithExperiments;

import java.util.ArrayList;

import Graphing.Graphing;

public class Sample extends Era{

	Sample(int numberOfGenerations, int populationSize, int selectionSize, double probabilityConstant,
			double percentMutation, int searchSpace, int numberOfGenes) {
		super(numberOfGenerations, populationSize, selectionSize, probabilityConstant, percentMutation,searchSpace, numberOfGenes);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	void evaluate(int generation){
		//super.evaluate(generation);
		//System.out.println("sample's evaluate method");
		for (int i = 0; i < this.era[generation].populationSize;i++){
			double x = this.era[generation].members[i].organism.genome.geneArray[0].geneValue;
			double y = this.era[generation].members[i].organism.genome.geneArray[1].geneValue;
			int xtran = 500;
			int ytran = 500;
			//this.era[generation].members[i].health = (1000)*Math.sin(.1*(x-1200))/(x-1200) + (1000)*Math.sin(.1*(y-200))/(y-200); 
			this.era[generation].members[i].health = (1000)*Math.sin(Math.pow((x-xtran), 2) + Math.pow((y-ytran),2))/(Math.pow((x-xtran), 2) + Math.pow((y-ytran),2));
			this.era[generation].avgHealth+= this.era[generation].members[i].health;
		}
		this.era[generation].avgHealth/=this.era[generation].populationSize;
		this.era[generation] = sortPopulation(this.era[generation]);	
	}
	

}
