/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection;

import java.util.NoSuchElementException;

/**
 *
 * @author jgomez
 */
public interface SearchCollection<T> extends Collection<T> {
	/**
	 * Locates the given object in the structure
	 * @param data Data object to be located
	 * @return A data iterator starting at the given object (when the next method is called),
	 * If the element is not in the data structure a NoSuchElementException is thrown
	 */
	public Location<T> find(T data) throws NoSuchElementException;

	/**
	 * Determines if the given object belongs to the structure
	 * @param data Data object to be located
	 * @return <i>true</i>If the objects belongs to the structure, <i>false>otherwise</i>
	 */
	public boolean contains( T data );       
}