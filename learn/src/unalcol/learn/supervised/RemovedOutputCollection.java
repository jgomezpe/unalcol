/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.supervised;

import java.util.Iterator;

import unalcol.types.collection.Collection;

/**
 *
 * @author jgomez
 */
public class RemovedOutputCollection<S,T> implements Collection<S>{
    protected Collection<InputOutputPair<S,T>> labeled;
    
    public RemovedOutputCollection( Collection<InputOutputPair<S,T>> labeled ){
        this.labeled = labeled;
    }
    
    protected class RemovedOutputIterator<U,V> implements Iterator<U>{
	protected Iterator<InputOutputPair<U,V>> inner;
	
	public  RemovedOutputIterator(Iterator<InputOutputPair<U,V>> inner) {
	   this.inner = inner;
	}
	
	@Override
	public boolean hasNext() {
	    // TODO Auto-generated method stub
	    return inner.hasNext();
	}

	@Override
	public U next() {
	    return inner.next().input();
	}
    }
    
    @Override
    public Iterator<S> iterator() {
	return new RemovedOutputIterator<S,T>(labeled.iterator());
    }

    @Override
    public boolean isEmpty() {
        return labeled.isEmpty();
    }
}