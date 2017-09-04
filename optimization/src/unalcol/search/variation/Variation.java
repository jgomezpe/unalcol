package unalcol.search.variation;

import unalcol.search.solution.Solution;
import unalcol.search.space.Space;
import unalcol.types.collection.keymap.KeyMap;

public interface Variation<T>{
	public default int arity(){ return 0; };

	public default int range_arity(){ return 0; };
	
	@SuppressWarnings("unchecked")
	public default T[] apply(T... pop){ return get(apply(set(pop))); }
    
    @SuppressWarnings("unchecked")
	public default T[] apply(Space<T> space, T... pop){ return space.repair(apply(pop)); }
    
	@SuppressWarnings("unchecked")
	public default Solution<T>[] apply(Solution<T>... pop){ return set(pop[0].tags(), false, apply(get(pop))); }    
    
	@SuppressWarnings("unchecked")
	public default Solution<T>[] apply(Space<T> space, Solution<T>... pop){
		//Tagged
		Solution<T>[] nPop = apply(pop);
		for( int i=0; i<nPop.length; i++ ){
			nPop[i].repair(space);
		}
		return nPop;
	} 
	
	@SuppressWarnings("unchecked")
	public default T[] get(Solution<T>... pop){
		int n = pop.length;
		T[] b_pop = (T[])(new Object[n]);
		for( int i=0; i<n; i++){
			b_pop[i] = pop[i].object();
		}
		return b_pop;
	}
	
	@SuppressWarnings("unchecked")
	public default Solution<T>[] set(T... pop){
		int n = pop.length;
		Solution<T>[] s_pop = (Solution<T>[])(new Solution[n]);
		for( int i=0; i<n; i++){
			s_pop[i] = new Solution<T>(pop[i]);
		}
		return s_pop;
	}

	@SuppressWarnings("unchecked")
	public default Solution<T>[] set(KeyMap<String,Object> tags, boolean cloneAllTags, T... pop){
		int n = pop.length;
		Solution<T>[] s_pop = (Solution<T>[])(new Solution[n]);
		for( int i=0; i<n; i++){
			s_pop[i] = new Solution<T>(pop[i], tags, cloneAllTags);
		}
		return s_pop;
	}
}