package unalcol.types.collection.vector;

import java.util.Iterator;
import java.util.NoSuchElementException;

import unalcol.types.collection.Location;
import unalcol.types.collection.SearchCollection;
import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.array.ArrayCollectionLocation;
import unalcol.types.collection.array.MutableArrayCollection;


public class ImmutableVector<T> implements ArrayCollection<T>, SearchCollection<T> {
	protected T[] buffer;
	protected int size;

	public ImmutableVector( T[] buffer ){
		this.buffer=buffer;
		size=buffer.length;
	}

	public int findIndex( T data ){
		int k=0;
		while( k<size && !data.equals(this.buffer[k]) ){ k++; }
		return (k==size)?-1:k;
	}

	public T get( int index ) throws IndexOutOfBoundsException{
		return buffer[index];
	}

	public int size(){ return size; }
	
	protected ImmutableVector<T> self(){ return this; }

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public Iterator<T> iterator(){ return new VectorIterator(); }
	
	protected class VectorIterator implements Iterator<T>{
		protected int pos=-1;
		
		public VectorIterator(){}
		public VectorIterator(int pos){ this.pos=pos; }
		
	    @Override
	    public boolean hasNext(){
	        return pos+1<size;
	    }

	    @Override
	    public T next() throws NoSuchElementException{
	        try{
	            pos++;
	            return buffer[pos];
	        }catch( Exception e ){
	            throw new NoSuchElementException( "" + pos );
	        }
	    }

	    @SuppressWarnings("unchecked")
		@Override
	    public void remove() {
	        if( self() instanceof MutableArrayCollection ){
	            ((MutableArrayCollection<T>)self()).remove(pos);
	        }
	    }    
	}

    /**
     * Locates the given object in the structure
     * @param data Data object to be located
     * @return A data iterator starting at the given object (when the next method is called),
     * If the element is not in the data strucuture the hasNext method will return an exception
     */
    @Override
    public Location<T> find(T data){
        return new ArrayCollectionLocation<T>( findIndex(data), this );
    }

	/**
	 * Determines if the given object belongs to the structure
	 * @param data Data object to be located
	 * @return <i>true</i>If the objects belongs to the structure, <i>false>otherwise</i>
	 */
	@Override
	public boolean contains( T data ){
		try{
			find(data).get();
			return true;
		}catch( NoSuchElementException e ){ return false; }
	}

	@Override
	public Iterator<T> iterator( Location<T> locator ){
    	if( locator instanceof ImmutableVector.VectorLocation )
			return new VectorIterator( ((VectorLocation)locator).getPos() );
		return null;
	}
		
	protected class VectorLocation implements Location<T> {
	    protected int pos;

	    public VectorLocation( int pos ){ this.pos = pos; }

	    @Override
	    public T get() throws NoSuchElementException{
	        try{ return self().get(pos); }catch( Exception e ){ throw new NoSuchElementException("Invalid index .." + pos); }
	    }
	    
	    public int getPos(){ return pos; }
	}
	
    @SuppressWarnings("unchecked")
	protected T[] create( int n ){
    	return (T[])new Object[n];
    }
    
    public T[] toArray(){
    	T[] x = create(size);
    	System.arraycopy(buffer, 0, x, 0, size);
    	return x;
    }
	
}