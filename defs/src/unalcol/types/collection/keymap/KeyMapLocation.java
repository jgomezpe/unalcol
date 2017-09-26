package unalcol.types.collection.keymap;

import java.util.Iterator;
import java.util.NoSuchElementException;

import unalcol.types.collection.Location;

public class KeyMapLocation<K,V> implements Location<V>{
	protected K key;
	protected ImmutableKeyMap<K,V> map;
	
	public KeyMapLocation(ImmutableKeyMap<K,V> map, K key){
		this.map = map;
		this.key = key; 
	}

	@Override
	public V get() throws NoSuchElementException{ return map.get(key); }

	@Override
	public Iterator<V> iterator() {
		V v = get();
		Iterator<V> iter = map.iterator();
		boolean flag=false;
		while(iter.hasNext()&&!flag) flag=iter.next().equals(v);
		return iter;
	}
}