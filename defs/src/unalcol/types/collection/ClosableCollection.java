package unalcol.types.collection;

public interface ClosableCollection<T> extends Collection<T> {
	public default void close() throws Exception{}; 
}