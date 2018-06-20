package unalcol.types.collection.sparse;

import unalcol.clone.Clone;
import unalcol.clone.Cloneable;
import unalcol.types.collection.keymap.KeyValue;
import unalcol.types.collection.vector.SortedVector;

public class SparseArrayClone<T> implements Clone{
	@SuppressWarnings("unchecked")
	public ImmutableSparseArray<T> clone( ImmutableSparseArray<T> toClone ){
		SortedVector<KeyValue<Integer,T>> c = (SortedVector<KeyValue<Integer,T>>)Cloneable.cast(toClone.vector).clone();
		if( toClone instanceof SparseArray ) return new SparseArray<T>(c);
		else return new ImmutableSparseArray<T>(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone(Object obj){ return clone((ImmutableSparseArray<T>)obj); }
}