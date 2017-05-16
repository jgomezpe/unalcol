package unalcol.learn.unsupervised.partition;


import unalcol.clone.Clone;
import unalcol.learn.unsupervised.UnsupervisedLearningFromArray;
import unalcol.learn.Prediction;
import unalcol.learn.Recognizer;
import unalcol.math.algebra.VectorSpace;
import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.vector.Vector;


/**
 * <p>Title:kMeans</p>
 * <p>Description:The k-means algorithm</p>
 * <p>Copyright:    Copyright (c) 2006</p>
 * <p>Company:Universidad Nacional De Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */
public class kMeans<T> extends UnsupervisedLearningFromArray<T>{
    protected double epsilon;
    protected int MAX_ITER;
    protected VectorSpace<T> space;
    
  /**
   * Creates a k-means unsupervised algorithm
   * @param _data Data Set being partitioned
   * @param _metric QuasiMetric Distance between data points
   * @param _condition Termination condition (can be the number of iterations)
   * @param _k Number of groups (clusters) defining the partition
   */
  public kMeans( Recognizer<T> initialMu, VectorSpace<T> space,
          double epsilon, int MAX_ITER ) {
      output = initialMu;
      this.space = space;
      this.epsilon = epsilon;
      this.MAX_ITER = MAX_ITER;
  }
  
  @Override
  public Recognizer<T> apply(ArrayCollection<T> set){
      Vector<Prediction> pred;
      int i=0;
      double oldS = 100.0;
      double s = -100.0;
      while( i<MAX_ITER && Math.abs(s-oldS) > epsilon ){
          oldS = s;
          pred = output.predict(set);
          s = next(output.classesNumber(), set, pred);
          i++;
          System.out.println(s);
      }
      return output;
  }
  
  
  @SuppressWarnings("unchecked")
public double next(int k, ArrayCollection<T> set, 
          Vector<Prediction> pred){
      CentroidsRecognizer<T> r = (CentroidsRecognizer<T>)output;
      T[] nextMu = (T[])new Object[k];
      for( int i=0; i<k; i++ ){
          nextMu[i] = null;
      }
      double s = 0.0;
      int[] counter = new int[k];
      int c;
      for( int i=0; i<pred.size(); i++ ){
          c = pred.get(i).label();
          s += pred.get(i).confidence();
          if( nextMu[c] != null ){
              nextMu[c] = space.fastPlus(nextMu[c], set.get(i));
          }else{
              nextMu[c] = (T)Clone.create(set.get(i));
          }
          counter[c]++;
      }
      for( int i=0; i<k; i++ ){
           if( counter[i] > 0 ){
             space.fastDivide( nextMu[i], counter[i] );
             r.setMu(i, nextMu[i]);
           }           
      }
      return s;
  }
  
}
