package unalcol.types.collection;

public interface GrowCollection<T> extends Collection<T>{
	/**
	 * Inserts a data element in the structure
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	public boolean add(T data);
}