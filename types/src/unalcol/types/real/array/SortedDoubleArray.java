package unalcol.types.real.array;

import unalcol.types.real.DoubleOrder;

public class SortedDoubleArray extends DoubleArray {
	
    protected DoubleSearch search;
    protected DoubleOrder order;

    public SortedDoubleArray( double[] buffer, int n, DoubleOrder order ){
        super( buffer, n );
        this.order = order;
        search = new DoubleSearch(this.buffer, order);
    }

    public SortedDoubleArray( double[] buffer, DoubleOrder order ){
        super( buffer );
        this.order = order;
        search = new DoubleSearch(this.buffer, order);
    }

    public int findIndex( double data ){ return search.find(data); }

    public boolean add( double data ){
        int index = search.findRight(data);
        if( index == this.size() ){
              return super.add(data);
        }else{
            rightShift(index);
            buffer[index] = data;
        }
        return true;
    }

    public boolean set( int index, double data ) throws IndexOutOfBoundsException{
        if( 0 <= index && index < size ){
        	boolean flag = 	(index==0 || order.compare(buffer[index-1], data)<=0) &&
        					(index==size-1 || order.compare(data, buffer[index+1])<=0);
            if( flag ) buffer[index] = data;
            return flag;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }

    public boolean add( int index, double data ) throws IndexOutOfBoundsException{
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