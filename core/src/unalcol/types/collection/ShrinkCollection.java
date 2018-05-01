package unalcol.types.collection;

public interface ShrinkCollection<K,T> extends SearchCollection<K,T>, CleanableCollection{
	/**
	 * Removes a data element from the structure
	 * @param data Data element to be removed
	 * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
	 */
	public default boolean del(T data){ return remove(find(data)); }

	/**
	 * Removes the next element returned by the iterator
	 * @param locator Location of the object to be deleted in the structure
	 * @return <i>true</i> if the next element returned by the iterator could be removed, <i>false</i> otherwise
	 */
	public boolean remove( K locator );
}