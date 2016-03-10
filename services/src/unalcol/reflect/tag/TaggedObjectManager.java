package unalcol.reflect.tag;

public interface TaggedObjectManager<T> {
	public default T unwrap( TaggedObject<T> obj ){
		return obj.object();
	}
	
	@SuppressWarnings("unchecked")
	public default T[] obj_array( int n ){
		return (T[])new Object[n];
	}
	
	public default T[] unwrap( @SuppressWarnings("unchecked") TaggedObject<T>... obj ){
		T[] t_obj = obj_array(obj.length);
		for( int i=0; i<obj.length; i++ ) t_obj[i] = unwrap(obj[i]);
		return t_obj;
	}  
	
	public default TaggedObject<T> wrap( T obj ){
		return new TaggedObject<T>(obj);
	}
	
	public default TaggedObject<T> wrap( T obj, TaggedObject<T> tag_obj ){
		return tag_obj.clone(obj);
	}
	
	@SuppressWarnings("unchecked")
	public default TaggedObject<T>[] tagged_array( int n ){
		return (TaggedObject<T>[])new TaggedObject[n];
	}
	
	public default TaggedObject<T>[] wrap( @SuppressWarnings("unchecked") T... obj ){
		TaggedObject<T>[] t_obj = tagged_array(obj.length);
		for( int i=0; i<obj.length; i++ ) t_obj[i] = wrap(obj[i]);
		return t_obj;
	}
	
    public default TaggedObject<T>[] wrap( TaggedObject<T> tag_obj, @SuppressWarnings("unchecked") T... obj ){
		TaggedObject<T>[] n_pop = tagged_array(obj.length);
    	int i=0;
    	for( T x:obj ){
    		n_pop[i] = wrap( x, tag_obj);
    		i++;
    	}
    	return n_pop;
    }	
}