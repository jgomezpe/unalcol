package unalcol.types.collection.sparse.vector;

import java.util.Iterator;

import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.array.ArrayCollectionIterator;

public class SparseVectorIndices implements ArrayCollection<Integer>{
    @SuppressWarnings("rawtypes")
    protected SparseVector sparse;
    
    public SparseVectorIndices(@SuppressWarnings("rawtypes") SparseVector sparse) {
	this.sparse = sparse;
    }
    @Override
    public int size() {
	return sparse.size();
    }

    @Override
    public boolean isEmpty() {
	return sparse.isEmpty();
    }

    @Override
    public Iterator<Integer> iterator() {
	return new ArrayCollectionIterator<Integer>(0,this);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Integer get(int index) throws ArrayIndexOutOfBoundsException {
	return ((SparseElement)sparse.vector.get(index)).index();
    }
}