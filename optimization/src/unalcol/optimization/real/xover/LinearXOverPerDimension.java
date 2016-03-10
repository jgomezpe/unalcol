package unalcol.optimization.real.xover;

import unalcol.clone.*;

/**
 * <p>Title: LinearXOverPerDimension</p>
 * <p>Description:Applies a linear crossover per dimension. in this case each alpha
 * is different for each component</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class LinearXOverPerDimension extends LinearXOver{

  /**
   * Default constructor
   */
  public LinearXOverPerDimension() {}

  /**
   * Apply the 2-ary genetic operator over the individual genomes
   * @param c1 First Individuals genome to be modified by the genetic operator
   * @param c2 Second Individuals genome to be modified by the genetic operator
   * @return extra information of the genetic operator
   */
  public double[][] apply(double[] c1, double[] c2) {
      try {
          double[] x = (double[]) Clone.create(c1);
          double[] y = (double[]) Clone.create(c2);
          double a;
          double a_1;
          double tx, ty;
          int min = Math.min(x.length, y.length);
          for (int i = 0; i < min; i++) {
              a = g.next();
              a_1 = 1.0 - a;
              tx = x[i];
              ty = y[i];
              x[i] = a * tx + a_1 * ty;
              y[i] = a_1 * tx + a * ty;
          }
          return new double[][]{x, y}; 
      } catch (Exception e) {
      }
      return null;
  }
}
