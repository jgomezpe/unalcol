package unalcol.collection.keymap;

import java.util.Iterator;

import unalcol.collection.Collection;
import unalcol.collection.KeyMap;
import unalcol.constants.InnerCore;
import unalcol.exception.ParamsException;

public class HashMap<K,V> implements KeyMap<K,V>{
	protected java.util.HashMap<K, V> table = new java.util.HashMap<K,V>();

	// KeyMap methods
	public boolean set( K key, V value ){
		table.put(key, value);
		return true;
	}

	public boolean remove( K key ){ return (table.remove(key)!=null); }

	@Override
	public V get(K key) throws ParamsException{
		if(valid(key)) return table.get(key);
		throw new ParamsException(InnerCore.NOSUCHELEM, key);
	}
	
	public void merge(KeyMap<K, V> newKeyValues ){
		try{ if(newKeyValues!=null) for( K key:newKeyValues.keys() ) table.put(key, newKeyValues.get(key)); }catch(Exception e){}
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

	public boolean valid(K key){ return table.containsKey(key); }
}