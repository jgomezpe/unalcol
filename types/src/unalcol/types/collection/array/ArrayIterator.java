/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.array;

import java.util.NoSuchElementException;

import unalcol.types.collection.iterator.PositionTrack;
import unalcol.types.collection.iterator.UnalcolIterator;

/**
 *
 * @author jgomez
 */
public class ArrayIterator<T>  implements UnalcolIterator<T> {
	protected int pos=-1;
	protected ImmutableArray<T> array;

	public ArrayIterator( int pos, ImmutableArray<T> array ) {
		this.array = array;
		this.pos = pos-1;
	}

	@Override
	public boolean hasNext(){
		return pos+1<array.size();
	}

	@Override
	public T next() throws NoSuchElementException{
		try{
			pos++;
			return array.get(pos);
		}catch( Exception e ){ throw new NoSuchElementException( "" + pos ); }
	}

	@Override
	public void remove() {
		if( array instanceof Array ){
			((Array<T>)array).remove(pos);
		}
	}

	@Override
	public PositionTrack pos() { return new PositionTrack(0,pos); }

	@Override
	public int maxBack() { return pos+1; }

	@Override
	public boolean back(int k) {
		if(0<k && k<=maxBack()){
			pos -= k;
		}
		return false;
	}
}