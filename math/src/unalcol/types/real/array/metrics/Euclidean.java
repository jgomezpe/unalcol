package unalcol.types.real.array.metrics;

/**
 * <p>Title: Euclidean</p>
 * <p>Description: Calculates the Euclidean distance between two real vectors,
 * <p>calculating the square-root</p>
 * <p>Copyright:    Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */
public class Euclidean extends Minkowski {
  /**
   * Creates an Euclidean distance object, calculating the square-root
   */
  public Euclidean() {
    super(2.0);
  }
}
