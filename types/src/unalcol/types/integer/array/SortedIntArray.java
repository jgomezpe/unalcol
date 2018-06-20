package unalcol.types.integer.array;

import unalcol.types.integer.IntegerOrder;

public class SortedIntArray extends IntArray {
	
    protected IntSearch search;
    protected IntegerOrder order;

    public SortedIntArray( int[] buffer, int n, IntegerOrder order ){
        super( buffer, n );
        this.order = order;
        search = new IntSearch(this.buffer, order);
    }

    public SortedIntArray( int[] buffer, IntegerOrder order ){
        super( buffer );
        this.order = order;
        search = new IntSearch(this.buffer, order);
    }

    public int findIndex( int data ){ return search.find(data); }

    public boolean add( int data ){
        int index = search.findRight(data);
        if( index == this.size() ){
              return super.add(data);
        }else{
            rightShift(index);
            buffer[index] = data;
        }
        return true;
    }

    public boolean set( int index, int data ) throws IndexOutOfBoundsException{
        if( 0 <= index && index < size ){
        	boolean flag = 	(index==0 || order.compare(buffer[index-1], data)<=0) &&
        					(index==size-1 || order.compare(data, buffer[index+1])<=0);
            if( flag ) buffer[index] = data;
            return flag;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }

    public boolean add( int index, int data ) throws IndexOutOfBoundsException{
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