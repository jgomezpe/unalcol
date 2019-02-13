/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real.array.sparse;

import unalcol.math.algebra.Scale;

/**
 *
 * @author jgomez
 */
public class SphereNormalization implements Scale<Vector>{
    protected DotProduct dot = new DotProduct();
    protected VectorSpace scale = new VectorSpace();
  /**
   * Applies the transformation on the data record
   * @param x Data record to be transformed
   */
  public Vector fastApply(Vector x) { return scale.fastDivide(x, dot.norm(x)); }
}