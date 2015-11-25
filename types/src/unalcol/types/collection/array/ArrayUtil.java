/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.collection.array;

import unalcol.random.util.Rand;
import unalcol.sort.Order;
import unalcol.sort.Search;
import unalcol.types.integer.IntegerOrder;

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

    @SuppressWarnings("rawtypes")
	protected static Search search = new Search();

    @SuppressWarnings("unchecked")
	public static int indexForAddToSortArray( int n, Object[] a, Object x, @SuppressWarnings("rawtypes") Order order ){
    	if( a.length <= n ) return -1;
    	if( n==0 ){    		
    		return 0;
    	}
		int k = search.findLeft(a,  0, n, x, order);
    	if( k<n-1 ){
    		if( order.compare(x, a[k+1]) != 0 ){
    			return k+1;
    		}
    		return -1;
    	}
    	return n;
    }
    
	public static boolean addToSortArray( int n, Object[] a, Object x, @SuppressWarnings("rawtypes") Order order ){
    	int k = indexForAddToSortArray(n, a, x, order);
    	if( k>=0 ){
			insert(n, a, x, k);
			return true;
    	}
    	return false;
    }

    @SuppressWarnings("unchecked")
	public static boolean removeFromSortArray( int n, Object[] a, Object x, @SuppressWarnings("rawtypes") Order order ){
    	if( a.length > 0 && n>0 ){
			int k = search.find(a,  0, n, x, order);
	    	if( k>=0 ){
	    		del(n, a, k);
	    		return true;
	    	}
    	}
    	return false;
    }
    
    @SuppressWarnings("unchecked")
	public static int findInSortArray( int n, Object[] a, Object x,  @SuppressWarnings("rawtypes") Order order ){
    	return search.find(a, 0,  n, x, order);
    }
    
    public static void main(String[] args){
    	Integer[] array = new Integer[100];
    	int n = 0;
    	Order<Integer> order = new IntegerOrder();
    	for( int i=0; i<100; i++ ){
    		int x = Rand.nextInt(100);
    		System.out.print("Adding.."+x + ":");    
    		boolean added = addToSortArray(n, array, x, order);
    		System.out.println(added);    
    		if( added ) n++;
        	for( int j=0; j<n; j++ ){
        		System.out.print( " " + array[j]);
        	}
        	System.out.println();
    	}
    	for( int i=0; i<20; i++ ){
    		int x = Rand.nextInt(100);
    		System.out.print("Removing.."+x + ":");    
    		boolean removed = removeFromSortArray(n, array, x, order);
    		System.out.println(removed);    
    		if( removed ) n--;
        	for( int j=0; j<n; j++ ){
        		System.out.print( " " + array[j]);
        	}
        	System.out.println();
    	}

    }
}