package unalcol.search.variation;

import java.util.Hashtable;

import unalcol.search.solution.Solution;
import unalcol.search.space.Space;

public class Variation<T>{
	public int arity(){ return 0; };
	public int range_arity(){ return 0; };
	
	@SuppressWarnings("unchecked")
	public T[] apply(T... pop){
		return get(apply(set(pop)));
	}
    
    @SuppressWarnings("unchecked")
	public T[] apply(Space<T> space, T... pop){
        return space.repair(apply(pop));
    }
    
	@SuppressWarnings("unchecked")
	public Solution<T>[] apply(Solution<T>... pop){
		return set(pop[0].tags(), false, apply(get(pop)));
	}    
    
	@SuppressWarnings("unchecked")
	public Solution<T>[] apply(Space<T> space, Solution<T>... pop){
		//Tagged
		Solution<T>[] nPop = apply(pop);
		for( int i=0; i<nPop.length; i++ ){
			nPop[i].repair(space);
		}
		return nPop;
	} 
	
	@SuppressWarnings("unchecked")
	public T[] get(Solution<T>... pop){
		int n = pop.length;
		T[] b_pop = (T[])(new Object[n]);
		for( int i=0; i<n; i++){
			b_pop[i] = pop[i].object();
		}
		return b_pop;
	}
	
	@SuppressWarnings("unchecked")
	public Solution<T>[] set(T... pop){
		int n = pop.length;
		Solution<T>[] s_pop = (Solution<T>[])(new Solution[n]);
		for( int i=0; i<n; i++){
			s_pop[i] = new Solution<T>(pop[i]);
		}
		return s_pop;
	}

	@SuppressWarnings("unchecked")
	public Solution<T>[] set(Hashtable<String,Object> tags, boolean cloneAllTags, T... pop){
		int n = pop.length;
		Solution<T>[] s_pop = (Solution<T>[])(new Solution[n]);
		for( int i=0; i<n; i++){
			s_pop[i] = new Solution<T>(pop[i], tags, cloneAllTags);
		}
		return s_pop;
	}
}