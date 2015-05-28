package unalcol.optimization.real.testbed;

import unalcol.optimization.*;

/**
 * <p>Title: RealFitness</p>
 * <p>Description: Abstract OptimizationFunction function for real values (one dimension)</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public abstract class RealFitness extends OptimizationFunction<Double>{

  /**
   * Evaluates the OptimizationFunction function in the given real value
   * @param x value used for evaluating the OptimizationFunction function
   * @return The OptimizationFunction function value for the given value
   */
  public abstract double apply(double x);

  /**
   * Evaluates the OptimizationFunction funtion in the first component of the RealVector
   * @param x RealVector used for sending the real value to be evaluated
   * @return The OptimizationFunction function value for the given value (first component of the RealVector)
   */
  public Double apply(Double x) {
    return this.apply( x.doubleValue() );
  }
}
