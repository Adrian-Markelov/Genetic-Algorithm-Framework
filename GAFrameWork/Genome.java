package GAFrameWork;

import GAFrameWork.Gene;

public class Genome {

public Gene[] geneArray;
	
	public Genome(int GenomeSize){
		this.geneArray = new Gene[GenomeSize];
		for(int i = 0; i<this.geneArray.length;i++){
			this.geneArray[i] = new Gene();
		}
	}
	



	public int length(){
		int length = this.geneArray.length;
		return length;
	}
}
