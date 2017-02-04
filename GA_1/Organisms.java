
package GA_1;
//run genomes 
//return results
//may have multiple evaluatable results for different elements of the organism

import GA_1.Genome;


public class Organisms {
	
	public Genome genome;
	
	int GenomeSize;
	
	public Organisms(int GenomeSize, int GeneSize){
		this.GenomeSize = GenomeSize;
		this.genome = new Genome(GenomeSize,GeneSize);
	}
	
	public Organisms(int GenomeSize){        // use this if you want to individually define every GeneSize later
		this.GenomeSize = GenomeSize;
		
		this.genome = new Genome(GenomeSize);
	}


}