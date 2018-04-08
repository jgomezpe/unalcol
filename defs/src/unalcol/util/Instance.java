package unalcol.util;

public interface Instance<T> {
	public T load( Object[] args );
	public Object[] store( T obj );
}