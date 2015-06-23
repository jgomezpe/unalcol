package unalcol.evolution.ga;

import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.math.logic.Predicate;
import unalcol.search.population.IterativePopulationSearch;
import unalcol.search.population.PopulationSolution;
import unalcol.search.population.variation.ArityOne;
import unalcol.search.population.variation.ArityTwo;
import unalcol.search.selection.Selection;

public class GenerationalGA<T> extends IterativePopulationSearch<T> {

	public GenerationalGA(	int n, Selection<T> parent_selection, 
			ArityOne<T> mutation, ArityTwo<T> xover, double probability, 
			Predicate<PopulationSolution<T>> tC) {
		super(n, new GenerationalStep<T>(n, parent_selection, mutation, xover, probability), tC);
	}
	
	public GenerationalGA(	int n, Selection<T> parent_selection, 
			ArityOne<T> mutation, ArityTwo<T> xover, double probability, 
			int MAXITERS) {
		this(n, parent_selection, mutation, xover, probability, new ForLoopCondition<PopulationSolution<T>>(MAXITERS));
	}
}
