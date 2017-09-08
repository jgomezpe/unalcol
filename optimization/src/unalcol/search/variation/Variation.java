package unalcol.search.variation;

import unalcol.Tagged;
import unalcol.search.space.Space;

public interface Variation<T>{
	public default int arity(){ return 0; };

	public default int range_arity(){ return 0; };
	
	@SuppressWarnings("unchecked")
	public default T[] apply(T... pop){ return get(apply(set(pop))); }
    
    @SuppressWarnings("unchecked")
	public default T[] apply(Space<T> space, T... pop){ return space.repair(apply(pop)); }
    
	@SuppressWarnings("unchecked")
	public default Tagged<T>[] apply(Tagged<T>... pop){ return set(apply(get(pop))); }    
    
	@SuppressWarnings("unchecked")
	public default Tagged<T>[] apply(Space<T> space, Tagged<T>... pop){ return space.repair(apply(pop)); } 
	
	@SuppressWarnings("unchecked")
	public default T[] get(Tagged<T>... pop){
		int n = pop.length;
		T[] b_pop = (T[])(new Object[n]);
		for( int i=0; i<n; i++) b_pop[i] = pop[i].unwrap();
		return b_pop;
	}
	
	@SuppressWarnings("unchecked")
	public default Tagged<T>[] set(T... pop){
		int n = pop.length;
		Tagged<T>[] s_pop = (Tagged<T>[])(new Tagged[n]);
		for( int i=0; i<n; i++) s_pop[i] = new Tagged<T>(pop[i]);
		return s_pop;
	}
}