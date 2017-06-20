package unalcol.types.collection;

public interface ShrinkCollection<T> extends Collection<T>{
	/**
	 * Removes all the objects in the data structure
	 */
	public void clear();

	/**
	 * Removes a data element from the structure
	 * @param data Data element to be removed
	 * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
	 */
	public boolean del(T data);

	/**
	 * Removes the next element returned by the iterator
	 * @param locator Location of the object to be deleted in the structure
	 * @return <i>true</i> if the next element returned by the iterator could be removed, <i>false</i> otherwise
	 */
	public boolean del( Location<T> locator );
}