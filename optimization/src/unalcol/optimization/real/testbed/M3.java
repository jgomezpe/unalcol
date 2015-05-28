package unalcol.optimization.real.testbed;



/**
 * <p>Title: M3 </p>
 * <p>Description: M3 Function as defined by De Jong</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class M3 extends M1 {
  /**
   * Evaluates the OptimizationFunction function in the given real value
   * @param x value used for evaluating the OptimizationFunction function
   * @return The OptimizationFunction function value for the given value
   */

  public double apply( double x) {
    x = Math.pow( x, 0.75 ) - 0.05;
    return super.apply(x);
  }
}
