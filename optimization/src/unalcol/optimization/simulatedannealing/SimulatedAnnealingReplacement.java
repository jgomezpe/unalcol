/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.simulatedannealing;

import unalcol.optimization.hillclimbing.HillClimbingReplacement;
import unalcol.search.Solution;

/**
 *
 * @author jgomez
 */
public class SimulatedAnnealingReplacement<T> extends HillClimbingReplacement<T> {
    protected SimulatedAnnealingScheme scheme;
    protected int t=0;
    
    public SimulatedAnnealingReplacement( SimulatedAnnealingScheme scheme ){
        this.scheme = scheme;
    }
    
    public SimulatedAnnealingReplacement( SimulatedAnnealingScheme scheme, boolean neutral ){
        super( neutral );
        this.scheme = scheme;
    }
    
    @Override
    public Solution<T> apply( Solution<T> current, Solution<T> next ) {
        Solution<T> x = super.apply(current, next);
        if( x==next || Math.exp((next.quality()-current.quality())/scheme.get(t++)) < Math.random())
            return next;
        else
            return current;
    }    

    @Override
    public void init(){
        t=0;
    }    
}
