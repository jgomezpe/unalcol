package unalcol.evolution.haea;
import unalcol.optimization.transformation.Transformation;
import unalcol.optimization.replacement.Replacement;
import unalcol.optimization.selection.Selection;
import unalcol.evolution.*;

/**
 * <p>Title: HAEA</p>
 * <p>Description: The Hybrid Adaptive Evolutionary Algorithm proposed by Gomez in
 * "Self Adaptation of Operator Rates in Evolutionary Algorithms", Proceedings of Gecco 2004.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class HAEA<G,P> extends Transformation<P> {

    public HAEA( HaeaStrategy<G,P> strategy ){
        super( strategy, new HaeaReplacement(strategy.operators) );
    }

    public HAEA( HaeaStrategy<G,P> strategy, Replacement<P> replacement ){
        super( strategy, replacement );
    }

    public HAEA( HaeaOperators<G> operators, GrowingFunction<G,P> grow, Selection<P> selection) {
        this( new HaeaStrategy( operators, grow, selection ) );
    }
}