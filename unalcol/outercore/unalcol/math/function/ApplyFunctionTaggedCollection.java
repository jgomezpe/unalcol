package unalcol.math.function;

import java.util.Iterator;

import unalcol.collection.Collection;
import unalcol.object.Tagged;

public class ApplyFunctionTaggedCollection<S,T>  implements Collection<T>{
	protected Function<S,T> function;
	protected Collection<Tagged<S>> collection=null;
    
	protected class ApplyFunctionTaggedCollectionIterator implements Iterator<T>{
		protected Iterator<Tagged<S>> iterator;
		public ApplyFunctionTaggedCollectionIterator(){ this.iterator = collection.iterator(); }
		
		@Override
		public boolean hasNext(){ return iterator.hasNext(); }
		
		@Override
		public T next(){ return function.apply(iterator.next()); }
	}
    
	public ApplyFunctionTaggedCollection(Function<S,T> function, Collection<Tagged<S>> collection) {
		this.function = function;
		this.collection = collection;
	} 
    
	@Override
	public Iterator<T> iterator(){ return new ApplyFunctionTaggedCollectionIterator(); }

	@Override
	public boolean isEmpty(){ return collection.isEmpty(); }
}