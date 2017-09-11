package unalcol.types.collection.array;

import java.util.Iterator;

import unalcol.types.collection.*;

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
public interface Array<T> extends FiniteCollection<T>{
	/**
	 * Gets the object at the given position
	 * @param index int
	 * @return T
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public T get( int index ) throws ArrayIndexOutOfBoundsException;        

	@Override
	public default Iterator<T> iterator(){ return new ArrayIterator<T>(0, this); }

	public default Location<T> getLocation(int index){ return new ArrayLocation<>(index, this); }        
}