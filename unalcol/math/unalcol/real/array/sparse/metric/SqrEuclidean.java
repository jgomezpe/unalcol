/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real.array.sparse.metric;

import unalcol.math.metric.Distance;
import unalcol.real.array.sparse.Vector;
import unalcol.real.array.sparse.DotProduct;
import unalcol.real.array.sparse.VectorSpace;

/**
 *
 * @author jgomez
 */
public class SqrEuclidean implements Distance<Vector> {
    protected DotProduct prod = new DotProduct();
    protected VectorSpace add = new VectorSpace();
  /**
   * Calculates the Minkowski distance from one real vector to another.
   * This object does not calculate the p-root
   * @param x The first real vector
   * @param y The second real vector
   * @return Minkowski distance (without calculating the p-root from object x to object y
   */
    @Override
  public double apply(Vector x, Vector y) {
      Vector z = add.minus(x, y);
      return prod.sqr_norm(z);
  }

}
