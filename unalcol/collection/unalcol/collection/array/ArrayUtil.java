package unalcol.collection.array;

import unalcol.sort.Order;

/**
 *
 * @author jgomez
 */
public class ArrayUtil {
    public static <T> void rightRotation( int start, int end, T[] a ){
    	System.arraycopy(a, start, a, start+1, end-start);
    }

    public static <T> void leftRotation( int start, int end, T[] a ){
    	System.arraycopy(a, start+1, a, start, end-start);
    }
    
    public static <T> void insert( int n, T[] a, T x, int i ){
        rightRotation( i, n, a );
        a[i] = x;
    }
    
    public static <T> void del( int n, T[] a, int i ){
        leftRotation(i, n-1, a);
    }

	public static <T> int indexForAddToSortArray( int n, T[] a, T x, Order order ){
        BinarySearch<T> search = new BinarySearch<T>(a,order);
    	if( a.length <= n ) return -1;
    	if( n==0 ){    		
    		return 0;
    	}
		int k = search.findLeft(0, n, x);
    	if( k<n-1 ){
    		if( order.compare(x, a[k+1]) != 0 ){
    			return k+1;
    		}
    		return -1;
    	}
    	return n;
    }
    
	public static <T> boolean addToSortArray( int n, T[] a, T x, Order order ){
    	int k = indexForAddToSortArray(n, a, x, order);
    	if( k>=0 ){
			insert(n, a, x, k);
			return true;
    	}
    	return false;
    }

	public static <T> boolean removeFromSortArray( int n, T[] a, T x, Order order ){
    	if( a.length > 0 && n>0 ){
            BinarySearch<T> search = new BinarySearch<T>(a,order);
			int k = search.find(0, n, x);
	    	if( k>=0 ){
	    		del(n, a, k);
	    		return true;
	    	}
    	}
    	return false;
    }
    
	public static <T> int findInSortArray( int n, T[] a, T x,  Order order ){
        BinarySearch<T> search = new BinarySearch<T>(a,order);
    	return search.find(0,  n, x);
    }    
	
	public static Object[] cast( Object... objects ){ return objects; }
}