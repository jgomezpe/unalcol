/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.simulatedannealing;

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
public class SimulatedAnnealing<T> extends IterativeSimplePointSearch<T>{
    public SimulatedAnnealing( SpaceSampler<T> sampler, Variation<T> variation, SimulatedAnnealingReplacement<T> replace, Predicate<Solution<T>> tC ){
        super(  new VariationReplaceSimplePointSearch<>(sampler, variation, replace),  tC );
    }

    public SimulatedAnnealing( SpaceSampler<T> sampler, Variation<T> variation, SimulatedAnnealingScheme scheme, Predicate<Solution<T>> tC ){
        this( sampler, variation, new SimulatedAnnealingReplacement<T>(scheme), tC );
    }
    
    public SimulatedAnnealing( SpaceSampler<T> sampler, Variation<T> variation, int K, Predicate<Solution<T>> tC ){
        this( sampler, variation, new SimulatedAnnealingReplacement<T>(new SimpleSimulatedAnnealingScheme(K)), tC );
    }       
}