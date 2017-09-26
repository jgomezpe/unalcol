package unalcol.types.collection.keymap;

import java.util.Iterator;
import java.util.NoSuchElementException;

import unalcol.types.collection.Collection;
import unalcol.types.collection.FiniteCollection;
import unalcol.types.collection.Location;
import unalcol.types.collection.SearchCollection;

public interface ImmutableKeyMap<K,V> extends FiniteCollection<V>, SearchCollection<V>{
	// Search collection methods 
	public default K findKey( V value ){
		Collection<K> keys = keys();
		for( K key:keys ) if( get(key).equals(value) ) return key;
		return null;
	}

	/**
	 * Locates the given object in the structure
	 * @param data Data object to be located
	 * @return A data iterator starting at the given object (when the next method is called),
	 * If the element is not in the data structure the get method will return an exception
	 */
	public default Location<V> find(V data) throws NoSuchElementException{
		K key = findKey(data);
		if( key != null ) return new KeyMapLocation<K, V>(this, key );
		throw new NoSuchElementException();
	}

	@Override
	public default boolean contains(V data){ return findKey(data)!=null; }
	
	// KeyMap own methods
	public V get( K key );
	public Collection<K> keys();

	public default Collection<KeyValue<K, V>> pairs(){
		return new Collection<KeyValue<K, V>>() {
			protected Collection<K> keys=keys();
			
			@Override
			public Iterator<KeyValue<K, V>> iterator() {
				// TODO Auto-generated method stub
				return new Iterator<KeyValue<K,V>>() {
					protected Iterator<K> inner = keys.iterator();
					@Override
					public boolean hasNext(){ return inner.hasNext(); }

					@Override
					public KeyValue<K, V> next() {
						K key = inner.next();
						return new KeyValue<K,V>(key, get(key));
					}
				};
			}

			@Override
			public boolean isEmpty(){ return keys.isEmpty(); }			
		};	
	}
}