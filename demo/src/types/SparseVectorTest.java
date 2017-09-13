package types;

import unalcol.clone.DefaultClone;
import unalcol.random.raw.JavaGenerator;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.types.collection.array.Array;
import unalcol.types.collection.sparse.vector.SparseVector;
import unalcol.types.collection.vector.Vector;
import unalcol.types.collection.vector.VectorClone;
import unalcol.types.real.array.DoubleArrayPlainRead;
import unalcol.types.real.array.DoubleArrayPlainWrite;

public class SparseVectorTest {
	public static void init_services(){
		ServicePool service = new ServicePool();
		service.register(new JavaGenerator(), Object.class);         
		service.register(new DoubleArrayPlainRead(), double[].class);
		service.register(new DoubleArrayPlainWrite(), double[].class);
		service.register(new DefaultClone(), Object.class);
		service.register(new VectorClone<Object>(), Vector.class);
//        service.register(new ConsoleTracer(), Object.class);
		Service.set(service);
	}

	public static void main( String[] args ){
		init_services();
		Vector<Integer> indices = new Vector<Integer>();
		// Creating a SparseVector 
		SparseVector<Integer> vector = new SparseVector<Integer>();
		// Adding data to randomly selected positions
		for( int i=0; i<100; i++ ){
		    int k = (int)(10000*Math.random());
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
		Array<Integer> the_indices = vector.indices();
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
				System.out.println( i + "->" + vector.del(i) );
			}catch( ArrayIndexOutOfBoundsException e ){
				System.out.println( "It is not there... " + i );
			}
		}
		
		// Deleting some elements 
    }
}