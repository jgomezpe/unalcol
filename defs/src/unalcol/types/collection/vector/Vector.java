package unalcol.types.collection.vector;

import unalcol.types.collection.Location;
import unalcol.types.collection.array.ArrayLocation;
import unalcol.types.collection.array.Array;
import unalcol.types.object.FiboArray;

public class Vector<T> extends FiboArray implements Array<T>{
	protected T[] buffer;
	protected int size;

	public Vector( T[] buffer ){ this(buffer, buffer.length); }

	public Vector( T[] buffer, int s ){
		this.buffer = buffer;
		size = s;
		find_fib(buffer.length);
	}

	@SuppressWarnings("unchecked")
	public Vector(){ this( (T[])new Object[DEFAULT_C], 0 ); }

	@Override
	public void clear(){
		super.clear();
		size = 0;
	}

	/**
	 * Inserts a data element in the structure
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	@Override
	public boolean add(T data){
		if( buffer.length == size ) grow();
		buffer[size]=data;
		size++;
		return true;
	}

	/**
	 * Inserts a data element in the structure
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	public boolean del(T data) {
		int k = findKey( data );
		if( k != -1 ){
			leftShift(k);
			return true;
		}
		return false;
	}

	protected void leftShift( int index ) throws IndexOutOfBoundsException{
		size--;
		if( index < size ) System.arraycopy(buffer, index+1, buffer, index, size-index);
		if( size < a ) shrink();
	}

	protected void rightShift( int index ) throws IndexOutOfBoundsException{
		if( buffer.length == size ) grow();
		System.arraycopy(buffer, index, buffer, index+1, buffer.length-index-1);
		size++;
	}

	public boolean set( int index, T data ) throws IndexOutOfBoundsException{
		if( 0 <= index && index < size ){
			buffer[index]=data;
			return true;
		}else{ throw new ArrayIndexOutOfBoundsException( index ); }
	}

	public boolean add( int index, T data ) throws IndexOutOfBoundsException{
		rightShift(index);
		buffer[index]=data;
		return true;
	}

	public boolean remove( int index ) throws IndexOutOfBoundsException{
		leftShift(index);
		return true;
	}

	public int size(){ return size; }

	/**
	 * Sets the size of the array
	 * @param n The new size of the array
	 */
	public void resize(int size){
		super.resize(size);
		this.size = size; 
	}
	
	/**
	 * Sets the size of the array
	 * @param n The new size of the array
	 */
	public void resize(){
		@SuppressWarnings({ "unchecked" })
		T[] new_buffer = (T[])new Object[c];
		System.arraycopy(buffer, 0, new_buffer, 0, Math.min(size,c));
		buffer = new_buffer;		
	}  

	/**
	 * Removes the element indicated by the locator
	 * @param locator The location information of the object to be deleted
	 * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
	 */
	@Override
	public boolean del( Location<T> locator ){
		if( locator instanceof ArrayLocation ){
			ArrayLocation<T> loc = ((ArrayLocation<T>)locator);
			leftShift( loc.getPos() );
			return true;
		}
		return false;
	}

	@Override
	public T get( int index ) throws IndexOutOfBoundsException{
		if( index >= size ) throw new IndexOutOfBoundsException();
		return buffer[index];
	}


	@Override
	public Object[] toArray() {
		@SuppressWarnings({ "unchecked" })
		T[] new_buffer = (T[])new Object[size];
		System.arraycopy(buffer, 0, new_buffer, 0, size);
		return new_buffer;		
	}
}