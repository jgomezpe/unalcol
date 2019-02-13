package unalcol.collection.keymap;

import java.util.Iterator;

import unalcol.sort.Comparator;
import unalcol.collection.Collection;
import unalcol.collection.Finite;
import unalcol.collection.Search;
import unalcol.constants.InnerCore;
import unalcol.exception.ParamsException;
import unalcol.sort.Comparable;

public interface Immutable<K,V> extends Finite<V>, Search<K,V>{
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
	public default K find( V value ) throws ParamsException{
		Comparator comp = value_comparator(value);
		try{ for( K key:keys() ) if( comp.eq( get(key), value ) ) return key; }catch(Exception e){}
		throw new ParamsException(InnerCore.NOSUCHELEM, value);
	}

	// KeyMap own methods
	public Collection<K> keys();

	@Override
	public default Collection<KeyValue<K, V>> pairs(){
		return new Collection<KeyValue<K, V>>() {
			protected Collection<K> keys=keys();
			
			@Override
			public Iterator<KeyValue<K, V>> iterator() {
				return new Iterator<KeyValue<K,V>>() {
					protected Iterator<K> inner = keys.iterator();
					@Override
					public boolean hasNext(){ return inner.hasNext(); }

					@Override
					public KeyValue<K, V> next() {
						K key = inner.next();
						try{ return new KeyValue<K,V>(key, get(key)); }catch(Exception e){ return null; } 	
					}
				};
			}

			@Override
			public boolean isEmpty(){ return keys.isEmpty(); }			
		};	
	}
}