package GAFrameWork;

public class SpeciesMember {

	double health;
	int numberOfGenes;
	public Organisms organism;
	
	SpeciesMember(int numberOfGenes){
		organism = new Organisms(numberOfGenes);
		
	}
	
	public void setHealth(double health){
		this.health = health;
	}
	
}
