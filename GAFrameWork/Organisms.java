package GAFrameWork;

import GAFrameWork.Genome;

//return results
//may have multiple evaluatable results for different elements of the organism


public class Organisms {
	
	public Genome genome;
	
	int GenomeSize;
	
	public Organisms(int GenomeSize){
		this.GenomeSize = GenomeSize;
		this.genome = new Genome(GenomeSize);
	}


}
