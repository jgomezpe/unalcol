/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.collection;

/**
 *
 * @author jgomez
 */
public interface Finite<T> extends Collection<T> {
	/**
	 * Determines the number of objects stored by the data structure
	 * @return Number of objects stored by the data structure
	 */
	public int size();

	@Override
	public default boolean isEmpty(){ return size()==0; }	
}