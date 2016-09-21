package unalcol.learn;
import java.util.Iterator;
import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.vector.*;

/**
 * <p>Title: Recognizer</p>
 * <p>Description: An abstract recognition algorithm.</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez-Perdomo
 * @version 1.0
 *
 */
public abstract class Recognizer<T> implements Labeler<T>{
    protected Aggregator aggregator;
    
    public Recognizer(){
        aggregator = new MaxAggregator();
    }
    
    public Recognizer( Aggregator aggregator ){
        this.aggregator = aggregator;
    }

  /**
   * Returns the number of classes that a data record belongs to
   * @return The number of classes that a data record belongs to
   */
  public abstract int classesNumber();

  /**
   * Calculate the confidence value of classifying the given data into each possible class
   * @param data Data sample to be classified
   * @return Confidence values of classifying the data point 'data' in each possible
   * class
   */
  public abstract double[] confidence( T data );
  
  public Prediction predict( T data ){
      return aggregator.apply(confidence(data));
  }
  
  public Vector<double[]> confidence( ArrayCollection<T> data ){
      Vector<double[]> conf = new Vector<double[]>();
      Iterator<T> iter = data.iterator();
      while( iter.hasNext() ){
          conf.add(confidence(iter.next()));
      }
      return conf;
  }
  
  public int label( T obj ){
      return predict(obj).label();
  }

  public Vector<Prediction> predict( ArrayCollection<T> data ){
      Vector<Prediction> pred = new Vector<Prediction>();
      Iterator<T> iter = data.iterator();
      while( iter.hasNext() ){
          pred.add(predict(iter.next()));
      }
      return pred;
  }  
  
  public Aggregator aggregator(){  return aggregator; }
}