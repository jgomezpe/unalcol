package unalcol.collection;

import java.util.Iterator;

import unalcol.clone.Cloneable;
import unalcol.sort.Comparable;
import unalcol.sort.Comparator;
import unalcol.collection.array.Fibo;
import unalcol.constants.InnerCore;
import unalcol.exception.ParamsException;

public class Vector<T> extends Fibo implements Array<T>, Cloneable{
	protected T[] buffer;
	
	public Vector( Vector<T> source ){ this(source.buffer, source.size()); }
	
	public Vector( T[] buffer ){ this(buffer, buffer.length); }

	public Vector( T[] buffer, int s ){
		this.buffer = buffer;
		size = s;
		find_fib(buffer.length);
	}

	@SuppressWarnings("unchecked")
	public Vector(){ this( (T[])new Object[DEFAULT_C], 0 ); }

	public T[] buffer(){ return buffer; }
	
	public Comparator value_comparator( T value ){ return Comparable.service(value); }
	
	@Override
	public Integer find( T value ) throws ParamsException{
		Comparator comp = value_comparator(value);
		int size = this.size();
		for( int i=0; i<size; i++ ) if( comp.eq( buffer[i], value ) ) return i;
		throw new ParamsException(InnerCore.NOSUCHELEM, value);
	}	

	/**
	 * Inserts a data element in the structure
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
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
		try{
			Integer k = find( data );
			leftShift(k);
			return true;
		}catch( Exception e ){ return false; }
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

	public boolean set( Integer index, T data ){
		try{
			buffer[index]=data;
			return index<size;
		}catch(ArrayIndexOutOfBoundsException e){}
		return false;
	}

	public boolean add( Integer index, T data ){
		rightShift(index);
		buffer[index]=data;
		return true;
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
	public boolean remove( Integer locator ){
		if(0<=locator && locator<size()){
			leftShift( locator );
			return true;
		}	
		return false;
	}

	@Override
	public T get(Integer key) throws ParamsException{
		try{ return buffer[key]; }catch(IndexOutOfBoundsException e ){ throw new ParamsException(InnerCore.NOSUCHELEM, key); } 
	}

	public Object[] toArray() {
		@SuppressWarnings({ "unchecked" })
		T[] new_buffer = (T[])new Object[size];
		System.arraycopy(buffer, 0, new_buffer, 0, size);
		return new_buffer;		
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<T>() {
			protected int pos=0;
			@Override
			public boolean hasNext(){ return pos<size; }

			@Override
			public T next(){ return buffer[pos++]; }
		};
	}

	@Override
	public boolean isEmpty(){ return size==0; }
		
	@SuppressWarnings("unchecked")
	@Override
	public Object clone(){
		Cloneable c = Cloneable.cast(buffer);
		return new Vector<T>((T[])c.clone(), size());
	}	
}