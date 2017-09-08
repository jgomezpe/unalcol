package unalcol.optimization.real.xover;


/**
 * <p>Title: LinearXOverPerDimension</p>
 * <p>Description:Applies a linear crossover per dimension. in this case each alpha
 * is different for each component.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class GeneXOver extends LinearXOver {
  /**
   * Default constructor
   */
  public GeneXOver() {
  }

  /**
   * Apply the 2-ary genetic operator over the individual genomes
   * @param c1 First Individuals genome to be modified by the genetic operator
   * @param c2 Second Individuals genome to be modified by the genetic operator
   * @return extra information of the genetic operator
   */
  @Override
  public double[][] apply( double[] c1, double[] c2) {
      double[] x = c1.clone();
      double[] y = c2.clone();
      int min = Math.min(x.length, y.length);
      for (int i = 0; i < min; i++) {
          if (g.bool()) {
              double t = x[i];
              x[i] = y[i];
              y[i] = t;
          }
      }
      return new double[][]{x, y}; 
  }
}
