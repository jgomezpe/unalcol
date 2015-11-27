package types;

import unalcol.random.Random;
import unalcol.sort.Order;
import unalcol.types.collection.array.ArrayUtil;
import unalcol.types.integer.IntegerOrder;

public class ArrayUtilTest {
    public static void main(String[] args){
    	Integer[] array = new Integer[100];
    	int n = 0;
    	Order<Integer> order = new IntegerOrder();
    	for( int i=0; i<100; i++ ){
    		int x = Random.nextInt(100);
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
    		int x = Random.nextInt(100);
    		System.out.print("Removing.."+x + ":");    
    		boolean removed = ArrayUtil.removeFromSortArray(n, array, x, order);
    		System.out.println(removed);    
    		if( removed ) n--;
        	for( int j=0; j<n; j++ ){
        		System.out.print( " " + array[j]);
        	}
        	System.out.println();
    	}

    }

}
