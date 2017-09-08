/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.method.annealing;

import unalcol.optimization.method.hillclimbing.HillClimbingReplacement;
import unalcol.search.Goal;
import unalcol.Tagged;

/**
 *
 * @author jgomez
 */
public class SimulatedAnnealingReplacement<T> extends HillClimbingReplacement<T> {
    protected SimulatedAnnealingScheme scheme;
    protected int t=0;
    
    public SimulatedAnnealingReplacement( Goal<T,Double> goal, SimulatedAnnealingScheme scheme ){
    	super(goal);
        this.scheme = scheme;
    }
    
    public SimulatedAnnealingReplacement( Goal<T,Double> goal, SimulatedAnnealingScheme scheme, boolean neutral ){
        super( goal, neutral );
        this.scheme = scheme;
    }
    
    @Override
    public Tagged<T> apply( Tagged<T> current, Tagged<T> next ) {
        Tagged<T> x = super.apply(current, next);
        if( x==next || Math.exp(-Math.abs(goal.apply(x)-goal.apply(current))/scheme.get(t++)) > Math.random())
            return next;
        else
            return current;
    }    

    @Override
    public void init(){
        t=0;
    }    
}