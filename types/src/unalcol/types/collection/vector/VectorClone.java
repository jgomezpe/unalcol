package unalcol.types.collection.vector;

import unalcol.clone.Clone;
import unalcol.clone.Cloneable;

public class VectorClone<T>  implements Clone {
	@SuppressWarnings("unchecked")
	public Vector<T> clone(Vector<T> toClone){
		Cloneable c = Cloneable.cast(toClone.buffer);
		if( toClone instanceof SortedVector )
			return new SortedVector<T>((T[])c.clone(), toClone.size(), ((SortedVector<T>)toClone).order);
		else
			return new Vector<T>((T[])c.clone(), toClone.size());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object clone(Object obj){ return clone((Vector<T>)obj); }
	
	@Override
	public String toString(){ return "VectorClone"; }	
}