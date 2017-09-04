package unalcol.types.collection.keymap;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;

import unalcol.types.collection.Location;

public class HTKeyMap<K,V> implements KeyMap<K, V>{
	protected Hashtable<K, V> table = new Hashtable<K,V>();
	
	@Override
	public boolean add(KeyValue<K, V> data) {
		table.put(data.key(), data.value());
		return false;
	}

	@Override
	public void put( K key, V value ){ table.put(key, value); }

	@Override
	public void remove( K key ){ table.remove(key); }
	
	@Override
	public boolean isEmpty(){ return table.isEmpty(); }

	@Override
	public Iterator<KeyValue<K, V>> iterator(){ return new KeyValuesIterator();	}

	@Override
	public void clear(){ table.clear(); }

	@Override
	public boolean del(KeyValue<K, V> data){ return table.remove(data.key)!=null; }

	@Override
	public boolean del(Location<KeyValue<K, V>> locator){ return del(locator.get()); }

	@Override
	public Location<KeyValue<K, V>> find(KeyValue<K, V> data){	return new HTLocation(data.key()); }

	@Override
	public boolean contains(KeyValue<K, V> data){ return get(data.key())!=null;	}

	@Override
	public V get(K key) { return table.get(key); }

	@Override
	public Iterator<K> keys(){ return new KeysIterator(); }

	@Override
	public Iterator<V> values(){ return new ValuesIterator(); }
	
	protected class KeysIterator implements Iterator<K>{
		protected Enumeration<K> iter;
		public KeysIterator(){ iter=table.keys(); }
		
		@Override
		public boolean hasNext(){ return iter.hasMoreElements(); }

		@Override
		public K next(){ return iter.nextElement();	}		
	}

	protected class ValuesIterator implements Iterator<V>{
		protected Enumeration<V> iter;
		public ValuesIterator(){ iter=table.elements(); }
		
		@Override
		public boolean hasNext(){ return iter.hasMoreElements(); }

		@Override
		public V next(){ return iter.nextElement();	}		
	}

	protected class KeyValuesIterator implements Iterator<KeyValue<K,V>>{
		protected Enumeration<K> iter;
		public KeyValuesIterator(){ iter=table.keys(); }
		
		@Override
		public boolean hasNext(){ return iter.hasMoreElements(); }

		@Override
		public KeyValue<K,V> next(){
			K key = iter.nextElement();
			return new KeyValue<K,V>(key,table.get(key));	
		}		
	}
	
	protected class HTLocation implements Location<KeyValue<K,V>>{
		protected K key;
		public HTLocation(K key){ this.key = key; }
		
		@Override
		public KeyValue<K, V> get() throws NoSuchElementException {
			return new KeyValue<K,V>(key,HTKeyMap.this.get(key));
		}

		@Override
		public Iterator<KeyValue<K, V>> iterator() {
			Iterator<KeyValue<K,V>> iter = HTKeyMap.this.iterator();
			boolean flag=false;
			while(iter.hasNext()&&!flag) flag=iter.next().key().equals(key);
			return iter;
		}
	}

	@Override
	public int size(){ return table.size(); }
}