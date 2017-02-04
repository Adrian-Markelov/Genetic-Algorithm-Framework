package GAFrameWorkwithExperiments;

public class SpeciesMember {

	double health;
	int numberOfGenes;
	Organisms organism;
	
	SpeciesMember(int numberOfGenes){
		organism = new Organisms(numberOfGenes);
		
	}
	
}
