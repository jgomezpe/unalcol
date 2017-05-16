/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.supervised.classification;

import java.util.Iterator;

import unalcol.learn.supervised.classification.fuzzy.FuzzyConfussionMatrix;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public class ConfussionMatrix extends FuzzyConfussionMatrix{
  public ConfussionMatrix( int real_classes, int predicted_classes){
      super( real_classes, predicted_classes );
  }
  
  public void add( int[] real, int[] predicted ){
      for( int i=0; i<real.length; i++){          
          add( real[i], predicted[i] );
      }
  }

  public void add( int real, int predicted ){
      add( real, predicted, 1.0 );
  } 
  
  /**
   *
   * @param real
   * @param predicted
   */
  public void add( Vector<Integer> real, Vector<Integer> predicted) {
	  Iterator<Integer> iter1 = real.iterator();
	  Iterator<Integer> iter2 = predicted.iterator();
	  while( iter1.hasNext() && iter2.hasNext()) {
		  int r = iter1.next();
                  int p = iter2.next();
                  matrix[r][p]++;
                  real_total[r]++;
                  predicted_total[p]++;
                  total++;
	  }
  }    
}
