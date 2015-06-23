package unalcol.evolution.ga;

import unalcol.search.population.Generational;
import unalcol.search.population.variation.ArityTwo;
import unalcol.search.selection.Selection;
import unalcol.search.space.ArityOne;

/**
 * <p>Title: GenerationalGA</p>
 *
 * <p>Description: Generational Genetic Algorithm</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class GenerationalStep<T> extends GAStep<T> {
    public GenerationalStep( int n, Selection<T> parent_selection,
                           ArityOne<T> mutation, ArityTwo<T> xover,
                           double probability ) {
        super( n, parent_selection, mutation, xover, probability,
               new Generational<T>() );
    } 
}
