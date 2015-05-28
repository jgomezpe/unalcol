package unalcol.sort.algorithm;
import unalcol.sort.*;

/**
 * <p>BubbleSort algorithm</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class BubbleSort<T> extends Sort<T> {

  /**
   * Default constructor
   */
  public BubbleSort(){}

  /**
   * Crates a bubble sort algorithm with the given order
   * @param order Order used for sorting the objects
   */
  public BubbleSort( Order<T> order ){
      super( order );
  }

  /**
   * Creates a bubble sort algorithm using the given order and overwriting array flag
   * @param order Order used for sorting the objects
   * @param overwrite If the array should be overwrited or not
   */
  public BubbleSort( Order<T> order, boolean overwrite ){
      super( order, overwrite );
  }

    /**
     * Sorts a portion of the array of objects according to the given order (it does not creates a new array)
     * @param a Array of objects to be sorted
     * @param start Initial position in the array to be sorted
     * @param end Final position in the array to be sorte
     * @return <i>true</i> If the sorting process was done without fails, <i>false</i> otherwise
     */
  public boolean apply(T[] a, int start, int end) {
      if( getOrder(a) != null ){
        for (int i = start; i < end - 1 && continueFlag; i++) {
          for (int j = i + 1; j < end && continueFlag; j++) {
            if (order.compare(a[j], a[i])<0) {
              T x = a[i];
              a[i] = a[j];
              a[j] = x;
            }
          }
        }
        return true;
      }
      return false;
  }
}