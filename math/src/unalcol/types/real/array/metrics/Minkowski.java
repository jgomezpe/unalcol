package unalcol.types.real.array.metrics;



/**
 * <p>Title: PMinkowski</p>
 * <p>Description: Calculates the PMinkowski distance between two real vectors,
 * <p>without calculating the p-root</p>
 * <p>Copyright:    Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */
public class Minkowski extends PMinkowski {
  /**
   * Creates a PMinkowski distance object with the given PMinkowski coeficient, without calculating the p-root
   * @param min PMinkowski coeficient
   */
  public Minkowski(double p) {
    super( p );
  }

  /**
   * Calculates the PMinkowski distance from one real vector to another.
   * This object does not calculate the p-root
   * @param x The first real vector
   * @param y The second real vector
   * @return PMinkowski distance (without calculating the p-root from object x to object y
   */
  @Override
  public double apply(double[] x, double[] y) {
    return Math.pow(super.apply(x,y), 1.0/p);
  }

  /**
   * Determines the maximum distance in the hypercube [0,1]în
   * @param n dimension of the hypercube
   * @return maximum distance in the hypercube [0,1]în
   */
  public double max01(int n) {
    return Math.pow(n,1.0/p);
  }
}