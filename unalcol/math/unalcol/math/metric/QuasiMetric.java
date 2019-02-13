package unalcol.math.metric;

/**
 * <p>Title: QuasiMetric</p>
 * <p>Description: Represents a quasimetric. A quasimetric satisfies the following conditions:</p>
 * <p>d(x,y) >= 0</p>
 * <p>d(x,x) = 0</p>
 * <p>d(x,y)=0 -> x = y</p>
 * <p>d(x,z) <= d(x,y) + d(y,z)</p>
 * <p>if it satisfies d(x,y) = d(y,x) then it is called metric</p>
 * <p>Copyright:    Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */

public interface QuasiMetric<T> {
  /**
   * Calculates the distance from one object to other. Remember in quasimetrics
   * the property d(x,y) == d(y,x) is not always true.
   * @param x The first object
   * @param y The second object
   * @return Distance from object x to object y
   */
  public double apply(T x, T y);  
}
