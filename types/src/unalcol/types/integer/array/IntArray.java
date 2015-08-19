package unalcol.types.integer.array;
import java.util.*;

import unalcol.random.integer.IntUniform;

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
    
    /**
     * Sorts (low to high) an array of integers using bubble sort
     * @param a Integer array to be sorted
     */
    public static void bubble(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[i]) {
                    int x = a[i];
                    a[i] = a[j];
                    a[j] = x;
                }
            }
        }
    }

    /**
     * Sorts (low to high) an array of integers using merge sort
     * @param a Integer array to be sorted
     */
    public static void merge(int[] a) {
        int n = a.length;
        if (n >= 4) {
            int nizq = n / 2;
            int nder = n - nizq;
            int[] aIzq = new int[nizq];
            int[] aDer = new int[nder];
            for (int i = 0; i < nizq; i++) {
                aIzq[i] = a[i];
            }
            for (int i = 0; i < nder; i++) {
                aDer[i] = a[nizq + i];
            }
            merge(aIzq);
            merge(aDer);
            int k = 0;
            int izq = 0;
            int der = 0;
            while (izq < nizq && der < nder) {
                if (aIzq[izq] < aDer[der]) {
                    a[k] = aIzq[izq];
                    izq++;
                } else {
                    a[k] = aDer[der];
                    der++;
                }
                k++;
            } while (izq < nizq) {
                a[k] = aIzq[izq];
                izq++;
                k++;
            } while (der < nder) {
                a[k] = aDer[der];
                der++;
                k++;
            }
        } else {
            bubble(a);
        }
    }    

    /**
     * Determines if the sorted array contains the given int
     * @param sorted Array of ints (should be sorted)
     * @param x Element to be located
     * @return <i>true</i> if the int belongs to the sorted array, <i>false</i> otherwise
     */
    public static boolean contains(int[] sorted, int x) {
        return contains(sorted, 0, sorted.length, x);
    }

    /**
     * Determines if the sorted array contains the given int
     * @param sorted Array of ints (should be sorted)
     * @param start Initial position for the searching process
     * @param end Final position (not included) for the searching process
     * @param x Element to be located
     * @return <i>true</i> if the int belongs to the sorted array, <i>false</i> otherwise
     */
    public static boolean contains(int[] sorted, int start, int end, int x) {
        return ( find(sorted, start, end, x) != -1 );
    }

    /**
     * Search for the position of the given int. The vector should be sorted
     * @param sorted Array of ints (should be sorted)
     * @param x Element to be located
     * @return The position of the given int, -1 if the given int is not in the array
     */
    public static int find( int[] sorted, int x ){
        return find(sorted, 0, sorted.length, x );
    }

    /**
     * Search for the position of the given int. The vector should be sorted
     * @param sorted Array of ints (should be sorted)
     * @param start Initial position for the searching process
     * @param end Final position (not included) for the searching process
     * @param x Element to be located
     * @return The position of the given int, -1 if the given int is not in the array
     */
    public static int find( int[] sorted, int start, int end, int x ){
        if( end > start && sorted[start] < sorted[end-1] ){
            return findLow2High(sorted, start, end, x);
        }else{
            return findHigh2Low(sorted, start, end, x);
        }
    }

    /**
     * Search for the position of the given int. The vector should be sorted (low to high)
     * @param sorted Array of ints (should be sorted)
     * @param x Element to be located
     * @return The position of the given int, -1 if the given int is not in the array
     */
    public static int findLow2High( int[] sorted, int x ){
        return findLow2High( sorted, 0, sorted.length, x);
    }

    /**
     * Search for the position of the given int. The vector should be sorted (low to high)
     * @param sorted Array of ints (should be sorted)
     * @param x Element to be located
     * @param start Initial position for the searching process
     * @param end Final position (not included) for the searching process
     * @return The position of the given int, -1 if the given int is not in the array
     */
    public static int findLow2High( int[] sorted, int start, int end, int x ){
        int pos = findRightLow2High( sorted, start, end, x );
        if( pos > start && x == sorted[pos-1] ){
                pos--;
        }else{
            pos = -1;
        }
        return pos;
    }

    /**
     * Search for the position of the first element in the array that is bigger
     * than the given element. The array should be sorted (low to high)
     * @param sorted Array of ints (should be sorted)
     * @param x Element to be located
     * @return Position of the first int that is bigger than the given int
     */
    public static int findRightLow2High(int[] sorted, int x) {
        return findRightLow2High(sorted, 0, sorted.length, x);
    }

    /**
     * Search for the position of the first element in the array that is bigger
     * than the given element. The array should be sorted (low to high)
     * @param sorted Array of ints (should be sorted)
     * @param start Initial position for the searching process
     * @param end Final position (not included) for the searching process
     * @param x Element to be located
     * @return Position of the first int that is bigger than the given int
     */
    public static int findRightLow2High(int[] sorted, int start, int end, int x) {
      if( end > start ){
          int a = start;
          int b = end - 1;
          if( x < sorted[a] ){ return start; }
          if( x >= sorted[b] ){ return end; }

          while (a + 1 < b) {
              int m = (a + b) / 2;
              if( x < sorted[m] ){ b = m; }
              else{ a = m; }
          }
          return b;
      }else{ return start; }
    }

    /**
     * Search for the position of the given int. The vector should be sorted (high to low)
     * @param sorted Array of ints (should be sorted)
     * @param x Element to be located
     * @return The position of the given int, -1 if the given int is not in the array
     */
    public static int findHigh2Low( int[] sorted, int x ){
        return findHigh2Low( sorted, 0, sorted.length, x );
    }
    /**
     * Search for the position of the given int. The vector should be sorted (high to low)
     * @param sorted Array of ints (should be sorted)
     * @param start Initial position for the searching process
     * @param end Final position (not included) for the searching process
     * @param x Element to be located
     * @return The position of the given int, -1 if the given int is not in the array
     */
    public static int findHigh2Low( int[] sorted, int start, int end, int x ){
        int pos = findRightHigh2Low( sorted, start, end, x );
        if( pos > start && x == sorted[pos-1] ){
                pos--;
        }else{
            pos = -1;
        }
        return pos;
    }

    /**
     * Search for the position of the first element in the array that is smaller
     * than the given element. The array should be sorted (high to low)
     * @param sorted Array of ints (should be sorted)
     * @param x Element to be located
     * @return Position of the first int that is smaller than the given int
     */
    public static int findRightHigh2Low(int[] sorted, int x) {
        return findRightHigh2Low(sorted, 0, sorted.length, x);
    }

    /**
     * Search for the position of the first element in the array that is smaller
     * than the given element. The array should be sorted (high to low)
     * @param sorted Array of ints (should be sorted)
     * @param start Initial position for the searching process
     * @param end Final position (not included) for the searching process
     * @param x Element to be located
     * @return Position of the first int that is smaller than the given int
     */
    public static int findRightHigh2Low(int[] sorted, int start, int end, int x) {
      if( end > start ){
          int a = start;
          int b = end - 1;
          if( x > sorted[a] ){ return start; }
          if( x <= sorted[b] ){ return end; }

          while (a + 1 < b) {
              int m = (a + b) / 2;
              if( x > sorted[m] ){ b = m; }
              else{ a = m; }
          }
          return b;
      }else{ return start; }
    }

    /**
     * Search for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted (low to high)
     * @param sorted Array of ints (should be sorted)
     * @param x Element to be located
     * @return Position of the object that is smaller than the given element
     */
    public static int findLeftLow2High(int[] sorted, int x) {
        return findLeftLow2High(sorted, 0, sorted.length, x);
    }

    /**
     * Search for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted (low to high)
     * @param sorted Array of ints (should be sorted)
     * @param start Initial position for the searching process
     * @param end Final position (not included) for the searching process
     * @param x Element to be located
     * @return Position of the object that is smaller than the given element
     */
    public static int findLeftLow2High(int[] sorted, int start, int end, int x) {
      if( end > start ){
        int a = start;
        int b = end - 1;
        if( x <= sorted[a] ){ return start-1; }
        if( x > sorted[b] ){ return end-1; }

        while (a + 1 < b) {
            int m = (a + b) / 2;
            if( x <= sorted[m] ){ b = m; }
            else{ a = m; }
        }
        return a;
      }else{ return start; }
    }

    /**
     * Search for the position of the last element in the array that is bigger
     * than the element given. The array should be sorted (high to low)
     * @param sorted Array of ints (should be sorted)
     * @param x Element to be located
     * @return Position of the int that is smaller than the given int
     */
    public static int findLeftHigh2Low(int[] sorted, int x) {
        return findLeftHigh2Low(sorted, 0, sorted.length, x);
    }

    /**
     * Search for the position of the last element in the array that is bigger
     * than the element given. The array should be sorted (high to low)
     * @param sorted Array of ints (should be sorted)
     * @param start Initial position for the searching process
     * @param end Final position (not included) for the searching process
     * @param x Element to be located
     * @return Position of the int that is smaller than the given int
     */
    public static int findLeftHigh2Low(int[] sorted, int start, int end, int x) {
      if( end > start ){
        int a = start;
        int b = end - 1;
        if( x >= sorted[a] ){ return start-1; }
        if( x < sorted[b] ){ return end-1; }

        while (a + 1 < b) {
            int m = (a + b) / 2;
            if( x >= sorted[m] ){ b = m; }
            else{ a = m; }
        }
        return a;
      }else{ return start; }
    }
    
    public static int[] create( int n, int value ){
    	int[] x = new int[n];
    	for( int i=0; i<n; i++ ) x[i] = value;
    	return x;
    }
    
    public static int[] random( int n, int max ){
    	IntUniform g = new IntUniform(max);
    	return g.generate(n);
    }    
}
