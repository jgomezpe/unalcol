package unalcol.types.collection.vector;

import unalcol.clone.Clone;
import unalcol.services.Service;
import unalcol.services.TaggedCallerNamePair;
import unalcol.types.tag.Tags;

public class VectorClone<T>  extends Tags implements TaggedCallerNamePair<ImmutableVector<T>>,  Clone<ImmutableVector<T>> {
	@SuppressWarnings("unchecked")
	@Override
	public ImmutableVector<T> clone(){
		ImmutableVector<T> toClone = caller();
		try{
			if( toClone instanceof SortedVector )
				return new SortedVector<T>((T[])Service.run(Clone.name, toClone.buffer), toClone.size(), ((SortedVector<T>)toClone).order);
			else
				if( toClone instanceof Vector ) return new Vector<T>((T[])Service.run(Clone.name, toClone.buffer), toClone.size());
				else return new ImmutableVector<T>((T[])Service.run(Clone.name, toClone.buffer));
		}catch(Exception e){}
		return toClone;
	}
}