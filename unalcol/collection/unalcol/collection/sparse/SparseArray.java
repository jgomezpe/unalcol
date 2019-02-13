package unalcol.collection.sparse;

import unalcol.collection.Array;
import unalcol.collection.keymap.KeyValue;
import unalcol.collection.vector.Sorted;

public class SparseArray<T> extends ImmutableSparseArray<T> implements Array<T> {
    public SparseArray(){ super(); }

    public SparseArray( Sorted<KeyValue<Integer,T>> vector ) { super(vector); }

	@Override
	public boolean add(T data){ return false; }

	@Override
	public void clear() { vector.clear(); }

	@Override
	public boolean add(Integer index, T data){ return set(index,data); }

	@Override
	public boolean remove(Integer index){
		loc.setKey(index);
		index = vector.find(loc);
		if( index != null ) return vector.remove(index);
		return false;
	}

	@Override
	public void resize(int size) {
		int i=vector.size()-1;
		while(i>=0 && index(i)>=size){
			vector.remove(i);
			i--;
		}
	}

	@Override
	public boolean set(Integer index, T data){
		loc.setKey(index);
		try{
			Integer i = vector.find(loc);
			vector.get(i).setValue(data);
			return true;
		}catch(Exception e){ return vector.add(new KeyValue<Integer,T>(index,data)); }
	}	
}