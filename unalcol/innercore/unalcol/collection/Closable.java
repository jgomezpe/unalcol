package unalcol.collection;

import unalcol.exception.ParamsException;

public interface Closable<T> extends Collection<T> {
	public default void close() throws ParamsException{}; 
}