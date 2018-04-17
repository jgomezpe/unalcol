package unalcol.types.collection;

import java.util.Iterator;

public class CollectionFromIterator<T> implements Collection<T>{
	protected Iterator<T> iterator;

	public CollectionFromIterator(Iterator<T> iterator) { this.iterator = iterator; }

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
