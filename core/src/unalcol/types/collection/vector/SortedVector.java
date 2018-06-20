package unalcol.types.collection.vector;

import unalcol.sort.*;
import unalcol.types.object.array.BinarySearch;

/**
 * <p>Title: SortedVector</p>
 *
 * <p>Description: Insert operation for sorted vectors</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class SortedVector<T> extends Vector<T>{
	
    protected BinarySearch<T> search;
    protected Order order;

    public SortedVector( T[] buffer, int n, Order order ){
        super( buffer, n );
        this.order = order;
        search = new BinarySearch<T>(this.buffer, order);
    }

    public SortedVector( T[] buffer, Order order ){
        super( buffer );
        this.order = order;
        search = new BinarySearch<T>(this.buffer, order);
    }

    public SortedVector( Vector<T> buffer, Order order ){
        super( buffer.buffer, buffer.size() );
        this.order = order;
        search = new BinarySearch<T>(this.buffer, order);
    }

    public SortedVector( Order order ) {
        super();
        this.order = order;
        search = new BinarySearch<T>(this.buffer, order);
    }
    
    public SortedVector( SortedVector<T> vector ){
    	this(vector, vector.order);
    }

    @Override
    public Integer find( T data ){
    	int k=search.find(0,size(),data);
    	if(k<0) return null;
    	return k;
    }

    public boolean add( T data ){
        int index = search.findRight(0,size(),data);
        if( index == this.size() ){
              return super.add(data);
        }else{
            rightShift(index);
            buffer[index] = data;
        }
        return true;
    }

    public boolean set( int index, T data ) throws IndexOutOfBoundsException{
        if( 0 <= index && index < size ){
        	boolean flag = 	(index==0 || order.compare(buffer[index-1], data)<=0) &&
        					(index==size-1 || order.compare(data, buffer[index+1])<=0);
            if( flag ) buffer[index] = data;
            return flag;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }

    public boolean add( int index, T data ) throws IndexOutOfBoundsException{
        if( 0 <= index && index <= size ){
        	boolean flag = 	(index==0 || order.compare(buffer[index-1], data)<=0) &&
        					(index==size || order.compare(data, buffer[index])<=0);
            if( flag ) super.add(index, data);
            return flag;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }
}