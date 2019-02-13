package unalcol.collection;

public interface Grow<T> extends Collection<T>{
	/**
	 * Inserts a data element in the structure
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	public boolean add(T data);
}