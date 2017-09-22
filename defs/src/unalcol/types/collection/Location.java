package unalcol.types.collection;

import java.util.Iterator;

/**
 * <p>Title: Location</p>
 *
 * <p>Description: Creates a Locator for </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface Location<T> {
	public T get();
	/**
	 * Obtains an iterator of the objects in the structure starting at the given Locator
	 * @return Iterator of the objects in the structure starting at the given Locator
	 */
	public Iterator<T> iterator();
}