package unalcol.object;

import java.util.Iterator;

import unalcol.collection.Collection;

public interface TaggedManager<T> {
	/**
	 * Sets the object that is being tagged. Removes all the non TaggedMethods associated to this object.
	 * @param object Object that is being tagged.
	 */
	default Tagged<T> wrap( T object ){ return new Tagged<T>(object); }
	
	/**
	 * Gets the object that is being tagged.
	 * @return tagged object.
	 */
	default T unwrap( Tagged<T> obj ){ return obj.unwrap(); }
	
	/**
	 * Obtains the set of objects that are actually tagged.
	 * @param obj Set of TaggedObject 's.
	 * @return Actual objects (without tags).
	 */
	default T[] unwrap( @SuppressWarnings("unchecked") Tagged<T>... obj ){ 
		@SuppressWarnings("unchecked")
		T[] t_obj = (T[])new Object[obj.length];
		for( int i=0; i<obj.length; i++ ) t_obj[i] = obj[i].unwrap();
		return t_obj;
	}  
	
	/**
	 * Creates a TaggedObject array from an array of (possibly non tagged) objects.
	 * @param obj Array of (possibly non tagged) objects to be Tagged.
	 * @return A TaggedObject array from an array of (possibly non tagged) objects.
	 */
	default Tagged<T>[] wrap( @SuppressWarnings("unchecked") T... obj ){
		@SuppressWarnings("unchecked")
		Tagged<T>[] t_obj = new Tagged[obj.length];
		for( int i=0; i<obj.length; i++ ) t_obj[i] = wrap(obj[i]);
		return t_obj;
	}
	
	default Collection<T> unwrap( Collection<Tagged<T>> col ){
		return new Collection<T>() {
			@Override
			public Iterator<T> iterator() {
				Iterator<Tagged<T>> iter = col.iterator();
				return new Iterator<T>() {
					@Override
					public boolean hasNext(){ return iter.hasNext(); }

					@Override
					public T next() { return iter.next().unwrap(); }
				};
			}

			@Override
			public boolean isEmpty(){ return col.isEmpty();	}
		};
	}
	
	default Collection<Tagged<T>> wrap( Collection<T> col ){
		return new Collection<Tagged<T>>() {
			@Override
			public Iterator<Tagged<T>> iterator() {
				Iterator<T> iter = col.iterator();
				return new Iterator<Tagged<T>>() {
					@Override
					public boolean hasNext(){ return iter.hasNext(); }

					@Override
					public Tagged<T> next() { return wrap(iter.next()); }
				};
			}

			@Override
			public boolean isEmpty(){ return col.isEmpty();	}
		};
	}
}