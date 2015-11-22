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
public class Prediction {
  /**
   *
   */
  public int label;

  /**
   *
   */
  public double confidence;

  /**
   *
   * @param _label
   * @param _confidence
   */
  public Prediction( int _label, double _confidence ) {
    label = _label;
    confidence = _confidence;
  }

  /**
   *
   * @param _label
   */
  public Prediction( int _label ) {
    label = _label;
    confidence = 1.0;
  }
  
  public int label(){
      return label;
  }
  
  public double confidence(){
      return confidence;
  }
}
