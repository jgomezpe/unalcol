/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real.array;

import unalcol.math.algebra.Scale;

/**
 *
 * @author jgomez
 */
public class SphereNormalization implements Scale<double[]>{
    protected DotProduct dot = new DotProduct();
    protected VectorSpace scale = new VectorSpace();
  /**
   * Applies the transformation on the data record
   * @param x Data record to be transformed
   */
  @Override  
  public double[] fastApply(double[] x) {
    double d = dot.norm(x);
    scale.fastDivide(x, d);
    return x;
  }  
}
