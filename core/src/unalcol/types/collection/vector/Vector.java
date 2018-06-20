package unalcol.types.collection.vector;

import unalcol.types.collection.array.Array;
import unalcol.types.object.array.FiboArray;

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
		Integer k = find( data );
		if( k != null ){
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

	@Override
	public boolean set( Integer index, T data ){
		try{
			buffer[index]=data;
			return index<size;
		}catch(ArrayIndexOutOfBoundsException e){}
		return false;
	}

	@Override
	public boolean add( Integer index, T data ){
		rightShift(index);
		buffer[index]=data;
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
	public boolean remove( Integer locator ){
		if(0<=locator && locator<size()){
			leftShift( locator );
			return true;
		}	
		return false;
	}

	@Override
	public T get(Integer key){ return buffer[key]; }

	@Override
	public Object[] toArray() {
		@SuppressWarnings({ "unchecked" })
		T[] new_buffer = (T[])new Object[size];
		System.arraycopy(buffer, 0, new_buffer, 0, size);
		return new_buffer;		
	}
}