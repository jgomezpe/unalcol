package unalcol.collection.sparse;

import unalcol.clone.Clone;
import unalcol.clone.Cloneable;
import unalcol.collection.keymap.KeyValue;
import unalcol.collection.vector.Sorted;

public class SparseArrayClone<T> implements Clone{
	@SuppressWarnings("unchecked")
	public ImmutableSparseArray<T> clone( ImmutableSparseArray<T> toClone ){
		Sorted<KeyValue<Integer,T>> c = (Sorted<KeyValue<Integer,T>>)Cloneable.cast(toClone.vector).clone();
		if( toClone instanceof SparseArray ) return new SparseArray<T>(c);
		else return new ImmutableSparseArray<T>(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone(Object obj){ return clone((ImmutableSparseArray<T>)obj); }
}