package unalcol.types.collection.vector;

import java.util.NoSuchElementException;

import unalcol.types.collection.Location;
import unalcol.types.collection.SearchCollection;
import unalcol.types.collection.array.Array;
import unalcol.types.collection.array.ArrayLocation;


public class ImmutableVector<T> implements Array<T>, SearchCollection<T> {
	protected T[] buffer;
	protected int size;

	public ImmutableVector( T[] buffer ){
		this.buffer=buffer;
		size=buffer.length;
	}

	public int findIndex( T data ){
		int k=0;
		while( k<size && !data.equals(this.buffer[k]) ){ k++; }
		return (k==size)?-1:k;
	}

	public T get( int index ) throws IndexOutOfBoundsException{
		if( index >= size ) throw new IndexOutOfBoundsException();
		return buffer[index];
	}

	public int size(){ return size; }

	protected ImmutableVector<T> self(){ return this; }

	/**
	 * Locates the given object in the structure
	 * @param data Data object to be located
	 * @return A data iterator starting at the given object (when the next method is called),
	 * If the element is not in the data structure the hasNext method will return an exception
	 */
	@Override
	public Location<T> find(T data){
		return new ArrayLocation<T>( findIndex(data), this );
	}

	/**
	 * Determines if the given object belongs to the structure
	 * @param data Data object to be located
	 * @return <i>true</i>If the objects belongs to the structure, <i>false>otherwise</i>
	 */
	@Override
	public boolean contains( T data ){
		try{
			find(data).get();
			return true;
		}catch( NoSuchElementException e ){ return false; }
	}

	@SuppressWarnings("unchecked")
	protected T[] create( int n ){ return (T[])new Object[n]; }
   
	public T[] toArray(){
		T[] x = create(size);
		System.arraycopy(buffer, 0, x, 0, size);
		return x;
	}
}