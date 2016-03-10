package unalcol.search.solution;

import unalcol.reflect.tag.TaggedObject;
import unalcol.reflect.tag.TaggedObjectManager;

public interface SolutionManager<T> extends TaggedObjectManager<T> {
	@SuppressWarnings("unchecked")
	public default TaggedObject<T>[] tagged_array( int n ){
		return (TaggedObject<T>[])new Solution[n];
	}
	
	public default TaggedObject<T> wrap( T obj ){
		return new Solution<T>(obj);
	}
}
