package unalcol.collection.sparse.matrix;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SparseMatrixIndicesIterator implements Iterator<int[]>{
	protected Iterator<SparseMatrixElement<Object>> inner_iter;
	
	public SparseMatrixIndicesIterator( SparseMatrix<Object> matrix, int[] order ) {
		inner_iter = new SparseMatrixElementsIterator<Object>(matrix, order);
	}
	
	@Override
	public boolean hasNext() {
		return inner_iter.hasNext();
	}

	@Override
	public int[] next() throws NoSuchElementException {
		return inner_iter.next().indices();
	}
}