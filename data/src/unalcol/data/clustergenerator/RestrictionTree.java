package unalcol.data.clustergenerator;

/**
 * <p>Title: RestrictionTree</p>
 * <p>Description: A compose restriction. The restriction follows a expression
 * tree compose of "intersection" or "union" restrictions, i.e. of the area
 * that the restrictions define </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */

public class RestrictionTree extends Restriction {
  /**
   * Determines if the composed restriction is the union of two restrictions
   * or if it is the intersection.
   * <p>true indicates that the restriction is an union g(x) = f(x) U h(x)
   * <p>false indicates that the restriction is an intersection g(x) f(x) n h(x)</p>
   */
  public boolean union = true;

  /**
   * First restriction that is combined in this restriction
   */
  public Restriction f = null;

  /**
   * Second restriction that is combined in this restriction
   */
  public Restriction h = null;

  /**
    * @param _union Determines if the composed restriction is the union of two restrictions
    * or if it is the intersection.
    * <p>true indicates that the restriction is an union g(x) = f(x) U h(x)
    * <p>false indicates that the restriction is an intersection g(x) f(x) n h(x)</p>
    * @param _f First restriction that is combined in this restriction
    * @param _h Second restriction that is combined in this restriction
    */
  public RestrictionTree( boolean _union, Restriction _f, Restriction _h ){
    union = _union;
    f = _f;
    h = _h;
  }

  /**
   * Evaluates the restriction in the given point
   * @param x Point to be evaluated by the restriction
   * @return the restriction value on the given point
   */
  public double evaluate( double[] x ){ return 0.0; }

  /**
   * Determines if the point satisfy the restriction or not
   * @param x Point to be evaluated by the restriction
   * @return true if the point satisfy the restriction false if not.
   */
  public boolean satisfy( double[] x ){
    if( union ){
      return (f.satisfy(x) || h.satisfy(x));
    }else{
      return (f.satisfy(x) && h.satisfy(x));
    }
  }
}
