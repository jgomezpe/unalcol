package unalcol.collection.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

import unalcol.collection.Collection;
import unalcol.iterator.BT;
import unalcol.iterator.PositionTrack;

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
public interface Immutable<T> extends unalcol.collection.keymap.Immutable<Integer,T>{
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
	public default Iterator<T> iterator(){ return iterator(0); } 

	public default Iterator<T> iterator(int index){ 
		return new BT<T>(){
			
			protected int pos=index;
			
			@Override
			public boolean hasNext(){ return pos+1<size(); }

			@Override
			public T next() throws NoSuchElementException{
				try{
					pos++;
					return get(pos);
				}catch( Exception e ){ throw new NoSuchElementException( e.getMessage() ); }
			}

			@Override
			public PositionTrack pos() { return new PositionTrack(0,pos); }

			@Override
			public int maxBack() { return pos+1; }

			@Override
			public boolean back(int k) {
				if(0<k && k<=maxBack()){ pos -= k; }
				return false;
			}
		};
	}
	
	public default Object[] toArray() {
		Object[] array = new Object[size()];
		try{ for( int i=0; i<array.length; i++ ) array[i] = get(i); }catch(Exception e){}
		return array;
	}
	
	@Override
	public default boolean valid(Integer key){ return key!=null && 0<=key && key<size(); }	
}