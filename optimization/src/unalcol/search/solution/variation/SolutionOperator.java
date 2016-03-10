package unalcol.search.solution.variation;

import unalcol.search.space.Space;
import unalcol.search.variation.SearchOperator;
import unalcol.search.population.Population;

public interface SolutionOperator<T> extends SearchOperator<T>{
	
	@SuppressWarnings("unchecked")
	public default T[] apply(T... pop){
		return get(apply(set(pop)));
	}
	
	public default Population<T> apply( Space<T> space, Population<T> pop ){
		return (Population<T>)pop.clone( apply(space, pop.object()) );
	}
	
	public default Population<T> apply( Population<T> pop ){
		return (Population<T>)pop.clone( apply(pop.object()) );
	}
}