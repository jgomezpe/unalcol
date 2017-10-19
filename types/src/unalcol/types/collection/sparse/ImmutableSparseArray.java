package unalcol.types.collection.sparse;

import unalcol.types.collection.Collection;
import unalcol.types.collection.array.ImmutableArray;
import unalcol.types.collection.keymap.KeyValue;
import unalcol.types.collection.keymap.KeyOrder;
import unalcol.types.collection.vector.SortedVector;
import unalcol.types.integer.IntegerOrder;

public class ImmutableSparseArray<T> implements ImmutableArray<T>{
	protected SortedVector<KeyValue<Integer,T>> vector;
    protected KeyValue<Integer,T> loc = new KeyValue<Integer,T>(0, null);

    public ImmutableSparseArray(){ this( new SortedVector<KeyValue<Integer,T>>(new KeyOrder<Integer,T>(new IntegerOrder()))); }

    public ImmutableSparseArray( SortedVector<KeyValue<Integer,T>> vector ) { this.vector = vector; }
    
	@Override
	public int size(){
		if( vector.size()==0 ) return 0;
		return vector.get(vector.size()-1).key()+1; 
	}

	@Override
	public T get(Integer index){
		loc.setKey(index);
		Integer pos = vector.find(loc);
		if( pos==null ) return null;
		else return vector.get(pos).value();
	}
	
	public int low(){ return size()>=0?vector.get(0).key():0; }

	@Override 
	public Collection<KeyValue<Integer,T>> pairs(){ return vector; } 
	
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
}