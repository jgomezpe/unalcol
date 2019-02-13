package evolution;
import unalcol.optimization.OptimizationFunction;
import unalcol.types.collection.bitarray.BitArray;

public class GlovitoFitness extends OptimizationFunction<BitArray>{
	  /**
	   * Evaluate the max ones OptimizationFunction function over the binary array given
	   * @param x Binary Array to be evaluated
	   * @return the OptimizationFunction function over the binary array
	   */
	  public Double compute( BitArray a ){
			int[] input = new int[]{1,1,0,1,1,0,0,0,1,1,0};
			Glovito g = new Glovito(a);
			int[] x = g.simulate(input);
			double f = 0.0;
			for( int i=0; i<input.length-1; i++){
				f += x[i]==input[i+1]?1:0;
			}
			return f;		  
	  }

}
