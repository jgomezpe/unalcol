package unalcol;

public interface TaggedManager<T> {
	/**
	 * Sets the object that is being tagged. Removes all the non TaggedMethods associated to this object.
	 * @param object Object that is being tagged.
	 */
	public default Tagged<T> wrap( T object ){
		return new Tagged<T>(object);
	}
	
	/**
	 * Gets the object that is being tagged.
	 * @return tagged object.
	 */
	public default T unwrap( Tagged<T> obj ){ return obj.unwrap(); }
	
	/**
	 * Obtains the set of objects that are actually tagged.
	 * @param obj Set of TaggedObject 's.
	 * @return Actual objects (without tags).
	 */
	public default T[] unwrap( @SuppressWarnings("unchecked") Tagged<T>... obj ){ 
		@SuppressWarnings("unchecked")
		T[] t_obj = (T[])new Object[obj.length];
		for( int i=0; i<obj.length; i++ ) t_obj[i] = obj[i].unwrap();
		return t_obj;
	}  
	
	/**
	 * Creates a TaggedObject array from an array of (possibly non tagged) objects.
	 * @param obj Array of (possibly non tagged) objects to be Tagged.
	 * @return A TaggedObject array from an array of (possibly non tagged) objects.
	 */
	public default Tagged<T>[] wrap( @SuppressWarnings("unchecked") T... obj ){
		@SuppressWarnings("unchecked")
		Tagged<T>[] t_obj = new Tagged[obj.length];
		for( int i=0; i<obj.length; i++ ) t_obj[i] = new Tagged<T>(obj[i]);
		return t_obj;
	}
	
	/**
	 * Creates an array of Tagged version of each object in <i>obj</i> using the TagMethods stored by <i>tags</i>. 
	 * @param tags Tags to be used by the tagged object.
	 * @param obj Objects to be Tagged.
	 * @return A Tagged version of each object in <i>obj</i> using the TagMethods stored by <i>tags</i>.
	 */
	public default Tagged<T>[] wrap( AbstractThing tags, @SuppressWarnings("unchecked") T... obj ){
		@SuppressWarnings("unchecked")
		Tagged<T>[] n_pop = new Tagged[obj.length];
		for( int i=0;i<obj.length; i++ ) n_pop[i] = new Tagged<T>( obj[i], tags);
		return n_pop;
	}	    
}