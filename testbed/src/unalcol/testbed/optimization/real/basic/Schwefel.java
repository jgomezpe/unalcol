package unalcol.testbed.optimization.real.basic;

/**
 * <p>Title:  Schwefel [-500,500]^n </p>
 * <p>Description: The Schwefel function</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Schwefel extends BasicFunction{

	/**
	 * Constructor: Creates a Schwefel function
	 */
	public Schwefel(){}

	/**
	 * Evaluates the Schwefel function over a real value
	 * @param x the real value argument of the Schwefel function
	 * @return the Schwefel value for the given value
	 */
	public double compute( double x ){
		return ( -x * Math.sin(Math.sqrt(Math.abs(x))) );
	}

	/**
	 * Evaluate the OptimizationFunction function over the real vector given
	 * @param x Real vector to be evaluated
	 * @return the OptimizationFunction function over the real vector
	 */
	public Double compute( double[] x ){
		int n = x.length;
		double f = 0.0;
		for( int i=0; i<n; i++ ){
			f += compute(x[i]);
		}
		return (418.9829101*n + f);
	}

	@Override
	public double limit(){ return 500; }
}