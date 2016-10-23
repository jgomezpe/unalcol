package types;

import java.util.Iterator;

import unalcol.types.collection.sparse.matrix.SparseMatrix;
import unalcol.types.collection.sparse.matrix.SparseMatrixElement;
import unalcol.types.collection.vector.Vector;

public class SparseMatrixTest {
	public static void main( String[] args ){
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
			Integer h = i;
			System.out.println( " Inserting... " + h );
			try{
				if( matrix.get( pos ) != null ){
					System.out.println( "Already there..." );
				}
			}catch( ArrayIndexOutOfBoundsException  ex ){
				System.out.println("It is not there...");
			}
			matrix.set( pos, h );
            System.out.println( "Recover" + matrix.get( pos ) );
//             System.out.println( "******************" );
		}
		
		int[] order = new int[]{0,1,2};
		Iterator<int[]> indices = matrix.indices(order);
		while( indices.hasNext() ){
			int[] x = indices.next();
			System.out.println(x[0]+","+x[1]+","+x[2]);
		}
		System.out.println("+++++++++++++++++++++++++++++");

		Iterator<SparseMatrixElement<Integer>> x_indices = matrix.elements(order);
		while( x_indices.hasNext() ){
			SparseMatrixElement<Integer> el = x_indices.next();
			int[] x = el.indices();
			System.out.println(x[0]+","+x[1]+","+x[2]+":"+el.value());
		}
		
		System.out.println("+++++++++++++++++++++++++++++");
		int[] low = matrix.low();
		int[] high = matrix.high();
		for( int i=0; i<low.length; i++ ){
			System.out.println( "[" + low[i] + "," + high[i] + "]" );
		}
	}
}
