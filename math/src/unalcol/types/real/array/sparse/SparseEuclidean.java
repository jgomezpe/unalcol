/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import unalcol.types.real.array.sparse.SparseRealVector;

/**
 *
 * @author jgomez
 */
public class SparseEuclidean  extends SqrSparseEuclidean {
  /**
   * Calculates the Minkowski distance from one real vector to another.
   * This object does not calculate the p-root
   * @param x The first real vector
   * @param y The second real vector
   * @return Minkowski distance (without calculating the p-root from object x to object y
   */
    @Override
  public double apply(SparseRealVector x, SparseRealVector y) {
      return Math.sqrt(super.apply(x, y));
  }
}