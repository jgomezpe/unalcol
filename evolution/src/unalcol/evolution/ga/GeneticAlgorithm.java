package unalcol.evolution.ga;

import unalcol.search.Goal;
import unalcol.search.population.PopulationSearch;
import unalcol.search.population.PopulationSolution;
import unalcol.search.population.variation.ArityOne;
import unalcol.search.population.variation.ArityTwo;
import unalcol.search.selection.Selection;
import unalcol.search.space.Space;
import unalcol.types.collection.vector.Vector;


/**
 * <p>Title: GeneticAlgorithm</p>
 *
 * <p>Description: The Genetic Algorithm evolutionary transformation</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class GeneticAlgorithm<T> extends PopulationSearch<T> {
	protected Selection<T> selection;
	protected GAVariation<T> variation;
	
    public GeneticAlgorithm( int n, Selection<T> selection,
                             ArityOne<T> mutation, ArityTwo<T> xover,
                             double probability ) {
    	super(n);
    	this.selection = selection;
    	variation = new GAVariation<T>(mutation, xover, probability);
    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PopulationSolution<T> apply(PopulationSolution<T> pop,
			Space<T> space, Goal<T> goal) {
		return new PopulationSolution<T>( variation.apply( space, selection.apply(n, pop.value(), pop.quality()) ), goal);
	}
}