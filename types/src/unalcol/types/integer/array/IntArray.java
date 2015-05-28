package unalcol.types.integer.array;
import java.util.*;

/**
 * <p>Set of constants and methods operating on an array of the primitive int data type,
 * for example, for inverting an array of ints</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */
public class IntArray{
    /**
     * Transforms a Vector into int array
     * @param v vector of integers to be converted
     * @return integer array
     */
    public static int[] get(Vector<Integer> v) {
        int n = v.size();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = v.get(i);
        }
        return a;
    }

    /**
     * Reverses the given array
     * @param a Integer array to be reversed
     */
    public static void invert(int[] a) {
        int n = a.length;
        int j = n - 1;
        for (int i = 0; i < j; i++) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            j--;
        }
    }
    
    public static int max(int[] a){
        int m = a[0];
        for( int i=1; i<a.length; i++ ){
            if( m < a[i] ){
                m = a[i];
            }
        }
        return m;
    }
    
    public static int min(int[] a){
        int m = a[0];
        for( int i=1; i<a.length; i++ ){
            if( m > a[i] ){
                m = a[i];
            }
        }
        return m;
    }    
}
