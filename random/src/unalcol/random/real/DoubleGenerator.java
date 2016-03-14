package unalcol.random.real;

import unalcol.random.Random;

/**
 * <p>Abstract random number generator.</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */

public abstract class DoubleGenerator extends Random<Double>{
	/**
	 * Returns a random double number
	 * @return A random double number
	 */
	public abstract double generate();

	/**
	 * Returns a random double number
	 * @return A random double number
	 */
	@Override
	public Double next(){
		return generate();
	}

	/**
	 * Returns a set of random double numbers
	 * @param v Array where random numbers will be stored
	 * @param m The total number of random numbers
	 */
	public void generate(double[] v, int offset, int m) {
		for (int i=0; i<m; i++) v[i+offset] = this.generate();
	}

	/**
	 * Returns a set of random double numbers
	 * @param m The total number of random numbers
	 * @return A set of m random double numbers
	 */
	public double[] generate(int m) {
		double[] v = null;
		if (m > 0) {
			v = new double[m];                  
			generate( v, 0, m );
		}
		return v;
	}
  
	public abstract DoubleGenerator new_instance();
}