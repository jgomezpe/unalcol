package unalcol.evolution.haea;
import unalcol.optimization.replacement.Replacement;
import unalcol.optimization.selection.Selection;
import unalcol.evolution.*;

/**
 * <p>Title: CAHAEA</p>
 * <p>Description: The Cellular Automata based Hybrid Adaptive Evolutionary Algorithm as
 * proposed by Cantor and Gomez in , Proceedings of WCCI 2010.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class CAHAEA<G,P> extends HAEA<G,P>{
    public CAHAEA( HaeaOperators<G> operators, GrowingFunction<G,P> grow, Selection<P> selection) {
        super( new CAHaeaStrategy<G,P>( operators, grow, selection ) );
    }

    public CAHAEA( HaeaOperators<G> operators, GrowingFunction<G,P> grow,
                 Selection<P> selection, Replacement<P> replacement ) {
        super( new CAHaeaStrategy<G,P>( operators, grow, selection ), replacement );
    }

}