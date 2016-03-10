package unalcol.optimization.real.xover;

import unalcol.clone.*;
import unalcol.random.raw.JavaGenerator;
import unalcol.random.raw.RawGenerator;

/**
 * <p>Title: LinearXOver</p>
 * <p>Description:Applies a linear crossover to a single component</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class OneDimensionLinearXOver extends SimpleXOver {
    protected RawGenerator rg = new JavaGenerator();
  /**
   * Default constructor
   */
  public OneDimensionLinearXOver() {
  }

  /**
   * Apply the 2-ary genetic operator over the individual genomes
   * @param c1 First Individuals genome to be modified by the genetic operator
   * @param c2 Second Individuals genome to be modified by the genetic operator
   * @return extra information of the genetic operator
   */
  @Override
  public double[][] apply(double[] c1, double[] c2) {
      try {
          double[] x = (double[]) Clone.create(c1);
          double[] y = (double[]) Clone.create(c2);
          int pos = pos(x.length, y.length);

          double alpha = rg.next();
          double alpha_1 = 1.0 - alpha;
          double tx, ty;
          tx = x[pos];
          ty = y[pos];
          x[pos] = alpha * tx + alpha_1 * ty;
          y[pos] = alpha_1 * tx + alpha * ty;
          return new double[][]{x, y}; 
      } catch (Exception e) {
      }
      return null;
  }
}
