package unalcol.data.clustergenerator;


/**
 * <p>Title: Line</p>
 * <p>Description: A wired shape that is in straight line 2D</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */

public class Line extends Wire{
  /**
   * Start point defining the line shape
   */
  protected double[] start;
  /**
   * End point defining the line shape
   */
  protected double[] end;
  /**
   * Angle defined by the line
   */
  protected double angle=0.0;

  /**
   * Creates a Line
   * @param _start Start point defining the LineShape
   * @param _end End point defining the line shape
   */
  public Line(double[] _start, double[] _end) {
    start = _start;
    end = _end;
    angle = getAngle(start, end);
  }

  /**
   * Creates a Line
   * @param _positive If the restriction is greater than or not
   * @param _start Start point defining the Line
   * @param _end End point defining the line shape
   */
  public Line (boolean _positive, double[] _start, double[] _end) {
    super(_positive);
    start = _start;
    end = _end;
    angle = getAngle(start, end);
  }

  /**
   * Returns the point in the wiredshape at position t, 0<=t<=1. Because the
   * figure is wire, there is a possible parametrization of the points that define the
   * wire.
   * @param t "index" of the point to be retrieved
   * @return The point at position t
   */
  public double[] getPoint(double t) {
    double[] x = (double[]) start.clone();
    double[] y = (double[]) end.clone();
    space.fastMinus(y, x);
    space.fastMultiply(y, t);
    space.fastPlus(x, y);
    return x;
  }

  /**
   * Returns the angle of the perpendicular to the tangent in the wiredshape
   * at position t, 0<=t<=1.
   * @param t "index" of the point to be used as perperdicular-tangent
   * @return The angle of the line that is perpendicular to the tanget line at position t
   */
  public double getAngle(double t) {
    return (Math.PI / 2.0 + angle);
  }

  /**
   * Evaluates the restriction in the given point
   * <p>g(x) = (s0-e0]*x1 - (s1-e1]*x0 + e0*s1- e1*s0</p>
   * <p>s is the start point, e is the end point</p>
   * @param x Point to be evaluated by the restriction
   * @return the restriction value on the given point
   */
  public double evaluate(double[] x) {
    double g = (start[0] - end[0]) * x[1] -
               (start[1] - end[1]) * x[0] +
               start[1] * end[0] - end[1]*start[0];
    return g;
  }

}
