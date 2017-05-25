package unalcol.learn;
import java.util.Iterator;

import unalcol.learn.supervised.classification.fuzzy.FuzzyClassifier;
import unalcol.learn.fuzzy.Aggregator;
import unalcol.learn.fuzzy.MaxAggregator;
import unalcol.learn.supervised.classification.Classifier;
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
public class Recognizer<T> implements Classifier<T>{
    protected Aggregator aggregator;
    protected FuzzyClassifier<T> labeler;
    
    public Recognizer(FuzzyClassifier<T> labeler){
        aggregator = new MaxAggregator();
        this.labeler = labeler;
    }
    
    public Recognizer(FuzzyClassifier<T> labeler, Aggregator aggregator ){
        this.aggregator = aggregator;
        this.labeler = labeler;
    }

  /**
   * Returns the number of classes that a data record belongs to
   * @return The number of classes that a data record belongs to
   */
  public int classesNumber(){ return labeler.classesNumber(); }

  /**
   * Calculate the confidence value of classifying the given data into each possible class
   * @param data Data sample to be classified
   * @return Confidence values of classifying the data point 'data' in each possible
   * class
   */
  public double[] confidence( T data ){ return labeler.apply(data); }
  
  public Vector<double[]> confidence( ArrayCollection<T> data ){
      Vector<double[]> conf = new Vector<double[]>();
      Iterator<T> iter = data.iterator();
      while( iter.hasNext() ){
          conf.add(confidence(iter.next()));
      }
      return conf;
  }  
  
  public Aggregator aggregator(){  return aggregator; }

  @Override
  public Prediction apply(T data) {
      return aggregator.apply(confidence(data));
  }
}