package unalcol.types.collection;

import java.util.Iterator;

/**
 * <p>Title: Collection</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface Collection<T> extends Iterable<T> {
	/**
	 * Determines if the data structure is empty or not
	 * @return <i>true</i> if the data structure is empty <i>false</i> otherwise
	 */
	public boolean isEmpty();      
	
	@SuppressWarnings("unchecked")
	public default UnalcolIterator<?, T> unalcol(){
		Iterator<T> iter = iterator();
		UnalcolIterator<?, T> riter;
		if( iter instanceof UnalcolIterator) riter = (UnalcolIterator<?, T>)iter;
		else riter = new ShortTermMemoryIterator<Integer, T>(iter) {
			@Override
			protected Integer key(T data) { return pos; }
		};		
		return riter;
	}
}