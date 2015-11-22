package unalcol.data.clustergenerator;


/**
 * <p>Title: Bezier</p>
 * <p>Description: A wired shape that is a Cubic Bezier</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */

public class Bezier extends Wire {
  /**
   * Control points
   */
  protected double[][] control;

  /**
   * Creates a cubic Bezier
   * @param _control Control points
   */
  public Bezier(double[][] _control) {
    control = _control;
  }

  /**
   * Returns the point in the wiredshape at position t, 0<=t<=1. Because the
   * figure is wire, there is a possible parametrization of the points that define the
   * wire.
   * @param t "index" of the point to be retrieved
   * @return The point at position t
   */
  public double[] getPoint(double t) {
    double[] u = new double[4];
    double[] u_1 = new double[4];
    double[] c = new double[] { 1, 3, 3, 1 };
    u_1[3] = 1.0;
    u[0] = 1.0;
    for (int i = 1; i < 4; i++) {
      u[i] = u[i - 1] * t;
      u_1[3 - i] = u_1[4 - i] * (1.0 - t);
    }
    double[] q = new double[control[0].length];
    for (int i = 0; i < q.length; i++) {
      q[i] = 0.0;
    }

    for (int i = 0; i < 4; i++) {
      double[] p = (double[]) control[i].clone();
      space.fastMultiply(p, u[i] * u_1[i] * c[i]);
      space.fastPlus(q, p);
    }
    return q;
  }

  /**
   * Returns the angle of the perpendicular to the tangent in the wiredshape
   * at position t, 0<=t<=1.
   * @param t "index" of the point to be used as perperdicular-tangent
   * @return The angle of the line that is perpendicular to the tanget line at position t
   */
  public double getAngle(double t) {
    double[] p1 = getPoint(Math.max(0.0, t - 0.001));
    double[] p2 = getPoint(Math.min(1.0, t + 0.001));
    return (Math.PI / 2.0 + getAngle(p1, p2));
  }

  /**
   * Evaluates the restriction in the given point
   * @param x Point to be evaluated by the restriction
   * @return the restriction value on the given point
   */
  public double evaluate(double[] x) {
    return 0.0;
  }

}
