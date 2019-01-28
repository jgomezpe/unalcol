package unalcol.types.collection.array;

import java.util.Iterator;

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
	public default Iterator<T> iterator(){ 
		return new ArrayIterator<T>(0, this); 
	}
	
	public default Object[] toArray() {
		Object[] array = new Object[size()];
		for( int i=0; i<array.length; i++ ) array[i] = get(i);
		return array;
	}
	
	@Override
	public default boolean valid(Integer key){ return key!=null && 0<=key && key<size(); }	
}