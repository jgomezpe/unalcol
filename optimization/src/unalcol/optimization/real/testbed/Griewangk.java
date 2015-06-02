package unalcol.optimization.real.testbed;

import unalcol.optimization.*;

/**
 * <p>Title:  Griewangk</p>
 * <p>Description: The Griewangk function</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Griewangk extends OptimizationFunction<double[]> {

/**
 * Constructor: Creates a Griewangk function
 */
  public Griewangk(){}

  /**
   * Evaluate the OptimizationFunction function over the real vector given
   * @param x Real vector to be evaluated
   * @return the OptimizationFunction function over the real vector
   */
  public Double apply( double[] x ){
    int n = x.length;
    double sum = 0.0;
    double prod = 1.0;
    for( int i=0; i<n; i++ ){
      sum += x[i]*x[i]/4000.0;
      prod *= Math.cos(x[i]/Math.sqrt(i+1.0));
    }
    return (1.0 + sum - prod);
  }
}
