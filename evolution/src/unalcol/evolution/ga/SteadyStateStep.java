package unalcol.evolution.ga;

import unalcol.search.population.TotalSelectionReplacement;
import unalcol.search.population.variation.ArityOne;
import unalcol.search.population.variation.ArityTwo;
import unalcol.search.selection.Selection;

/**
 * <p>Title: SteadyStateGA</p>
 *
 * <p>Description: Steady State Genetic Algorithm (GA with Steady State replacement strategy </p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class SteadyStateStep<T> extends GAStep<T> {
    public SteadyStateStep( int n,  Selection<T> parent_selection,
                          ArityOne<T> mutation, ArityTwo<T> xover,
                          double probability ) {
        super( n, parent_selection, mutation, xover, probability,
               new TotalSelectionReplacement<T>() );
    }
}