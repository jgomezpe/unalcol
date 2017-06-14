package unalcol.math.function;

import java.util.Iterator;

import unalcol.types.collection.Collection;

public class ApplyFunctionCollection<S,T> implements Collection<T> {
    protected Function<S,T> function;
    protected Collection<S> collection;
    
    protected class ApplyFunctionCollectionIterator<U,V> implements Iterator<V>{
	protected Function<U,V> function;
	protected Iterator<U> iterator;
	public ApplyFunctionCollectionIterator(ApplyFunctionCollection<U, V> collection) {
		this.function = collection.function;
		this.iterator = collection.collection.iterator();
	}
	@Override
	public boolean hasNext() {
	    return iterator.hasNext();
	}
	@Override
	public V next() {
	    return function.apply(iterator.next());
	}
    }
    
    public ApplyFunctionCollection(Function<S,T> function, Collection<S> collection) {
	this.function = function;
	this.collection = collection;
    } 
    
    @Override
    public Iterator<T> iterator() {
	return new ApplyFunctionCollectionIterator<S,T>(this);
    }

    @Override
    public boolean isEmpty() {
	return collection.isEmpty();
    }
}