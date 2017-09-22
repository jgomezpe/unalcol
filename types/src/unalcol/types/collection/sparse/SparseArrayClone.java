package unalcol.types.collection.sparse;

import unalcol.clone.Clone;
import unalcol.services.MicroService;
import unalcol.types.collection.keymap.KeyValue;
import unalcol.types.collection.vector.SortedVector;

public class SparseArrayClone<T>  extends MicroService<ImmutableSparseArray<T>> implements Clone<ImmutableSparseArray<T>> {
	@SuppressWarnings("unchecked")
	@Override
	public ImmutableSparseArray<T> clone(){
		ImmutableSparseArray<T> toClone = caller();
		if( toClone instanceof SparseArray ) return new SparseArray<T>( (SortedVector<KeyValue<Integer,T>>)Clone.create(toClone.vector));
		else return new ImmutableSparseArray<T>( (SortedVector<KeyValue<Integer,T>>)Clone.create(toClone.vector));
	}
}