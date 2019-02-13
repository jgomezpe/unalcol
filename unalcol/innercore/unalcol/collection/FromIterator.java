package unalcol.collection;

import java.util.Iterator;

public class FromIterator<T> implements Collection<T>{
	protected Iterator<T> iterator;
	protected boolean empty;

	public FromIterator(Iterator<T> iterator){
		this.iterator = iterator;
		empty = !iterator.hasNext();
	}

	@Override
	public Iterator<T> iterator(){ return iterator; }

	@Override
	public boolean isEmpty(){ return empty; }
}