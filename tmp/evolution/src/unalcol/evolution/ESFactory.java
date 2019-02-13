package unalcol.evolution;

import unalcol.math.function.iterative.ForLoopCondition;
import unalcol.evolution.es.ESReplacement;
import unalcol.evolution.es.ESStep;
import unalcol.math.logic.Predicate;
import unalcol.search.population.IterativePopulationSearch;
import unalcol.search.population.PopulationSearch;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation_1_1;
import unalcol.search.variation.Variation_n_1;
import unalcol.object.Tagged;

public class ESFactory<T,P> {
	//Evolutionary Strategy factory
	public PopulationSearch<T,Double> evolutionarystrategy( 
			ESStep<T,P> step, Predicate<Tagged<T>[]> tC ){
		return new IterativePopulationSearch<T,Double>( step, tC );
	}

	public PopulationSearch<T,Double> evolutionarystrategy( 
			ESStep<T,P> step, int MAXITERS ){
		return evolutionarystrategy( step, new ForLoopCondition<>(MAXITERS) );
	}
	
	public PopulationSearch<T,Double> evolutionarystrategy(
			int mu, int lambda, int ro, 
			Variation_n_1<T> y_recombination, Variation_1_1<T> mutation, 
			Variation_n_1<P> s_recombination, Variation_1_1<P> s_mutation, Space<P> s_space,
			ESReplacement<T> replacement, int MAXITERS ){
		return evolutionarystrategy(new ESStep<T,P>(mu, lambda, ro, y_recombination, mutation, s_recombination, s_mutation, s_space, replacement), MAXITERS);
	}
	
	public PopulationSearch<T,Double> evolutionarystrategy( 
			int mu, int lambda, int ro, 
			Variation_n_1<T> y_recombination, Variation_1_1<T> mutation, 
			Variation_n_1<P> s_recombination, Variation_1_1<P> s_mutation, Space<P> s_space,
			boolean plus_replacement, int MAXITERS ){
		return evolutionarystrategy(new ESStep<T,P>(mu, lambda, ro, y_recombination, mutation, s_recombination, s_mutation, s_space, plus_replacement), MAXITERS);
	}	
}