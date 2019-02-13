package unalcol.collection.sparse.matrix;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SparseMatrixIterator<T> implements Iterator<T>{
	protected Iterator<SparseMatrixElement<T>> inner_iter;
	
	public SparseMatrixIterator( SparseMatrix<T> matrix, int[] order ) {
		inner_iter = new SparseMatrixElementsIterator<T>(matrix, order);
	}
	
	@Override
	public boolean hasNext() {
		return inner_iter.hasNext();
	}

	@Override
	public T next() throws NoSuchElementException {
		return inner_iter.next().value();
	}
}