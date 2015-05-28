/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.collection.array;

/**
 *
 * @author jgomez
 */
public class ArrayUtil {
    public static void rightRotation( int start, int end, Object[] a ){
        for( int i=end; i>start; i-- ){
            a[i] = a[i-1];
        }
    }

    public static void leftRotation( int start, int end, Object[] a ){
        for( int i=start; i<end; i++ ){
            a[i] = a[i+1];
        }
    }

    public static void insert( int n, Object[] a, Object x, int i ){
        rightRotation( i, n, a );
        a[i] = x;
    }
    
    public static void del( int n, Object[] a, int i ){
        leftRotation(i, n-1, a);
    }
}
