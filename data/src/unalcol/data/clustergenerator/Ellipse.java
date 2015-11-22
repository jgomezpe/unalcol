package unalcol.data.clustergenerator;


/**
 * <p>Title: Ellipse</p>
 * <p>Description: A wired shape that is an ellipse 2D</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */

public class Ellipse extends Wire {
  /**
   * Center point
   */
  protected double[] center;
  /**
   * Radius
   */
  protected double[] radius;

  /**
   * Creates an Ellipse
   * @param _center Center of the ellipse
   * @param _radius Radius of the ellipse
   */
  public Ellipse(double[] _center, double[] _radius) {
    center = _center;
    radius = _radius;
  }

  /**
   * Creates an Ellipse
   * @param _positive If the restriction is greater than or not
   * @param _center Center of the ellipse
   * @param _radius Radius of the ellipse
   */
  public Ellipse(boolean _positive, double[] _center, double[] _radius) {
    super(_positive);
    center = _center;
    radius = _radius;
  }

  /**
   * Returns the point in the wiredshape at position t, 0<=t<=1. Because the
   * figure is wire, there is a possible parametrization of the points that define the
   * wire.
   * @param t "index" of the point to be retrieved
   * @return The point at position t
   */
  public double[] getPoint(double t) {
    double x = center[0];
    double y = center[1];
    double rx = radius[0];
    double ry = radius[1];
    double theta = 2.0 * Math.PI * t;
    x += rx*Math.cos(theta);
    y += ry*Math.sin(theta);
    return new double[]{x, y};
  }

  /**
   * Returns the angle of the perpendicular to the tangent in the wiredshape
   * at position t, 0<=t<=1.
   * @param t "index" of the point to be used as perperdicular-tangent
   * @return The angle of the line that is perpendicular to the tanget line at position t
   */
  public double getAngle( double t ){
    return( 2.0 * Math.PI * t );
  }

  /**
   * Evaluates the restriction in the given point
   * <p>g(x) = (x0-c0]^2/r0^2 + (x1-c1]^2/r1^2 - 1</p>
   * <p>c is the center of the ellipse, r is the radius of the ellipse</p>
   * @param x Point to be evaluated by the restriction
   * @return the restriction value on the given point
   */
  public double evaluate( double[] x ){
    double g =
        (x[0] - center[0]) * (x[0] - center[0]) / radius[0] / radius[0] +
        (x[1] - center[1]) * (x[1] - center[1]) / radius[1] / radius[1] - 1.0;
    return g;
  }

}
