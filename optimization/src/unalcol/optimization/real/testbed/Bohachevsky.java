package unalcol.optimization.real.testbed;

import unalcol.optimization.*;

/**
 * <p>Title:  Bohachevsky</p>
 * <p>Description: The Bohachevsky function</p>
 * Copyright: Copyright (c) 2010</p>
 * <p>Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Bohachevsky extends OptimizationFunction<double[]> {
  /**
   * True if it is the first Bohachevsky function , false if it is the second
   */
  public boolean one;
/**
 * Constructor: Creates a Bohachevsky function
 * @param _one True if it is the Bohachevsky I function, false if it is the Bohachevsky II function
 */
  public Bohachevsky( boolean _one ){}

  /**
   * Evaluates the Bohachevsky I function over two real values
   * @param x1 the first real value argument of the Bohachevsky function
   * @param x2 the second real value argument of the Bohachevsky function
   * @return the Bohachevsky value for the given values
   */
  public double evalI( double x1, double x2 ){
    return ( x1*x1 - 2*x2*x2 - 0.3*Math.cos(3.0*Math.PI*x1)
                   - 0.4*Math.cos(4.0*Math.PI*x2) + 0.7 );
  }

  /**
   * Evaluates the Bohachevsky II function over two real values
   * @param x1 the first real value argument of the Bohachevsky function
   * @param x2 the second real value argument of the Bohachevsky function
   * @return the Bohachevsky value for the given values
   */
  public double evalII( double x1, double x2 ){
    return ( x1*x1 + 2*x2*x2 - 0.12*Math.cos(3.0*Math.PI*x1)*Math.cos(4.0*Math.PI*x2) + 0.3 );
  }


  /**
   * Evaluate the OptimizationFunction function over the real vector given
   * @param x Real vector to be evaluated
   * @return the OptimizationFunction function over the real vector
   */
  public Double apply( double[] x ){
    double f = 0.0;
    int n = x.length - 1;
    if( one ){
      for( int i=0; i<n; i++ ){
        f += evalI( x[i], x[i+1] );
      }
    }else{
      for( int i=0; i<n; i++ ){
        f += evalII( x[i], x[i+1] );
      }
    }
    return f;
  }
}
