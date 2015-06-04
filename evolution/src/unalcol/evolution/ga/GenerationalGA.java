package unalcol.evolution.ga;

import unalcol.optimization.selection.Selection;
import unalcol.optimization.replacement.Generational;
import unalcol.evolution.*;
import unalcol.optimization.operators.ArityOne;
import unalcol.optimization.operators.ArityTwo;
import unalcol.optimization.operators.RefiningOperator;

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
public class GenerationalGA<G,P> extends GeneticAlgorithm<G,P> {
    public GenerationalGA( Selection<P> parent_selection,
                           GrowingFunction<G,P> grow,
                           ArityOne<G> mutation, ArityTwo<G> xover,
                           double probability ) {
        super( parent_selection, grow, mutation, xover, probability,
               new Generational() );
    }

    public GenerationalGA( Selection<P> parent_selection,
                           GrowingFunction<G,P> grow,
                           RefiningOperator<G> operator, double probability ) {
           super( parent_selection, grow, operator, probability,
                  new Generational() );
    }

}
