package annFramework;

import java.util.Random;
import java.util.Vector;

public class Layer {

	Neuron[] layerVector; // this layers vector of neurons which each contain a value, and sum
	public int layerSize;
	Layer prevLayer;
	
	
	double[][] weightsMatrix; // [Neuron from this layer][weight to previous layer neuron]

	
	// 
	
	
	Layer(Layer prevLayer, int layerSize){
		this.layerSize = layerSize;
		this.prevLayer = prevLayer;
		this.layerVector = new Neuron[this.layerSize]; 
		this.weightsMatrix = new double[this.layerSize][this.prevLayer.layerSize + 1]; // +1 for bias neuron connection: don't actually need another neuron only need an extra connection
//		System.out.println("weight: " + this.weightsMatrix[1][1]);
//		System.out.println("layerSize: " + this.layerSize);
//		System.out.println("previous Layer size: " + prevLayer.layerSize);
		for(int k = 0; k<this.layerSize; k++){			
			this.layerVector[k] = new Neuron();	
		}
		
		// build this layers weight matrix
		Random randNum = new Random();
		//System.out.println("matrix size: " + this.weightsMatrix[0].length);
		System.out.println("weights matrix: ");
		for(int i = 0; i<layerSize; i++){
			for(int j = 0; j<prevLayer.layerSize+1; j++){
				this.weightsMatrix[i][j] = randNum.nextDouble();
				System.out.print(this.weightsMatrix[i][j] + "  ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
		
	}
	
	Layer(double[] inputLayer){
		this.layerSize = inputLayer.length;
		this.layerVector = new Neuron[inputLayer.length];
		System.out.println("nueron Layer (input):  " + 0);
		System.out.print("input vector: \n [ ");
		for(int k = 0; k < inputLayer.length;k++){
			this.layerVector[k] = new Neuron();
			this.layerVector[k].value = inputLayer[k];
			System.out.print(inputLayer[k] + " ");
		}
		System.out.println("]");
		System.out.println();
	}
	
	// ----- might just be able to re-use the generic layer constructor instead of this one
//	Layer(Layer prevLayer, Vector outputVector){
//		this.layerSize = outputVector.size();
//		this.prevLayer = prevLayer;
//		this.layerVector = new Neuron[outputVector.size()];
//		
//		this.weightsMatrix = new double[outputVector.size()][this.prevLayer.layerSize];
//		for(int k = 0; k<layerSize; k++){			
//			this.layerVector[k] = new Neuron();	
//		}
//		
//		// build this layers weight matrix
//		Random randNum = new Random();
//		for(int i = 0; i<this.layerSize; i++){
//			for(int j = 0; j<this.prevLayer.layerSize; j++){
//				this.weightsMatrix[i][j] = randNum.nextDouble();
//				System.out.print(this.weightsMatrix[i][j] + "  ");
//			}
//			System.out.println("  ");
//		}
//	}
	
	
	void run(Layer previousLayer){
		
		// L = W x V of previous layer (preactivation numbers of sums)
		
		for(int i = 0; i < this.layerSize; i++){
			System.out.print("neuron: " + i + ":--->  ");
			for(int j = 0; j < previousLayer.layerSize; j++){
				this.layerVector[i].sum += weightsMatrix[i][j] * previousLayer.layerVector[j].value ;
			}
			this.layerVector[i].sum += weightsMatrix[i][previousLayer.layerSize] * 1; // include BIAS neuron for every neuron of this layer
			
			this.layerVector[i].value = Neuron.sigmoidActivation(this.layerVector[i].sum);
			System.out.print("sum: " + this.layerVector[i].sum + "    ");
			System.out.print("value: " + this.layerVector[i].value + "  ");
		}
		System.out.println();
		System.out.println();

	}
	
}
