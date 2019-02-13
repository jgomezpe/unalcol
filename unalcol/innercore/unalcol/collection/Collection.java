package unalcol.collection;

import java.util.Iterator;

import unalcol.iterator.Backable;
import unalcol.iterator.Core;
import unalcol.iterator.PositionTrack;

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
	
	public default Backable<T> unalcol(){
		Iterator<T> iter = iterator();
		Backable<T> riter;
		if( iter instanceof Backable) riter = (Backable<T>)iter;
		else riter = new Core<T>(iter) {
			@Override
			protected PositionTrack pos(T data) { return pos(); }

			@Override
			public PositionTrack pos(){ return new PositionTrack(0,pos); }
		};		
		return riter;
	}
}