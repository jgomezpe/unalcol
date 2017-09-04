package unalcol.types.collection.keymap;

import java.util.Iterator;

import unalcol.types.collection.FiniteCollection;
import unalcol.types.collection.MutableCollection;
import unalcol.types.collection.SearchCollection;

public interface KeyMap<K,V> extends FiniteCollection<KeyValue<K,V>>, MutableCollection<KeyValue<K,V>>, SearchCollection<KeyValue<K,V>>{
	public default void put( K key, V value ){ add(new KeyValue<K, V>(key, value)); }
	public default void remove( K key ){ del(new KeyValue<K, V>(key, null)); }
	public V get( K key );
	public Iterator<K> keys();
	public Iterator<V> values();
}