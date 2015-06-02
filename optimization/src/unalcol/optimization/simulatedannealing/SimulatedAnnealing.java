/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.simulatedannealing;

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
public class SimulatedAnnealing<T> extends IterativeSinglePointSearch<T>{
    public SimulatedAnnealing( Variation<T> variation, SimulatedAnnealingReplacement<T> replace, Predicate<Solution<T>> tC ){
        super(  new VariationReplaceSinglePointSearch<>( variation, replace),  tC );
    }

    public SimulatedAnnealing( Variation<T> variation, SimulatedAnnealingScheme scheme, Predicate<Solution<T>> tC ){
        this( variation, new SimulatedAnnealingReplacement<T>(scheme), tC );
    }
    
    public SimulatedAnnealing( Variation<T> variation, int K, Predicate<Solution<T>> tC ){
        this( variation, new SimulatedAnnealingReplacement<T>(new SimpleSimulatedAnnealingScheme(K)), tC );
    }       

    public SimulatedAnnealing( Variation<T> variation, int MAXITERS ){
        this( variation, MAXITERS, new ForLoopCondition<Solution<T>>(MAXITERS) );
    }       
}