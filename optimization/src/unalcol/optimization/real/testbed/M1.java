package unalcol.optimization.real.testbed;

/**
 * <p>Title: M1 </p>
 * <p>Description: M1 Function as defined by De Jong</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class M1 extends RealFitness{
  /**
   * Evaluates the OptimizationFunction function in the given real value
   * @param x value used for evaluating the OptimizationFunction function
   * @return The OptimizationFunction function value for the given value
   */
  public double apply( double x ){
    return Math.pow(Math.sin(5.0*Math.PI*x), 6.0 );
  }
}
