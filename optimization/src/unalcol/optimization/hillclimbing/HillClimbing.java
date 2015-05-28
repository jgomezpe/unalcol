/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.hillclimbing;

import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.math.logic.Predicate;
import unalcol.search.Solution;
import unalcol.search.single.IterativeSimplePointSearch;
import unalcol.search.single.VariationReplaceSimplePointSearch;
import unalcol.search.space.SpaceSampler;
import unalcol.search.space.Variation;

/**
 *
 * @author jgomez
 */
public class HillClimbing<T> extends IterativeSimplePointSearch<T>{
    public HillClimbing( SpaceSampler<T> sampler, Variation<T> variation, HillClimbingReplacement<T> replace, Predicate<Solution<T>> tC ){
        super(new VariationReplaceSimplePointSearch<>(sampler, variation, replace), tC);
    }

    public HillClimbing( SpaceSampler<T> sampler, Variation<T> variation, boolean neutral, Predicate<Solution<T>> tC ){
        this( sampler, variation, new HillClimbingReplacement<>(neutral), tC );
    }
    
    public HillClimbing( SpaceSampler<T> sampler, Variation<T> variation, boolean neutral, int MAX_ITERS ){
        this( sampler, variation, new HillClimbingReplacement<>(neutral), new ForLoopCondition<Solution<T>>(MAX_ITERS) );
    }        
}