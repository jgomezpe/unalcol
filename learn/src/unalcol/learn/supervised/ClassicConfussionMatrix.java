/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.supervised;

import java.util.Iterator;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public class ClassicConfussionMatrix extends ConfussionMatrix{
  public ClassicConfussionMatrix( int real_classes, int predicted_classes){
      super( real_classes, predicted_classes );
  }
  
  public ClassicConfussionMatrix( int real_classes, Vector<Integer> real, 
                             int predicted_classes, Vector<Integer> predicted){
      super( real_classes, predicted_classes);
      add(real, predicted);
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
