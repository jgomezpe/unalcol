package unalcol.collection.sparse.matrix;

public class SparseMatrixElement<T> {
	protected int[] pos;
	protected T value;
	public SparseMatrixElement( int[] pos, T value ){
		this.pos = pos;
		this.value = value;
	}
	
	public T value(){ return value; }
	
	public int[] indices(){ return pos; }
}
