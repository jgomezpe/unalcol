package unalcol.search.space.variation;

import unalcol.search.solution.Solution;
import unalcol.search.variation.SearchOperator;

public interface Operator<T> extends SearchOperator<T>{	
	@SuppressWarnings("unchecked")
	public default Solution<T>[] apply(Solution<T>... pop){
		Solution<T>[] s = set(apply(get(pop)));
		for( int i=0; i<s.length; i++ ){
			s[i].cloneTaggedMethods(pop[i]);
		}
		return s;
	}    
}