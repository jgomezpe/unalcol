package unalcol.evolution.ga;

import unalcol.search.population.PopulationReplacement;
import unalcol.search.population.SVRPopulationSearch;
import unalcol.search.population.variation.ArityOne;
import unalcol.search.population.variation.ArityTwo;
import unalcol.search.selection.Selection;

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
public class GAStep<T> extends SVRPopulationSearch<T> {
	protected Selection<T> selection;
	protected GAVariation<T> variation;
	
    public GAStep( int n, Selection<T> selection,
                             ArityOne<T> mutation, ArityTwo<T> xover,
                             double probability, PopulationReplacement<T> replace ) {
    	super(n, selection, new GAVariation<T>(mutation, xover, probability), replace);
    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}