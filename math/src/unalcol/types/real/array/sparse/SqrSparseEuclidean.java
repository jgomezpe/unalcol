/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import unalcol.math.metric.Distance;
import unalcol.types.real.array.sparse.SparseRealVector;
import unalcol.types.real.array.sparse.SparseRealVectorSpace;
import unalcol.types.real.array.sparse.SparseRealVectorDotProduct;

/**
 *
 * @author jgomez
 */
public class SqrSparseEuclidean implements Distance<SparseRealVector> {
    protected SparseRealVectorDotProduct prod = new SparseRealVectorDotProduct();
    protected SparseRealVectorSpace add = new SparseRealVectorSpace();
  /**
   * Calculates the Minkowski distance from one real vector to another.
   * This object does not calculate the p-root
   * @param x The first real vector
   * @param y The second real vector
   * @return Minkowski distance (without calculating the p-root from object x to object y
   */
    @Override
  public double apply(SparseRealVector x, SparseRealVector y) {
      SparseRealVector z = add.minus(x, y);
      return prod.sqr_norm(z);
  }

}
