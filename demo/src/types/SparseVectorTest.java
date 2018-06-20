package types;

import unalcol.random.raw.JavaGenerator;
import unalcol.services.Service;
import unalcol.types.collection.Collection;
import unalcol.types.collection.sparse.SparseArray;
import unalcol.types.collection.vector.Vector;
import unalcol.types.collection.vector.VectorClone;
import unalcol.types.real.array.DoubleArrayPlainRead;
import unalcol.types.real.array.DoubleArrayPlainWrite;

public class SparseVectorTest {
	public static void init_services(){
        Service.register(new JavaGenerator(), Object.class);         
    	Service.register(new DoubleArrayPlainRead(), double[].class);
        Service.register(new DoubleArrayPlainWrite(), double[].class);
        Service.register(new VectorClone<Object>(), Vector.class);
//        service.register(new ConsoleTracer(), Object.class);
	}

    public static void main( String[] args ){
    	init_services();
		Vector<Integer> indices = new Vector<Integer>();
		// Creating a SparseVector 
		SparseArray<Integer> vector = new SparseArray<Integer>();
		// Adding data to randomly selected positions
		for( Integer i=0; i<100; i++ ){
		    int k = (int)(1000*Math.random());
		    indices.add( k );
		    vector.set(k,i);
		    System.out.println( k + ":" + i );
		}
		
		// Printing the collection of indices as it was generated
		for( int i : indices ){
		    System.out.print(" "+ i );
		}
		System.out.println();
		
		// Getting the final set of indices and its stored value
		Collection<Integer> the_indices = vector.keys();
		for( int i : the_indices ){
			System.out.println( i + ":" + vector.get(i));
		}
		
		// Checking for some random positions
		// Handling the exception for checking index existence 		
		for( int i=0; i<100; i++ ){
			int k = (int)(1000*Math.random());
			try{
				System.out.println( k + "->" + vector.get(k) );
			}catch( ArrayIndexOutOfBoundsException e ){
				System.out.println( "It is not there... " + k );
			}
		}

		// Deleting positions between 0 and 1000 
		// Handling the exception for checking index existence 		
		for( int i=0; i<1000; i++ ){
			try{
				System.out.println( i + "->" + vector.remove(i) );
			}catch( ArrayIndexOutOfBoundsException e ){
				System.out.println( "It is not there... " + i );
			}
		}
		
		// Deleting some elements 
    }
}