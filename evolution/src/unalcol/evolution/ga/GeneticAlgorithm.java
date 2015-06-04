package unalcol.evolution.ga;

import unalcol.optimization.transformation.Transformation;
import unalcol.optimization.selection.Selection;
import unalcol.optimization.replacement.Replacement;
import unalcol.evolution.*;
import unalcol.optimization.operators.ArityOne;
import unalcol.optimization.operators.ArityTwo;
import unalcol.optimization.operators.RefiningOperator;

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
public class GeneticAlgorithm<G,P> extends Transformation<P> {
    public GeneticAlgorithm( Selection<P> parent_selection,
                             GrowingFunction<G,P> grow,
                             ArityOne<G> mutation, ArityTwo<G> xover,
                             double probability,
                             Replacement<P> replacement ) {
        super( new ClassicStrategy( parent_selection, grow, mutation, xover, probability ),
               replacement );
    }

    public GeneticAlgorithm( Selection<P> parent_selection,
                             GrowingFunction<G,P> grow,
                             RefiningOperator<G> operator, double probability,
                             Replacement<P> replacement ) {
        super( new ClassicStrategy(parent_selection, grow, operator, probability ),
               replacement );
    }
}