package unalcol.testbed.optimization.real.basic;

/**
 * <p>Title:  Bohachevsky</p>
 * <p>Description: The Bohachevsky function</p>
 * Copyright: Copyright (c) 2010</p>
 * <p>Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Bohachevsky extends BasicFunction {
	/**
	 * Evaluates the Bohachevsky I function over two real values
	 * @param x1 the first real value argument of the Bohachevsky function
	 * @param x2 the second real value argument of the Bohachevsky function
	 * @return the Bohachevsky value for the given values
	 */
	public double eval( double x1, double x2 ){
		return ( x1*x1 - 2*x2*x2 - 0.3*Math.cos(3.0*Math.PI*x1) - 0.4*Math.cos(4.0*Math.PI*x2) + 0.7 );
	}

	/**
	 * Evaluate the OptimizationFunction function over the real vector given
	 * @param x Real vector to be evaluated
	 * @return the OptimizationFunction function over the real vector
	 */
	public Double compute( double[] x ){
		double f = 0.0;
		int n = x.length - 1;
		for( int i=0; i<n; i++ ) f += eval( x[i], x[i+1] );
		return f;
	}

	@Override
	public double limit() { return 100; }
}