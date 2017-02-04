
package GA_1;

import GA_1.Gene;

public class Genome {

public Gene[] BinaryGenome;
	
	public Genome(int GenomeSize, int GeneSize){
		this.BinaryGenome = new Gene[GenomeSize];
		for(int i = 0; i<this.BinaryGenome.length;i++){
			this.BinaryGenome[i] = new Gene(GeneSize);
		}
	}
	
	public Genome(int GenomeSize){   // if you want to individually define GeneSize
		this.BinaryGenome = new Gene[GenomeSize];
		for(int i = 0; i<this.BinaryGenome.length;i++){
			this.BinaryGenome[i] = new Gene();
		}
	}


	public int length(){
		int length = this.BinaryGenome.length;
		return length;
	}
}