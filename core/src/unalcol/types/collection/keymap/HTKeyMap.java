package unalcol.types.collection.keymap;

import java.util.HashMap;
import java.util.Iterator;

import unalcol.types.collection.Collection;

public class HTKeyMap<K,V> implements KeyMap<K, V>{
	protected HashMap<K, V> table = new HashMap<K,V>();

	// KeyMap methods
	@Override
	public boolean set( K key, V value ){
		if( value!=null && key!=null ){
			table.put(key, value);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove( K key ){ return (table.remove(key)!=null); }

	@Override
	public V get(K key){ return table.get(key); }

	@Override
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
	@Override
	public int size(){ return table.size(); }

	//Mutable collection
	@Override
	public void clear(){ table.clear(); }

	@Override
	public boolean add(V data){ return false; }

	@Override
	public boolean valid(K key){ return key!=null && table.get(key)!=null; }
}