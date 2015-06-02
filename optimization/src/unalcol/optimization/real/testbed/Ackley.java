package unalcol.optimization.real.testbed;

import unalcol.optimization.*;

/**
 * <p>Title:  Ackley</p>
 * <p>Description: The Ackley function</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Ackley extends OptimizationFunction<double[]> {

/**
 * Constructor: Creates a Ackley function
 */
  public Ackley(){}

  /**
   * Evaluate the OptimizationFunction function over the real vector given
   * @param x Real vector to be evaluated
   * @return the OptimizationFunction function over the real vector
   */
  public Double apply( double[] x ){
    int n = x.length;
    double sum1 = 0.0;
    double sum2 = 0.0;
    for( int i=0; i<n; i++ ){
      sum1 += x[i]*x[i];
      sum2 += Math.cos(2.0*Math.PI*x[i]);
    }
    sum1 /= n;
    sum2 /= n;

    return (20.0 + Math.exp(1.0) - 20.0*Math.exp(-0.2*Math.sqrt(sum1)) - Math.exp(sum2));
  }
}
