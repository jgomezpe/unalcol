/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import unalcol.math.algebra.Scale;

/**
 *
 * @author jgomez
 */
public class SparseRealVectorSphereNormalization extends Scale<SparseRealVector>{
    protected SparseRealVectorDotProduct dot = new SparseRealVectorDotProduct();
    protected SparseRealVectorSpace scale = new SparseRealVectorSpace();
  /**
   * Applies the transformation on the data record
   * @param x Data record to be transformed
   */
  public SparseRealVector fastApply(SparseRealVector x) {
    double d = dot.norm(x);
    scale.fastDivide(x, d);
    return x;
  }
}

