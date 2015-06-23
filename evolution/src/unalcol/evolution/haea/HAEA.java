package unalcol.evolution.haea;

import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.math.logic.Predicate;
import unalcol.search.population.IterativePopulationSearch;
import unalcol.search.population.PopulationSolution;
import unalcol.search.selection.Selection;

public class HAEA<T> extends IterativePopulationSearch<T> {
	public HAEA( int n, HaeaOperators<T> operators, Selection<T> selection, Predicate<PopulationSolution<T>> tC ){
		this( n, new HaeaStep<T>(n,operators,selection), tC );
	}

	public HAEA( int n, HaeaOperators<T> operators, Selection<T> selection, int MAXITERS ){
		this( n, operators, selection, new ForLoopCondition<PopulationSolution<T>>(MAXITERS) );
	}

	public HAEA( int n, HaeaStep<T> step, Predicate<PopulationSolution<T>> tC ){
		super( n, step, tC );
	}

	public HAEA( int n, HaeaStep<T> step, int MAXITERS ){
		this( n, step, new ForLoopCondition<PopulationSolution<T>>(MAXITERS) );
	}
	
}
