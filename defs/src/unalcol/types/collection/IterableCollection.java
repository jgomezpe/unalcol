package unalcol.types.collection;

import java.util.Iterator;

public interface IterableCollection<T> extends Collection<T> {
    /**
     * Obtains an iterator of the objects in the structure starting at the given Locator
     * @param locator Locator used for starting the iterator, the first element returned by the iterator is the one in the locator
     * (if some one is in the locator)
     * @return Iterator of the objects in the structure starting at the given Locator
     */
	public Iterator<T> iterator( Location<T> locator );

	/**
	 * Obtains an iterator of the objects in the structure 
	 * @return Iterator of the objects in the structure 
	 */
	public Iterator<T> iterator(); 
}