package unalcol.optimization.real.testbed;

import unalcol.optimization.*;

/**
 * <p>Title:  Rastrigin</p>
 * <p>Description: The Rastrigin function</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Rastrigin extends OptimizationFunction<double[]> {

/**
 * Constructor: Creates a Rastrigin function Variables should be in the [-5.12, 5.12] interval
 */
  public Rastrigin(){}

  /**
   * Evaluates the Rastrigin function over a real value
   * @param x the real value argument of the Rastrigin function
   * @return the Rastrigin value for the given value
   */
  public double apply( double x ){
    return ( x*x - 10.0*Math.cos(2.0*Math.PI*x) );
  }

  /**
   * Evaluate the OptimizationFunction function over the real vector given
   * @param x Real vector to be evaluated
   * @return the OptimizationFunction function over the real vector
   */
  public Double apply( double[] x ){
    int n = x.length;
    double f = 0.0;
    for( int i=0; i<n; i++ ){
      f += apply(x[i]);
    }
    return (10.0*n + f);
  }
}
