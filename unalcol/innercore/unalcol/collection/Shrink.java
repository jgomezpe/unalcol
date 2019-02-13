package unalcol.collection;

public interface Shrink<K,T> extends Search<K,T>, Cleanable{
	/**
	 * Removes a data element from the structure
	 * @param data Data element to be removed
	 * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
	 */
	public default boolean del(T data){ try{ return remove(find(data)); }catch(Exception e){ return false; } }

	/**
	 * Removes the next element returned by the iterator
	 * @param locator Location of the object to be deleted in the structure
	 * @return <i>true</i> if the next element returned by the iterator could be removed, <i>false</i> otherwise
	 */
	public boolean remove( K locator );
}