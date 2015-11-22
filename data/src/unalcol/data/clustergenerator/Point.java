package unalcol.data.clustergenerator;


/**
 * <p>Title: Point</p>
 * <p>Description: A wire that is a point</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */

public class Point extends Wire {
  /**
   * Point defining the point shape
   */
  protected double[] point;

  /**
   * Creates a PointShape
   * @param _point Point defining the PointShape
   */
  public Point (double[] _point ) {
    point = _point;
  }

  /**
   * Returns the point in the wiredshape at position t, 0<=t<=1. Because the
   * figure is wire, there is a possible parametrization of the points that define the
   * wire.
   * @param t "index" of the point to be retrieved
   * @return The point at position t
   */
  public double[] getPoint(double t) {
    return (double[]) point.clone();
  }

  /**
   * Returns the angle of the perpendicular to the tangent in the wiredshape
   * at position t, 0<=t<=1.
   * @param t "index" of the point to be used as perperdicular-tangent
   * @return The angle of the line that is perpendicular to the tanget line at position t
   */
  public double getAngle(double t) {
    return (2.0 * Math.PI * t);
  }

  /**
   * Evaluates the restriction in the given point
   * <p>g(x) = distance(x,point)
   * @param x Point to be evaluated by the restriction
   * @return the restriction value on the given point
   */
  public double evaluate (double[] x) {
    return distance.apply(x, point);
  }

}
