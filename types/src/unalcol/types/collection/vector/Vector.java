/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.vector;

import unalcol.random.integer.IntUniform;
//import unalcol.clone.Clone;
//import unalcol.service.ServiceCore;
import unalcol.types.collection.Location;
import unalcol.types.collection.array.ArrayCollectionLocation;
import unalcol.types.collection.array.MutableArrayCollection;

/**
 *
 * @author jgomez
 */
public class Vector<T> extends ImmutableVector<T> implements MutableArrayCollection<T> {
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
    
	public Vector( Vector<T> vector ){
    	super( vector );
        find_fib(this.size);
    }
    
    public Vector( T[] buffer ){
        this( buffer, buffer.length );
    }

    public Vector( T[] buffer, int s ){
        super( buffer );
        size = s;
        find_fib(size);
    }

    @SuppressWarnings("unchecked")
	public Vector(){
        super( (T[])new Object[DEFAULT_C] );
        size = 0;
        a = DEFAULT_A;
        b = DEFAULT_B;
        c = DEFAULT_C;
    }

    @SuppressWarnings("unchecked")
	@Override
    public void clear(){
        size = 0;
        buffer=(T[])new Object[DEFAULT_C];
        a = DEFAULT_A;
        b = DEFAULT_B;
        c = DEFAULT_C;
    }

    @SuppressWarnings("unchecked")
	protected T[] grow(){
        // It requires than a > buffer.length/2
        a = b;
        b = c;
        c = a+b;
        return (T[])new Object[c];        
    };

    @SuppressWarnings("unchecked")
	protected T[] shrink(){
        // It maintains a > buffer.length/2
        if( a >= DEFAULT_B ){
            c = b;
            b = a;
            a = c-b;
        }    
        if(buffer.length!=c)
            return (T[])new Object[c];        
        else
            return buffer;
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

    /**
     * Inserts a data element in the structure
     * @param data Data element to be inserted
     * @return <i>true</i> if the element could be added, <i>false</i> otherwise
     */
    @Override
    public boolean del(T data) {
        int k = findIndex( data );
        if( k != -1 ){
            leftShift(k);
            return true;
        }
        return false;
    }

    /**
     * Removes the element indicated by the locator
     * @param locator The location information of the object to be deleted
     * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
     */
    @Override
    public boolean del( Location<T> locator ){
        if( locator instanceof ArrayCollectionLocation ){
            ArrayCollectionLocation<T> loc = ((ArrayCollectionLocation<T>)locator);
            leftShift( loc.getPos() );
            return true;
        }
        return false;
    }

    protected void leftShift( int index ) throws IndexOutOfBoundsException{
        size--;
        if( size < a ){
            T[] newData = shrink();
            System.arraycopy(buffer, 0, newData, 0, index );
            System.arraycopy(buffer, index+1, newData, index, size-index );
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


    @Override
    public boolean set( int index, T data ) throws IndexOutOfBoundsException{
        if( 0 <= index && index < size ){
            this.buffer[index] = data;
            return true;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }

    @Override
    public boolean add( int index, T data ) throws IndexOutOfBoundsException{
            rightShift(index);
            this.buffer[index] = data;
            return true;
    }

    @Override
    public boolean remove( int index ) throws IndexOutOfBoundsException{
            leftShift(index);
            return true;
    }

    public void shuffle(){
        int m = 0;
        int j, k;
        T temp;
        int n = size();
        IntUniform g = new IntUniform(n);
        int[] indices = g.generate(2 * n);
        for (int i = 0; i < n; i++) {
            j = indices[m];
            m++;
            k = indices[m];
            m++;
            temp = buffer[j];
            buffer[j] = buffer[k];
            buffer[k] = temp;
        }
    }
        
}
