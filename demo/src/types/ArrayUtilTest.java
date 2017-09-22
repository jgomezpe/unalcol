package types;

import unalcol.random.raw.JavaGenerator;
import unalcol.random.raw.RawGenerator;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.sort.Order;
import unalcol.types.object.ArrayUtil;
import unalcol.types.integer.IntegerOrder;

public class ArrayUtilTest {
	public static void init_services(){
		ServicePool service = new ServicePool();
        service.register(new JavaGenerator(), Object.class);         
//        service.register(new ConsoleTracer(), Object.class);
        Service.set(service);
	}

	public static void main(String[] args){
		try{
			init_services();
			Integer[] array = new Integer[100];
			int n = 0;
			Order<Integer> order = new IntegerOrder();
			for( int i=0; i<100; i++ ){
	    		int x = (int)Service.run(RawGenerator.integer, Object.class, 100);
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
	    		int x = (int)Service.run(RawGenerator.integer, Object.class, 100);
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
