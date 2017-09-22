package unalcol.types.collection.keymap;

import unalcol.types.collection.Location;
import unalcol.types.collection.MutableCollection;

public interface KeyMap<K,V> extends ImmutableKeyMap<K,V>, MutableCollection<V>{
	// Mutable collection methods 
	@Override
	public default boolean del(Location<V> locator){
		if( locator instanceof KeyMapLocation ){
			@SuppressWarnings("unchecked")
			KeyMapLocation<K, V> loc = (KeyMapLocation<K, V>)locator;
			return remove(loc.key); 
		}
		return false;
	}

	@Override
	public default boolean del(V data) {
		K key = findKey(data);
		if( key != null ) return remove(key);
		return false;
	}
	
	// KeyMap own methods
	public boolean set( K key, V value );
	public boolean remove( K key );
	public boolean add( K key, V value);

	public default boolean set( KeyValue<K,V> pair ){ return set(pair.key(), pair.value()); }
	public default boolean add( KeyValue<K,V> pair ){ return add(pair.key(), pair.value()); }	
}