/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.hillclimbing;

import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.math.logic.Predicate;
import unalcol.search.Solution;
import unalcol.search.single.IterativeSinglePointSearch;
import unalcol.search.single.VariationReplaceSinglePointSearch;
import unalcol.search.space.Variation;

/**
 *
 * @author jgomez
 */
public class HillClimbing<T> extends IterativeSinglePointSearch<T>{
    public HillClimbing( Variation<T> variation, HillClimbingReplacement<T> replace, Predicate<Solution<T>> tC ){
        super(new VariationReplaceSinglePointSearch<>(variation, replace), tC);
    }

    public HillClimbing( Variation<T> variation, boolean neutral, Predicate<Solution<T>> tC ){
        this( variation, new HillClimbingReplacement<>(neutral), tC );
    }
    
    public HillClimbing( Variation<T> variation, boolean neutral, int MAX_ITERS ){
        this( variation, new HillClimbingReplacement<>(neutral), new ForLoopCondition<Solution<T>>(MAX_ITERS) );
    }

    public HillClimbing( Variation<T> variation, int MAX_ITERS ){
        this( variation, true, new ForLoopCondition<Solution<T>>(MAX_ITERS) );
    }        
    
}