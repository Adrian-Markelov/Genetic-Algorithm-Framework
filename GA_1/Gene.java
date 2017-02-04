
package GA_1;

public class Gene {	
	public Boolean[] BinaryString;
	public int GeneSize;
	public Gene(int GeneSize){
		this.BinaryString = new Boolean[GeneSize];
		this.GeneSize = GeneSize;
	}
	
	public Gene(){}
}