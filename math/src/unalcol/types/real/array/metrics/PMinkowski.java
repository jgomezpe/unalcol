package unalcol.types.real.array.metrics;

import unalcol.math.metric.*;

/**
 * <p>Title: PMinkowski</p>
 * <p>Description: Calculates the PMinkowski distance between two real vectors,
 * <p>without calculating the p-root</p>
 * <p>Copyright:    Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */
public class PMinkowski implements Distance<double[]> {
  /**
   * The PMinkowski coeficient
   */
  protected double p;
  /**
   * Creates a PMinkowski distance object with the given PMinkowski coeficient, without calculating the p-root
   * @param p PMinkowski coeficient
   */
  public PMinkowski(double p) {
    this.p = p;
  }

  /**
   * Calculates the PMinkowski distance from one real vector to another.
   * This object does not calculate the p-root
   * @param x The first real vector
   * @param y The second real vector
   * @return PMinkowski distance (without calculating the p-root from object x to object y
   */
  public double apply(double[] x, double[] y) {
    double s = 0.0;
    int n = x.length;
    for (int i = 0; i < n; i++) {
      s += Math.pow(Math.abs(x[i] - y[i]), p);
    }
    return s;
  }
  

  /**
   * Returns the PMinkowski coefficient
   * @return The PMinkowski coefficient
   */
  public double coeficient() {
    return p;
  }
  
}
