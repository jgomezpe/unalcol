package unalcol.types.collection.keymap;

import unalcol.types.collection.MutableCollection;

public interface KeyMap<K,V> extends ImmutableKeyMap<K,V>, MutableCollection<K,V>{
	// Mutable collection methods 
	@Override
	public default boolean del(V data) { return remove(find(data)); }
	
	// KeyMap own methods
	/**
	 * Sets an element with the given map into the KeyMap
	 * @param key Key used for setting the object 
	 * @param value The element to be set
	 * @return <i>true</i> if the element was set, <i>false</i> otherwise
	 */
	public boolean set( K key, V value );

	/**
	 * Sets an element into the KeyMap using the given key
	 * @param pair Location,Value pair for the setting process
	 * @return <i>true</i> if the element was set using the given key, <i>false</i> otherwise
	 */
	public default boolean set( KeyValue<K,V> pair ){ return set(pair.key(), pair.value()); }
}