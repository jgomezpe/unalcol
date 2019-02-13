/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.collection;

import unalcol.collection.keymap.KeyValue;
import unalcol.exception.ParamsException;

/**
 *
 * @author jgomez
 */
public interface Search<K,T> extends Collection<T> {
	/**
	 * Obtains the location of the object into the search collection
	 * @param data Data object to be located
	 * @return A location (collection dependent) where the object is located,
	 *  <i>null</i> if the object is not in the search collection
	 */
	public K find(T data)  throws ParamsException;
	
	public boolean valid( K key );
	
	public T get( K key ) throws ParamsException;
	
	public Collection<KeyValue<K, T>> pairs();

	/**
	 * Determines if the given object belongs to the structure
	 * @param data Data object to be located
	 * @return <i>true</i>If the objects belongs to the structure, <i>false>otherwise</i>
	 */
	public default boolean contains( T data ){
		try{
			find(data);
			return true;
		}catch(Exception e){ return false; }
	}     
}