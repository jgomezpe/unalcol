package unalcol.types.collection.vector;

import unalcol.sort.*;

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
	
    protected SortedVectorSearch<T> search = new SortedVectorSearch<T>();
    protected Order<T> order;

    public SortedVector( T[] buffer, int n, Order<T> order ){
        super( buffer, n );
        this.order = order;
    }

    public SortedVector( T[] buffer, Order<T> order ){
        super( buffer );
        this.order = order;
    }

    public SortedVector( Vector<T> buffer, Order<T> order ){
        super( buffer.buffer, buffer.size() );
        this.order = order;
    }

    public SortedVector( Order<T> order ) {
        super();
        this.order = order;
    }
    
    public SortedVector( SortedVector<T> vector ){
    	super(vector);
    	this.order = vector.order;
    }

    public int findIndex( T data ){
        return search.find(this,data,order);
    }

    public boolean add( T data ){
        int index = search.findRight(this, data, order);
        if( index == this.size() ){
              return super.add(data);
        }else{
            rightShift(index);
            this.buffer[index] = data;
        }
        return true;
    }

    public boolean set( int index, T data ) throws IndexOutOfBoundsException{
        if( (0 <= index && index < size) &&
            (size==1 ||
             (index==0 && order.compare(data, buffer[1])<=0) ||
             (index==size-1 && order.compare(buffer[index-1], data)<=0) ||
             (size>2 && order.compare(buffer[index-1], data)<=0 &&
              order.compare(data, buffer[index+1])<=0)) ){
            this.buffer[index] = data;
            return true;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }

    public boolean add( int index, T data ) throws IndexOutOfBoundsException{
        if( (0 <= index && index <= size) &&
            (size==0 ||
             (index==0 && order.compare(data, buffer[0])<=0) ||
             (index==size && order.compare(buffer[index-1], data)<=0) ||
             (size>1 && order.compare(buffer[index-1], data)<=0 &&
              order.compare(data, buffer[index])<=0)) ){
            rightShift(index);
            this.buffer[index] = data;
            return true;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }

}
