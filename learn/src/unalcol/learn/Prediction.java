package unalcol.learn;

/**
 * <p>Title: Prediction</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 *
 */
public class Prediction<T> {
  /**
   *
   */
  public T label;

  /**
   *
   */
  public double confidence;

  /**
   *
   * @param _label
   * @param _confidence
   */
  public Prediction( T _label, double _confidence ) {
    label = _label;
    confidence = _confidence;
  }

  /**
   *
   * @param _label
   */
  public Prediction( T _label ) {
    label = _label;
    confidence = 1.0;
  }
  
  public T label(){
      return label;
  }
  
  public double confidence(){
      return confidence;
  }
}
