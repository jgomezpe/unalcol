package unalcol.optimization.real.testbed;



/**
 *
 * <p>Title: TwoPeakTrap</p>
 * <p>Description: A two peak trap function for real values</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class TwoPeakTrap extends RealFitness{
  /**
   * Evaluates the two peak trap function 1-dimensional
   * @param x Argument for the Shubert function
   * @return The Shuberrt function evaluated on the given argument
   */
  public double apply( double x ){
    if( x < 15.0 ){
      return(160.0*(15.0-x)/15.0);
    }else{
      return(200.0*(x-15.0)/5.0);
    }
  }
}
