package unalcol.evolution;

import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.evolution.es.ESReplacement;
import unalcol.evolution.es.ESStep;
import unalcol.math.logic.Predicate;
import unalcol.search.population.IterativePopulationSearch;
import unalcol.search.population.Population;
import unalcol.search.population.PopulationSearch;
import unalcol.search.space.Space;
import unalcol.search.space.variation.BuildOne;
import unalcol.search.variation.ArityOneSearchOperator;

public class ESFactory<T,P> {
	//Evolutionary Strategy factory
	public PopulationSearch<T,Double> evolutionarystrategy( 
			ESStep<T,P> step, Predicate<Population<T>> tC ){
		return new IterativePopulationSearch<T,Double>( step, tC );
	}

	public PopulationSearch<T,Double> evolutionarystrategy( 
			ESStep<T,P> step, int MAXITERS ){
		return evolutionarystrategy( step, new ForLoopCondition<>(MAXITERS) );
	}
	
	public PopulationSearch<T,Double> evolutionarystrategy(
			int mu, int lambda, int ro, 
			BuildOne<T> y_recombination, ArityOneSearchOperator<T> mutation, 
			BuildOne<P> s_recombination, ArityOneSearchOperator<P> s_mutation, Space<P> s_space,
			ESReplacement<T> replacement, int MAXITERS ){
		return evolutionarystrategy(new ESStep<T,P>(mu, lambda, ro, y_recombination, mutation, s_recombination, s_mutation, s_space, replacement), MAXITERS);
	}
	
	public PopulationSearch<T,Double> evolutionarystrategy( 
			int mu, int lambda, int ro, 
			BuildOne<T> y_recombination, ArityOneSearchOperator<T> mutation, 
			BuildOne<P> s_recombination, ArityOneSearchOperator<P> s_mutation, Space<P> s_space,
			boolean plus_replacement, int MAXITERS ){
		return evolutionarystrategy(new ESStep<T,P>(mu, lambda, ro, y_recombination, mutation, s_recombination, s_mutation, s_space, plus_replacement), MAXITERS);
	}	
}