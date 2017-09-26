package unalcol.types.collection.vector;

import unalcol.sort.*;
import unalcol.types.object.BinarySearch;

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
    protected Order<T> order;

    public SortedVector( T[] buffer, int n, Order<T> order ){
        super( buffer, n );
        this.order = order;
        search = new BinarySearch<T>(this.buffer, order);
    }

    public SortedVector( T[] buffer, Order<T> order ){
        super( buffer );
        this.order = order;
        search = new BinarySearch<T>(this.buffer, order);
    }

    public SortedVector( Vector<T> buffer, Order<T> order ){
        super( buffer.buffer, buffer.size() );
        this.order = order;
        search = new BinarySearch<T>(this.buffer, order);
    }

    public SortedVector( Order<T> order ) {
        super();
        this.order = order;
        search = new BinarySearch<T>(this.buffer, order);
    }
    
    public SortedVector( SortedVector<T> vector ){
    	this(vector, vector.order);
    }

    @Override
    public Integer findKey( T data ){ return search.find(0,size(),data);  }

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