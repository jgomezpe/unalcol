package types;

import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.sparse.vector.SparseVector;
import unalcol.types.collection.vector.Vector;

public class SparseVectorTest {
    public static void main( String[] args ){
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
		ArrayCollection<Integer> the_indices = vector.indices();
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