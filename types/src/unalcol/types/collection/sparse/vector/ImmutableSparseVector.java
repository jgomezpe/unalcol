/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.sparse.vector;

import java.util.Iterator;

import unalcol.clone.Clone;
import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.vector.SortedVector;

/**
 *
 * @author jgomez
 */
public class ImmutableSparseVector<T> implements ArrayCollection<T>{
	protected SortedVector<SparseElement<T>> vector;
    protected SparseElement<T> loc = new SparseElement<T>(0, null);
        
    public ImmutableSparseVector( SortedVector<SparseElement<T>> vector ){
        this.vector = vector; // new SortedVector(new SparseElementOrder());
    }
    
    @SuppressWarnings("unchecked")
    public ImmutableSparseVector( ImmutableSparseVector<T> sparse ) {
	this.vector = (SortedVector<SparseElement<T>>)Clone.create(sparse.vector);
    }
    
    public boolean stored( int index ){
	return findIndex(index) >= 0;
    }
    
    public int findIndex( int index ){
        loc.index = index;
        return vector.findIndex(loc);
    }
    
    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        return vector.get(findIndex(index)).value();
    }

    @Override
    public Iterator<T> iterator() {
        return new SparseVectorIterator<>(0, this);
    }

    public Iterator<SparseElement<T>> sparse_elements() {
        return vector.iterator();
    }

    public int locate(T data) {
        int k=0;
        while( k<size() && !data.equals(vector.get(k).value()) ){ k++; }
        return (k==size())?-1:k;
    }
    
    @Override
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    @Override
    public int size() {
        return vector.size();
    }
    
    public int low(){
	if( !isEmpty()){ return vector.get(0).index; }
	return Integer.MAX_VALUE;
    }
    
    public int high(){
	if( !isEmpty()){ return vector.get(vector.size()-1).index; }
	return Integer.MIN_VALUE;
    }
       
    public SortedVector<SparseElement<T>> sparseVector(){
        return vector;
    }
    
    @SuppressWarnings("rawtypes")
    public ArrayCollection<Integer> indices(){
	return new SparseVectorIndices((SparseVector)this);
    }
}
