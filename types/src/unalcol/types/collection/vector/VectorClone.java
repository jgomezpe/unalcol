package unalcol.types.collection.vector;

import unalcol.clone.Clone;
import unalcol.services.MicroService;
import unalcol.services.Service;

public class VectorClone<T>  extends MicroService<Vector<T>> implements Clone<Vector<T>> {
	@SuppressWarnings("unchecked")
	@Override
	public Vector<T> clone(){
		Vector<T> toClone = caller();
		try{
			if( toClone instanceof SortedVector )
				return new SortedVector<T>((T[])Service.run(Clone.name, toClone.buffer), toClone.size(), ((SortedVector<T>)toClone).order);
			else
				return new Vector<T>((T[])Service.run(Clone.name, toClone.buffer), toClone.size());
		}catch(Exception e){}
		return toClone;
	}
}