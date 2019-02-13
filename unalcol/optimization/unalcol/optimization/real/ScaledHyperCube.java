package unalcol.optimization.real;

import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.real.Array;
import unalcol.real.array.LinealScale;

public class ScaledHyperCube extends CodeDecodeMap<double[], double[]> {
    protected LinealScale scale = null;
	
	public ScaledHyperCube( double[] min, double[] max ){
		this.scale = new LinealScale(min, max);
	}
	
	public ScaledHyperCube( HyperCube space ){
		this.scale = new LinealScale(space.min, space.max);
	}
	
	/**
	  * Generates a thing from the given genome
	  * @param genome Genome of the thing to be expressed
	  * @return A thing expressed from the genome
	  */
	public double[] decode(double[] genome) {
		  return scale.inverse(genome); 
	}

	  /**
	   * Generates a genome from the given thing
	   * @param thing A thing expressed from the genome
	   * @return Genome of the thing
	   */
	public double[] code(double[] thing) {
		return scale.apply(thing); 
	}

	
	public static void main( String[] args){
		ScaledHyperCube scale = new ScaledHyperCube(Array.create(5, -10.0), Array.create(5, 10.0));
		for( int i=-10; i<=10; i++){
			double[] x = scale.code(Array.create(5, i));
			for( int k=0; k<x.length; k++ ){ 
				System.out.print(" "+x[k]);
			}
			System.out.println();
		}	
	}
}
