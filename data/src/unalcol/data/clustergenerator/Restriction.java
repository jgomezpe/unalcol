package unalcol.data.clustergenerator;

import unalcol.math.metric.QuasiMetric;
import unalcol.types.real.array.RealVectorSpace;
import unalcol.types.real.array.metrics.Euclidean;

/**
 * <p>Title: Restriction</p>
 * <p>Description: Abstract restriction g(x) <, > 0  </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */

public abstract class Restriction {
  public static QuasiMetric<double[]> distance = new Euclidean();
  public static RealVectorSpace space = new RealVectorSpace();

  /**
   * Determines which condition is been applied:
   * true indicates that the restriction is g(x) >= 0
   * false indicates that the restriction is g(x) <= 0
   */
  public boolean positive = true;

  /**
   * Default constructor
   */
  public Restriction() {}

  /**
   * Sets the restriction
   * @param _positive Determines which condition is been applied:
   * true indicates that the restriction is g(x) >= 0
   * false indicates that the restriction is g(x) <= 0
   */
  public Restriction( boolean _positive ){  positive = _positive; }

  /**
   * Evaluates the restriction in the given point
   * @param x Point to be evaluated by the restriction
   * @return the restriction value on the given point
   */
  public abstract double evaluate( double[] x );

  /**
   * Determines if the point satisfy the restriction or not
   * @param x Point to be evaluated by the restriction
   * @return true if the point satisfy the restriction false if not.
   */
  public boolean satisfy( double[] x ){
    double g = evaluate( x );
    return ((positive&&(g >= 0)) || (!positive&&(g<=0)));
  }
}