package types;

import java.util.Iterator;

import unalcol.types.collection.sparse.matrix.SparseMatrix;
import unalcol.types.collection.sparse.matrix.SparseMatrixElement;
import unalcol.types.collection.vector.Vector;

public class SparseMatrixTest {
	public static void main( String[] args ){
		System.out.println("Setting elements in the sparse matrix");
		Vector<int[]> poss = new Vector<int[]>();
		int n = 3;
		SparseMatrix<Integer> matrix = new SparseMatrix<Integer>(n);
		for( int i=0; i<1000; i++ ){
			int[] pos = new int[n];
			for( int k=0; k<n; k++ ){
				pos[k] = (int)(Math.random() * 100);
				System.out.print( " " + pos[k] );
			}
			poss.add(pos);
			matrix.set( pos, i );
			System.out.println( " Recovered.." + matrix.get( pos ) );
		}
		
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("Getting elements from the sparse matrix");
		for( int i=0; i<100; i++ ){
			int[] pos = new int[n];
			for( int k=0; k<n; k++ ){
				pos[k] = (int)(Math.random() * 100);
				System.out.print( " " + pos[k] );
			}
			if( matrix.stored(pos) ){
				System.out.println( " .." + matrix.get( pos ) );
			}else{
				System.out.println( " Not element..");
			}	
		}

		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("Getting elements from  positions of the sparse matrix");
		for( int i=0; i<100; i++ ){
			int[] pos =  poss.get( (int)(Math.random() * poss.size()) );
			for( int k=0; k<pos.length; k++){
				System.out.print( " " + pos[k] );
			}
			System.out.println( " .." + matrix.get( pos ) );
		}

		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("Getting the set of active indices of the sparse matrix");
		int[] order = new int[]{0,1,2};
		Iterator<int[]> indices = matrix.indices(order);
		while( indices.hasNext() ){
			int[] x = indices.next();
			System.out.println(x[0]+","+x[1]+","+x[2]);
		}
		
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("Iterating through the sparse matrix");
		Iterator<SparseMatrixElement<Integer>> x_indices = matrix.elements(order);
		while( x_indices.hasNext() ){
			SparseMatrixElement<Integer> el = x_indices.next();
			int[] x = el.indices();
			System.out.println(x[0]+","+x[1]+","+x[2]+":"+el.value());
		}
		
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("Iterating through the sparse matrix in different dimension order");
		order = new int[]{1,2,0};
		x_indices = matrix.elements(order);
		while( x_indices.hasNext() ){
			SparseMatrixElement<Integer> el = x_indices.next();
			int[] x = el.indices();
			System.out.println(x[0]+","+x[1]+","+x[2]+":"+el.value());
		}
		

		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("Obtaining the dimension limits of the sparse matrix");
		int[] low = matrix.low();
		int[] high = matrix.high();
		for( int i=0; i<low.length; i++ ){
			System.out.println( "[" + low[i] + "," + high[i] + "]" );
		}
		
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("Deleting elements from random position of the sparse matrix");
		for( int i=0; i<100; i++ ){
			int[] pos = new int[n];
			for( int k=0; k<n; k++ ){
				pos[k] = (int)(Math.random() * 100);
				System.out.print( " " + pos[k] );
			}
			System.out.println( " Deleted.." + matrix.del( pos ) );
		}

		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("Deleting elements from  positions of the sparse matrix");
		for( int i=0; i<100; i++ ){
			int[] pos =  poss.get( (int)(Math.random() * poss.size()) );
			for( int k=0; k<pos.length; k++){
				System.out.print( " " + pos[k] );
			}
			System.out.println( " Deleted.." + matrix.del( pos ) );
		}

		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("Iterating through the sparse matrix");
		x_indices = matrix.elements(order);
		while( x_indices.hasNext() ){
			SparseMatrixElement<Integer> el = x_indices.next();
			int[] x = el.indices();
			System.out.println(x[0]+","+x[1]+","+x[2]+":"+el.value());
		}
		
	}
}
