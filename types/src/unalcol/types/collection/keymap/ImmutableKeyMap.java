package unalcol.types.collection.keymap;

import java.util.Iterator;
import unalcol.types.collection.Collection;
import unalcol.types.collection.FiniteCollection;
import unalcol.types.collection.SearchCollection;
import unalcol.sort.Comparator;
import unalcol.sort.Comparable;

public interface ImmutableKeyMap<K,V> extends FiniteCollection<V>, SearchCollection<K,V>{
	default Comparator value_comparator( V value ){ return Comparable.service(value); }
/*
  	import java.lang.reflect.ParameterizedType;
	default Class<?> returnedClass( int i ) {
	     ParameterizedType parameterizedType = (ParameterizedType)getClass()
	                                                 .getGenericSuperclass();
	     return (Class<?>) parameterizedType.getActualTypeArguments()[i];
	}
*/
	
	// Search collection methods 
	/**
	 * Locates the given object in the structure
	 * @param data Data object to be located
	 * @return A data iterator starting at the given object (when the next method is called),
	 * If the element is not in the data structure the get method will return an exception
	 */
	@Override
	public default K find( V value ){
		Comparator comp = value_comparator(value);
		for( K key:keys() ) if( comp.eq( get(key), value ) ) return key;
		return null;
	}

	// KeyMap own methods
	public Collection<K> keys();

	@Override
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