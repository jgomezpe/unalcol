package unalcol.data.clustergenerator;

import unalcol.random.raw.RawGenerator;

/**
 * <p>Title: Wire</p>
 * <p>Description: Abstract class for representing a wire. A wire can be of any
 * shape in the space: straight lines, curve lines, etc</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */

public abstract class Wire extends Restriction {

  /**
   * Default constructor
   */
  public Wire() {}

  /**
   * Sets the restriction
   * @param _positive Determines which condition is been applied:
   * true indicates that the restriction is g(x) >= 0
   * false indicates that the restriction is g(x) <= 0
   */
  public Wire( boolean _positive ){  super( _positive );  }

  /**
   * Returns the point in the wiredshape at position t, 0<=t<=1. Because the
   * figure is wire, there is a possible parametrization of the points that define the
   * wire.
   * @param t "index" of the point to be retrieved
   * @return The point at position t
   */
  public abstract double[] getPoint( double t );

  /**
   * Returns the angle of the perpendicular to the tangent in the wiredshape
   * at position t, 0<=t<=1.
   * @param t "index" of the point to be used as perperdicular-tangent
   * @return The angle of the line that is perpendicular to the tangent line at position t
   */
  public abstract double getAngle( double t );

  /**
   * Generates a point following the given shape and distribution
   * @param d Distribution that follows the point generation
   * @return A point following the wire shape and the point distribution
   */
  public double[] getPoint( Distribution d ){
    double t = RawGenerator.next(this);
    double theta = getAngle( t );
    double[] p = getPoint( t );
    double[] q = d.generate( p, theta/Math.PI/2.0 );
//    q.sum(p);
    return q;
  }

  /**
   * Calculates the angle defined by the segment of line
   * @param start Initial point of the line
   * @param end Final point of the line
   * @return The angle defined by the segment of line
   */
  public static double getAngle( double[] start, double[] end ){
    double angle;
    double[] p = (double[]) end.clone();
    space.fastMinus(p, start);
    if( p[0] == 0.0 ) {
      if (p[1] > 0.0 ) {
        angle = Math.PI / 2.0;
      } else {
        if (p[1] < 0.0) { angle = -Math.PI/2.0; } else { angle = 0.0;  }
      }
    } else {
      angle = Math.atan(p[1] / p[0]);
    }
    if ( angle < 0.0 ){ angle += 2.0 * Math.PI; }
    return angle;
  }
  /**
   * Calculates the magnitude of the segment of line
   * @param start Initial point of the line
   * @param end Final point of the line
   * @return The magnitude of the segment of line
   */
  public static double getMagnitude( double[] start, double[] end ){
    return(distance.apply(start,end));
  }
}
