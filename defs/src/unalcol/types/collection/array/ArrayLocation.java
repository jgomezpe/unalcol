/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.array;

import java.util.Iterator;
import java.util.NoSuchElementException;
import unalcol.types.collection.Location;

/**
 *
 * @author jgomez
 */
public class ArrayLocation<T> implements Location<T> {
	protected int pos;
	protected ImmutableArray<T> array;

	public ArrayLocation( int pos, ImmutableArray<T> array ) {
		this.array = array;
		this.pos = pos;
	}

	@Override
	public T get() throws NoSuchElementException{
		try{ return array.get(pos); }catch( Exception e ){ throw new NoSuchElementException("Invalid index .." + pos); }
	}

	/**
	 * Obtains an iterator of the objects in the structure starting at the given Locator
	 * @return Iterator of the objects in the structure starting at the given Locator
	 */
	public Iterator<T> iterator(){ return new ArrayIterator<T>( pos, array ); }

	public int getPos(){ return pos; }
}