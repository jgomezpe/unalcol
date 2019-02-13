package unalcol.learn.supervised;

import java.util.Iterator;

import unalcol.types.collection.Collection;

public class RemovedInputCollection<S,T>   implements Collection<T>{
   protected Collection<InputOutputPair<S,T>> labeled;
    
    public RemovedInputCollection( Collection<InputOutputPair<S,T>> labeled ){
        this.labeled = labeled;
    }
    
    protected class RemovedInputIterator<U,V> implements Iterator<V>{
	protected Iterator<InputOutputPair<U,V>> inner;
	
	public  RemovedInputIterator(Iterator<InputOutputPair<U,V>> inner) {
	   this.inner = inner;
	}
	
	@Override
	public boolean hasNext() {
	    return inner.hasNext();
	}

	@Override
	public V next() {
	    return inner.next().output();
	}
    }
    
    @Override
    public Iterator<T> iterator() {
	return new RemovedInputIterator<S,T>(labeled.iterator());
    }

    @Override
    public boolean isEmpty() {
        return labeled.isEmpty();
    }
}