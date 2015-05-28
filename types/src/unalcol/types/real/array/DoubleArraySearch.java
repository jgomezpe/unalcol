package unalcol.types.real.array;

/**
 * <p>Title: DoubleArray</p>
 * <p>Description:  A set of methods for searching in a sorted array of doubles</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */
public class DoubleArraySearch{

    /**
     * Determines if the sorted array contains the given double
     * @param sorted Array of ints (should be sorted)
     * @param x Element to be located
     * @return <i>true</i> if the double belongs to the sorted array, <i>false</i> otherwise
     */
    public static boolean contains(double[] sorted, double x) {
        return ( find(sorted, x) != -1 );
    }

    /**
     * Search for the position of the given int. The vector should be sorted
     * @param sorted Array of ints (should be sorted)
     * @param x Element to be located
     * @return The position of the given int, -1 if the given int is not in the array
     */
    public static int find( double[] sorted, double x ){
        if( sorted.length > 0 && sorted[0] < sorted[sorted.length-1] ){
            return findLow2High(sorted, x);
        }else{
            return findHigh2Low(sorted, x);
        }
    }


    /**
     * Search for the position of the given double. The vector should be sorted (low to high)
     * @param sorted Array of doubles (should be sorted)
     * @param x Element to be located
     * @return The position of the given double, -1 if the given double is not in the array
     */
    public static int findLow2High( double[] sorted, double x ){
        int pos = findRightLow2High( sorted, x );
        if( pos > 0 && x == sorted[pos-1] ){
                pos--;
        }else{
            pos = -1;
        }
        return pos;
    }

    /**
     * Search for the position of the first element in the array that is bigger
     * than the given element. The array should be sorted (low to high)
     * @param sorted Array of doubles (should be sorted)
     * @param x Element to be located
     * @return Position of the first double that is bigger than the given double
     */
    public static int findRightLow2High(double[] sorted, double x) {
      int n = sorted.length;
      if( n > 0 ){
          int a = 0;
          int b = n - 1;
          if( x < sorted[a] ){ return 0; }
          if( x >= sorted[b] ){ return n; }

          while (a + 1 < b) {
              int m = (a + b) / 2;
              if( x < sorted[m] ){ b = m; }
              else{ a = m; }
          }
          return b;
      }else{ return 0; }
    }

    /**
     * Search for the position of the given double. The vector should be sorted (high to low)
     * @param sorted Array of doubles (should be sorted)
     * @param x Element to be located
     * @return The position of the given double, -1 if the given double is not in the array
     */
    public static int findHigh2Low( double[] sorted, double x ){
        int pos = findRightHigh2Low( sorted, x );
        if( pos > 0 && x == sorted[pos-1] ){
                pos--;
        }else{
            pos = -1;
        }
        return pos;
    }

    /**
     * Search for the position of the first element in the array that is smaller
     * than the given element. The array should be sorted (high to low)
     * @param sorted Array of doubles (should be sorted)
     * @param x Element to be located
     * @return Position of the first double that is smaller than the given double
     */
    public static int findRightHigh2Low(double[] sorted, double x) {
      int n = sorted.length;
      if( n > 0 ){
          int a = 0;
          int b = n - 1;
          if( x > sorted[a] ){ return 0; }
          if( x <= sorted[b] ){ return n; }

          while (a + 1 < b) {
              int m = (a + b) / 2;
              if( x > sorted[m] ){ b = m; }
              else{ a = m; }
          }
          return b;
      }else{ return 0; }
    }

    /**
     * Search for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted (low to high)
     * @param sorted Array of doubles (should be sorted)
     * @param x Element to be located
     * @return Position of the object that is smaller than the given element
     */
    public int findLeftLow2High(double[] sorted, double x) {
      int n = sorted.length;
      if( n > 0 ){
        int a = 0;
        int b = n - 1;
        if( x <= sorted[a] ){ return -1; }
        if( x > sorted[b] ){ return n-1; }

        while (a + 1 < b) {
            int m = (a + b) / 2;
            if( x <= sorted[m] ){ b = m; }
            else{ a = m; }
        }
        return a;
      }else{ return 0; }
    }

    /**
     * Search for the position of the last element in the array that is bigger
     * than the element given. The array should be sorted (high to low)
     * @param sorted Array of doubles (should be sorted)
     * @param x Element to be located
     * @return Position of the double that is smaller than the given double
     */
    public int findLeftHigh2Low(double[] sorted, double x) {
      int n = sorted.length;
      if( n > 0 ){
        int a = 0;
        int b = n - 1;
        if( x >= sorted[a] ){ return -1; }
        if( x < sorted[b] ){ return n-1; }

        while (a + 1 < b) {
            int m = (a + b) / 2;
            if( x >= sorted[m] ){ b = m; }
            else{ a = m; }
        }
        return a;
      }else{ return 0; }
    }
}
