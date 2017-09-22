package unalcol.types.collection.sparse;


import unalcol.types.collection.array.Array;
import unalcol.types.collection.keymap.KeyValue;
import unalcol.types.collection.vector.SortedVector;

public class SparseArray<T> extends ImmutableSparseArray<T> implements Array<T> {
    public SparseArray(){ super(); }

    public SparseArray( SortedVector<KeyValue<Integer,T>> vector ) { super(vector); }

	@Override
	public boolean add(T data){ return false; }

	@Override
	public void clear() { vector.clear(); }

	@Override
	public boolean add(int index, T data) throws ArrayIndexOutOfBoundsException { return set(index,data); }

	@Override
	public boolean remove(int index) throws ArrayIndexOutOfBoundsException {
		loc.setKey(index);
		index = vector.findKey(loc);
		if( index >= 0 ) return vector.remove(index);
		return false;
	}

	@Override
	public void resize(int size) {
		int i=vector.size()-1;
		while(i>=0 && vector.get(i).key()>=size){
			vector.remove(i);
			i--;
		}
	}

	@Override
	public boolean set(int index, T data) throws ArrayIndexOutOfBoundsException {
		loc.setKey(index);
		int i = vector.findKey(loc);
		if( i >= 0 ){
			vector.get(i).setValue(data);
			return true;
		}else return vector.add(new KeyValue<Integer,T>(index,data));
	}	
}