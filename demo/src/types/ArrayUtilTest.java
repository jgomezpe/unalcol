package types;

import unalcol.random.integer.IntUniform;
import unalcol.random.integer.RandInt;
import unalcol.types.integer.IntegerOrder;
import unalcol.types.object.array.ArrayUtil;

public class ArrayUtilTest {
	public static void main(String[] args){
		try{
			Integer[] array = new Integer[100];
			int n = 0;
			IntegerOrder order = new IntegerOrder();
			RandInt g = new IntUniform(100);
			for( int i=0; i<100; i++ ){
	    		int x = g.next();
	    		System.out.print("Adding.."+x + ":");    
	    		boolean added = ArrayUtil.addToSortArray(n, array, x, order);
	    		System.out.println(added);    
	    		if( added ) n++;
	        	for( int j=0; j<n; j++ ){
	        		System.out.print( " " + array[j]);
	        	}
	        	System.out.println();
	    	}
	    	for( int i=0; i<20; i++ ){
	    		int x = g.next();
	    		System.out.print("Removing.."+x + ":");    
	    		boolean removed = ArrayUtil.removeFromSortArray(n, array, x, order);
	    		System.out.println(removed);    
	    		if( removed ) n--;
	        	for( int j=0; j<n; j++ ){
	        		System.out.print( " " + array[j]);
	        	}
	        	System.out.println();
	    	}
		}catch(Exception e){ e.printStackTrace(); }
    }

}
