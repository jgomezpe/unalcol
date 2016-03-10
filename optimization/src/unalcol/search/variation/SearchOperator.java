package unalcol.search.variation;

import unalcol.search.solution.Solution;
import unalcol.search.space.Space;

public interface SearchOperator<T>{
	public default int arity(){ return 0; };
	
    @SuppressWarnings("unchecked")
	public T[] apply(T... pop);
    
    @SuppressWarnings("unchecked")
	public default T[] apply(Space<T> space, T... pop){
        return space.repair(apply(pop));
    }
    
    @SuppressWarnings("unchecked")
	public Solution<T>[] apply(Solution<T>... pop);
    
	@SuppressWarnings("unchecked")
	public default Solution<T>[] apply(Space<T> space, Solution<T>... pop){
		Solution<T>[] nPop = apply(pop);
		for( int i=0; i<nPop.length; i++ ){
			nPop[i] = new Solution<T>(space.repair(nPop[i].object()));
			nPop[i].cloneTaggedMethods(pop[0]);
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
}