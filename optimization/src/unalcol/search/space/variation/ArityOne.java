package unalcol.search.space.variation;

import unalcol.search.solution.Solution;
import unalcol.search.variation.ArityOneSearchOperator;

public interface ArityOne<T> extends ArityOneSearchOperator<T>, Operator<T> {
    public default Solution<T> apply( Solution<T> x ){
    	Solution<T> s = new Solution<T>(apply(x.object()));
    	s.cloneTaggedMethods(x);
    	return s;
    } 

    @SuppressWarnings("unchecked")
 	public default T[] apply(T... pop){
		T[] v = (T[])(new Object[pop.length]);
		for( int i=0; i<pop.length; i++ )
			v[i] = apply(pop[i]);
		return v; 
    }    
}