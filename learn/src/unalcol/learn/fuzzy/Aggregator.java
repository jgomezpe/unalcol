package unalcol.learn.fuzzy;

import unalcol.learn.Prediction;

/**
 * <p>Title: Aggregator</p>
 * <p>Description: This class determines the method for aggregating the answer
 * given by a recognizer in a single class according to the classes confidences</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */
public interface Aggregator {
  /**
   * Determines the final class recognized
   * @param confidence Confidence values produced by a recognizer
   * (one confidence value per class)
   * @return The final prediction given by a classifier
   */
  public Prediction<Integer> apply( double[] confidence );

}
