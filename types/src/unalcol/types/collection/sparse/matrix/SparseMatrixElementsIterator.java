package unalcol.types.collection.sparse.matrix;

import java.util.Iterator;
import java.util.NoSuchElementException;

import unalcol.types.collection.sparse.vector.SparseElement;
import unalcol.types.collection.sparse.vector.SparseVector;

public class SparseMatrixElementsIterator<T> implements Iterator<SparseMatrixElement<T>>{
	protected SparseMatrix<T> matrix;
	protected T value;
	protected Iterator<SparseElement<SparseMatrix<T>>> iter=null;
	protected SparseMatrixElementsIterator<T> inner_iter = null;
	protected int[] reduce;
	protected int[] order;
	protected int k;
	protected int i=-1;
	
	protected int[]  indices;
	
	protected static int[] reduce( int[] order ){
		int[] corder = order.clone();
		for( int i=0; i<corder.length; i++ ){
			for( int j=i+1; j<corder.length; j++ ){
				if( corder[j] > corder[i] ) corder[j]--;
			}
		}
		return corder;
	}
	
	public SparseMatrixElementsIterator( SparseMatrix<T> matrix, int[] order ) {
		this(matrix, order, reduce(order), 0, new int[order.length]);
	}
	
	protected SparseMatrixElementsIterator( SparseMatrix<T> matrix, int[] order, int[] reduce, int k, int[] indices ) {
		this.indices = indices;
		this.order = order;
		this.k = k;
		this.reduce = reduce;
		if( k<order.length ){
			SparseVector<SparseMatrix<T>> vector = matrix.dimension_data[reduce[k]];
			this.iter = (Iterator<SparseElement<SparseMatrix<T>>>)vector.sparse_elements();
		}else{
			value = matrix.data;
		}	
	}		
	
	@Override
	public boolean hasNext() {
		if(k==order.length) return true;
		if( k+1<order.length && inner_iter != null && inner_iter.hasNext() ) return true;
		boolean flag = false;
		while(!flag && iter.hasNext() ){
			SparseElement<SparseMatrix<T>> element = iter.next();
			indices[order[k]] = element.index();
			inner_iter = new SparseMatrixElementsIterator<T>(element.value(), order, reduce, k+1, indices);
			flag = inner_iter.hasNext();				
		}
		return flag;
	}

	@Override
	public SparseMatrixElement<T> next() throws NoSuchElementException {
		if( inner_iter != null ){
			inner_iter.next();
			value = inner_iter.value;
		}
		return new SparseMatrixElement<T>(indices, value);
	}
}