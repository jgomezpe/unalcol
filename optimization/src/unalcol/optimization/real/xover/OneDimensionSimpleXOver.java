package unalcol.optimization.real.xover;

import unalcol.types.collection.vector.*;

import unalcol.clone.*;
import unalcol.search.space.Space;

/**
 * <p>Title: OneDimensionSimpleXOver</p>
 * <p>Description:Exchanges one component of the first individual with
 * the same component of the second individual</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class OneDimensionSimpleXOver extends SimpleXOver {
  /**
   * Default constructor
   */
  public OneDimensionSimpleXOver() {}

  /**
   * Apply the 2-ary genetic operator over the individual genomes
   * @param c1 First Individuals genome to be modified by the genetic operator
   * @param c2 Second Individuals genome to be modified by the genetic operator
   * @return extra information of the genetic operator
   */
  public Vector<double[]> generates(Space<double[]> space, double[] c1, double[] c2) {
      try {
          double[] x = (double[]) Clone.create(c1);
          double[] y = (double[]) Clone.create(c2);
          int pos = pos(x.length, y.length);
          double t = x[pos];
          x[pos] = y[pos];
          y[pos] = t;
          Vector<double[]> v = new Vector<double[]>();
          v.add(x);
          v.add(y);
          return v;
      } catch (Exception e) {
      }
      return null;
  }
}
