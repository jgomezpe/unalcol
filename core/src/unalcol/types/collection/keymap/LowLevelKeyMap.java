package unalcol.types.collection.keymap;

import java.util.HashMap;
import java.util.Iterator;

import unalcol.types.collection.Collection;

public class LowLevelKeyMap<K,V> implements Collection<V>{
	protected HashMap<K, V> table = new HashMap<K,V>();

	// KeyMap methods
	public boolean set( K key, V value ){
		if( value!=null && key!=null ){
			table.put(key, value);
			return true;
		}
		return false;
	}

	public boolean remove( K key ){ return (table.remove(key)!=null); }

	public V get(K key){ return table.get(key); }
	
	public void merge( LowLevelKeyMap<K, V> newKeyValues ){
		if(newKeyValues!=null) for( K key:newKeyValues.keys() ) table.put(key, newKeyValues.get(key));
	}

	public Collection<K> keys(){ 
		return new Collection<K>(){
			@Override
			public Iterator<K> iterator(){ return table.keySet().iterator(); }

			@Override
			public boolean isEmpty(){ return table.isEmpty(); }
		}; 
	}
	
	// Collection methods
	@Override
	public boolean isEmpty(){ return table.isEmpty(); }

	@Override 
	public Iterator<V> iterator(){ return table.values().iterator(); }

	// Finite collection 
	public int size(){ return table.size(); }

	//Mutable collection
	public void clear(){ table.clear(); }

	public boolean add(V data){ return false; }

	public boolean valid(K key){ return key!=null && table.get(key)!=null; }
}