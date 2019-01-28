package unalcol.types.collection;

import java.util.Iterator;

public class CollectionFromIterator<T> implements Collection<T>{
	protected Iterator<T> iterator;
	protected boolean empty;

	public CollectionFromIterator(Iterator<T> iterator){
		this.iterator = iterator;
		empty = !iterator.hasNext();
	}

	@Override
	public Iterator<T> iterator(){ return iterator; }

	@Override
	public boolean isEmpty(){ return empty; }
}