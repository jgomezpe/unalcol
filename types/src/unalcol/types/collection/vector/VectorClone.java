package unalcol.types.collection.vector;

import unalcol.clone.Clone;
import unalcol.services.Service;

public class VectorClone<T> implements Clone<ImmutableVector<T>> {

	@SuppressWarnings("unchecked")
	@Override
	public ImmutableVector<T> clone(ImmutableVector<T> toClone) throws Exception {
		if( toClone instanceof SortedVector )
			return new SortedVector<T>((T[])Service.run(Clone.name, toClone.buffer), toClone.size(), ((SortedVector<T>)toClone).order);
		else
			if( toClone instanceof Vector ) return new Vector<T>((T[])Service.run(Clone.name, toClone.buffer), toClone.size());
			else return new ImmutableVector<T>((T[])Service.run(Clone.name, toClone.buffer));
	}
}