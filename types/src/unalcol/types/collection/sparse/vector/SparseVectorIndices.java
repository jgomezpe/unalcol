package unalcol.types.collection.sparse.vector;

import unalcol.types.collection.array.Array;

public class SparseVectorIndices implements Array<Integer>{
	@SuppressWarnings("rawtypes")
	protected SparseVector sparse;
    
	public SparseVectorIndices(@SuppressWarnings("rawtypes") SparseVector sparse){ this.sparse = sparse; }

	@Override
    public int size(){ return sparse.size(); }

    @Override
    public boolean isEmpty(){ return sparse.isEmpty(); }

	@SuppressWarnings("rawtypes")
	@Override
	public Integer get(int index) throws ArrayIndexOutOfBoundsException {
		return ((SparseElement)sparse.vector.get(index)).index();
	}
}