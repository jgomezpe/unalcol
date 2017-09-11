package unalcol.types.collection.vector;

import unalcol.types.collection.Location;
import unalcol.types.collection.MutableCollection;
import unalcol.types.collection.array.ArrayLocation;

public class Vector<T> extends ImmutableVector<T> implements MutableCollection<T>{
	protected int a, b, c;

	protected static final int DEFAULT_C = 144;
	protected static final int DEFAULT_B = 89;
	protected static final int DEFAULT_A = 55;

	protected final void find_fib( int s ){
		a = DEFAULT_A;
		b = DEFAULT_B;
		c = DEFAULT_C;
		while(s>c){
			a=b;
			b=c;
			c=a+b;
		}
	}
    
	public Vector( T[] buffer ){ super( buffer ); }

	public Vector( T[] buffer, int s ){
		super(buffer);
		size = s;
		find_fib(size);
	}

	@SuppressWarnings("unchecked")
	public Vector(){
		this( (T[])new Object[DEFAULT_C] );
		size = 0;
		a = DEFAULT_A;
		b = DEFAULT_B;
		c = DEFAULT_C;
	}

	public void clear(){
		size = 0;
		buffer=create(DEFAULT_C);
		a = DEFAULT_A;
		b = DEFAULT_B;
		c = DEFAULT_C;
	}

	protected T[] grow(){
		// It requires than a > buffer.length/2
		a = b;
		b = c;
		c = a+b;
		return create(c);        
	};

	protected T[] shrink(){
		// It maintains a > buffer.length/2
		if( a >= DEFAULT_B ){
			c = b;
			b = a;
			a = c-b;
		}    
		if(buffer.length!=c)	return create(c);	else return buffer;
	};

	/**
	 * Inserts a data element in the structure
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	@Override
	public boolean add(T data){
		if( buffer.length == size ){
			T[] newData = grow();
			System.arraycopy( buffer, 0, newData, 0, size );
			buffer = newData;
		}
		buffer[size] = data;
		size++;
		return true;
	}

	public int findIndex( T data ){
		int k=0;
		while( k<size && !data.equals(this.buffer[k]) ){ k++; }
		return (k==size)?-1:k;
	}

	/**
	 * Inserts a data element in the structure
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	public boolean del(T data) {
		int k = findIndex( data );
		if( k != -1 ){
			leftShift(k);
			return true;
		}
		return false;
	}

	protected void leftShift( int index ) throws IndexOutOfBoundsException{
		size--;
		if( size < a ){
			T[] newData = shrink();
			System.arraycopy(buffer, 0, newData, 0, index );
			if( index < size ) System.arraycopy(buffer, index+1, newData, index, size-index );
			buffer = newData;
		}else{
			System.arraycopy(buffer, index+1, buffer, index, size-index );
		}
	}

	protected void rightShift( int index ) throws IndexOutOfBoundsException{
		T[] newData = buffer;
		if( buffer.length == size ){
			newData = grow();
			System.arraycopy( buffer, 0, newData, 0, index );
		}
		System.arraycopy(buffer, index, newData, index+1, size-index);
		this.buffer = newData;
		size++;
	}

	public boolean set( int index, T data ) throws IndexOutOfBoundsException{
		if( 0 <= index && index < size ){
			this.buffer[index] = data;
			return true;
		}else{ throw new ArrayIndexOutOfBoundsException( index ); }
	}

	public boolean add( int index, T data ) throws IndexOutOfBoundsException{
		rightShift(index);
		this.buffer[index] = data;
		return true;
	}

	public boolean remove( int index ) throws IndexOutOfBoundsException{
		leftShift(index);
		return true;
	}

	/**
	 * Sets the size of the array
	 * @param n The new size of the array
	 */
	public void setSize( int n ){
		find_fib(n);
		if( c != buffer.length ){
			T[] newBuffer = create(c);
			System.arraycopy( buffer, 0, newBuffer, 0, Math.min(n, size) );
			buffer = newBuffer;
		}
		size = n;
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
}