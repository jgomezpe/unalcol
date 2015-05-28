package unalcol.optimization.real.testbed;


/**
 * <p>Title: M2 </p>
 * <p>Description: M2 Function as defined by De Jong</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class M2 extends M1 {
  /**
   * Evaluates the OptimizationFunction function in the given real value
   * @param x value used for evaluating the OptimizationFunction function
   * @return The OptimizationFunction function value for the given value
   */
  public double apply( double x){
    double v = super.apply(x);
    double y = (x-0.1)/0.8;
    return Math.exp(-2.0*0.69314718*y*y)*v;
  }

}
