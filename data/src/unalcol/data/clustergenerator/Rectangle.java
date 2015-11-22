package unalcol.data.clustergenerator;


/**
 * <p>Title: Rectangle</p>
 * <p>Description: A wired shape that is a rectangle 2D</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */

public class Rectangle extends Wire {
  /**
   * Center point
   */
  protected double[] center;
  /**
   * Dimension
   */
  protected double[] dimension;

  /**
   * Creates a Rectangle
   * @param _center Center of the rectangle
   * @param _dimension Dimension of the rectangle
   */
  public Rectangle(double[] _center, double[] _dimension) {
    center = _center;
    dimension = _dimension;
  }

  /**
   * Creates a Rectangle
   * @param _positive If the restriction is greater than or not
   * @param _center Center of the rectangle
   * @param _dimension Dimension of the rectangle
   */
  public Rectangle(boolean _positive, double[] _center, double[] _dimension) {
    super(_positive);
    center = _center;
    dimension = _dimension;
  }
  /**
   * Returns the point in the wiredshape at position t, 0<=t<=1. Because the
   * figure is wire, there is a possible parametrization of the points that define the
   * wire.
   * @param t "index" of the point to be retrieved
   * @return The point at position t
   */
  public double[] getPoint( double t ){
    double x = center[0];
    double y = center[1];
    double dx = dimension[0];
    double dy = dimension[1];
    double perimeter = 2.0 * (dx + dy);
    t *= perimeter;
    double[] p;
    if (t <= dy / 2.0) {
      p = new double[]{ x + dx / 2.0, y + t};
    } else {
      t -= dy / 2.0;
      if (t <= dx) {
        p = new double[] {x + dx / 2.0 - t, y + dy};
      } else {
        t -= dx;
        if (t <= dy) {
          p = new double[] {x - dx / 2.0, y + dy / 2.0 - t};
        } else {
          t -= dy;
          if ( t <= dx ) {
            p = new double[] {x - dx/2.0 + t, y - dy / 2.0};
          } else {
            t -= dx;
            p = new double[] {x + dx/2.0, y - dy / 2.0 + t};
          }
        }
      }
    }
    return p;
  }

  /**
   * Returns the angle of the perpendicular to the tangent in the wiredshape
   * at position t, 0<=t<=1.
   * @param t "index" of the point to be used as perperdicular-tangent
   * @return The angle of the line that is perpendicular to the tangent line at position t
   */
  public double getAngle( double t ){
    return (getAngle(center, getPoint(t)));
  }

  /**
   * Evaluates the restriction in the given point
   * <p>g(x) = (x0-c0]^2/r0^2 + (x1-c1]^2/r1^2 - 1</p>
   * <p>c is the center of the ellipse, r is the radius of the ellipse</p>
   * @param p Point to be evaluated by the restriction
   * @return the restriction value on the given point
   */
  public double evaluate(double[] p) {
    // I have to think a little for this restriction but is not so important now
    return 0.0;
  }

}
