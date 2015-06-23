package unalcol.evolution.ga;

import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.math.logic.Predicate;
import unalcol.search.population.IterativePopulationSearch;
import unalcol.search.population.PopulationSolution;
import unalcol.search.population.variation.ArityTwo;
import unalcol.search.selection.Selection;
import unalcol.search.space.ArityOne;

public class SteadyStateGA<T> extends IterativePopulationSearch<T> {

	public SteadyStateGA( int n, Selection<T> parent_selection, 
			ArityOne<T> mutation, ArityTwo<T> xover, double probability, 
			Predicate<PopulationSolution<T>> tC ) {
		super(n, new SteadyStateStep<T>(n, parent_selection, mutation, xover, probability), tC);
	}
	
	public SteadyStateGA( int n, Selection<T> parent_selection, 
			ArityOne<T> mutation, ArityTwo<T> xover, double probability, 
			int MAXITERS ) {
		this(n, parent_selection, mutation, xover, probability, new ForLoopCondition<PopulationSolution<T>>(MAXITERS));
	}
}
