package unalcol.collection.sparse;

import unalcol.collection.Collection;
import unalcol.collection.array.Immutable;
import unalcol.collection.keymap.KeyValue;
import unalcol.collection.vector.Sorted;
import unalcol.exception.ParamsException;
import unalcol.collection.keymap.KeyOrder;
import unalcol.integer.Order;

public class ImmutableSparseArray<T> implements Immutable<T>{
	protected Sorted<KeyValue<Integer,T>> vector;
    protected KeyValue<Integer,T> loc = new KeyValue<Integer,T>(0, null);

    public ImmutableSparseArray(){ this( new Sorted<KeyValue<Integer,T>>(new KeyOrder<Integer,T>(new Order()))); }

    public ImmutableSparseArray( Sorted<KeyValue<Integer,T>> vector ) { this.vector = vector; }
    
    protected KeyValue<Integer,T> pair( int index ){
    	try{ return vector.get(index); }catch( Exception e ){ return null; } 
    }
    
    protected int index( int index ){ return pair(index).key(); } 
    
	@Override
	public int size(){ return vector.size()>0?index(vector.size()-1)+1:0; }

	@Override
	public T get(Integer index) throws ParamsException{
		loc.setKey(index);
		Integer pos = vector.find(loc);
		return vector.get(pos).value();
	}
	
	public int low(){ return size()>=0?index(0):0; }

	@Override 
	public Collection<KeyValue<Integer,T>> pairs(){ return vector; } 
	
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
}