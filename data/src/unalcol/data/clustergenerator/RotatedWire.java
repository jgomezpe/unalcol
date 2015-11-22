package unalcol.data.clustergenerator;


/**
 * <p>Title: RotatedWire</p>
 * <p>Description: A rotated wire object.</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */

public class RotatedWire extends Wire {

  /**
   * Shape been rotated
   */
  protected Wire shape = null;
  /**
   * Rotation center
   */
  protected double[] center = null;
  /**
   * Rotation angle
   */
  protected double angle = 0.0;

  /**
   * Creates a rotated wire shape
   * @param _shape Shape been rotated
   * @param _center Rotation center
   * @param _angle Rotation angle
   */
  public RotatedWire (Wire _shape, double[] _center, double _angle) {
    shape = _shape;
    center = _center;
    angle = _angle;
  }

  /**
   * Sets the restriction
   * @param _positive Determines which condition is been applied:
   * true indicates that the restriction is g(x) >= 0
   * false indicates that the restriction is g(x) <= 0
   * @param _shape Shape been rotated
   * @param _center Rotation center
   * @param _angle Rotation angle
   */
  public RotatedWire( boolean _positive,
                      Wire _shape, double[] _center, double _angle  ){
    super( _positive );
    shape = _shape;
    center = _center;
    angle = _angle;
  }

  /**
   * Rotates the given point according to the rotation center and angle
   * @param p Point to be rotated
   */
  public void rotate(double[] p) {
    double a = getAngle( center, p ) + angle;
    space.fastMinus(p, center);
    double r = Math.sqrt(p[0] * p[0] + p[1] * p[1]);
    p[0] = r * Math.cos(a);
    p[1] = r * Math.sin(a);
    space.fastPlus(p, center);
  }

  /**
   * Returns the point in the wiredshape at position t, 0<=t<=1. Because the
   * figure is wire, there is a possible parametrization of the points that define the
   * wire.
   * @param t "index" of the point to be retrieved
   * @return The point at position t
   */
  public double[] getPoint( double t ) {
    double[] p = shape.getPoint( t );
    rotate( p );
    return p;
  }

  /**
   * Generates a point following the given shape and distribution
   * @param d Distribution that follows the point generation
   * @return A point following the wire shape and the point distribution
   */
  public double[] getPoint(Distribution d) {
    double[] p = shape.getPoint(d);
    rotate(p);
    return p;
  }

  /**
   * Returns the angle of the perpendicular to the tangent in the wiredshape
   * at position t, 0<=t<=1.
   * @param t "index" of the point to be used as perperdicular-tangent
   * @return The angle of the line that is perpendicular to the tanget line at position t
   */
  public double getAngle(double t){
    return( angle + shape.getAngle(t) );
//    double a = shape.getAngle(t);
//    double[] p = shape.getPoint(t);
//    double[] q = new double[]( p );
//    double[] comp = q.getComponents();
//    comp[0] += Math.cos(a);
//    comp[1] += Math.sin(a);
//    rotate( q );
//    rotate( p );
//    return getAngle(p,q);
  }

  /**
   * Evaluates the restriction in the given point
   * @param x Point to be evaluated by the restriction
   * @return the restriction value on the given point
   */
  public double evaluate( double[] x ){
    double[] p = (double[])x.clone();
    angle *= -1.0;
    rotate( p );
    angle *= -1.0;
    return shape.evaluate( p );
  }
}
