package unalcol.types.collection.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

import unalcol.types.collection.*;
import unalcol.types.collection.keymap.ImmutableKeyMap;

/**
 * <p>Title: ArrayCollection</p>
 *
 * <p>Description: An array collection (indexable collection)</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface ImmutableArray<T> extends ImmutableKeyMap<Integer,T>{
	/**
	 * Gets the object at the given position
	 * @param index int
	 * @return T
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public T get( int index ) throws ArrayIndexOutOfBoundsException;        

	public default T get( Integer key ){ return get((int)key); }        

	public default Collection<Integer> keys(){
		return new Collection<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return new Iterator<Integer>(){
					protected int index=0;
					@Override
					public boolean hasNext(){ return index<size(); }

					@Override
					public Integer next() {
						int k=index;
						index++;
						return k;
					}					
				};
			}
			
			@Override
			public boolean isEmpty(){ return size()==0;	}
		};
	}
	
	@Override
	public default Iterator<T> iterator(){ return new ArrayIterator<T>(0, this); }

	public default Location<T> getLocation(int index){ return new ArrayLocation<T>(index, this); }        
	
	/**
	 * Locates the given object in the structure
	 * @param data Data object to be located
	 * @return A data iterator starting at the given object (when the next method is called),
	 * If the element is not in the data structure the hasNext method will return an exception
	 */
	@Override
	public default Location<T> find(T data)  throws NoSuchElementException{
		Integer key = findKey(data);
		if( key != null ) return new ArrayLocation<T>(key, this );
		throw new NoSuchElementException();
	}

	/**
	 * Determines if the given object belongs to the structure
	 * @param data Data object to be located
	 * @return <i>true</i>If the objects belongs to the structure, <i>false>otherwise</i>
	 */
	@Override
	public default boolean contains( T data ){
		try{
			find(data).get();
			return true;
		}catch( NoSuchElementException e ){ return false; }
	}
	
	public default Object[] toArray() {
		Object[] array = new Object[size()];
		for( int i=0; i<array.length; i++ ) array[i] = get(i);
		return array;
	}
}