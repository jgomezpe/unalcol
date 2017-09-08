/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.method.hillclimbing;

import unalcol.Tagged;
import unalcol.search.Goal;
import unalcol.search.replacement.GoalBasedReplacement;

/**
 *
 * @author jgomez
 */
public class HillClimbingReplacement<T> extends GoalBasedReplacement<T,Double> {
    protected boolean neutral = false;
    
    public HillClimbingReplacement( Goal<T,Double> goal ){ super(goal); }
    
    public HillClimbingReplacement(  Goal<T,Double> goal , boolean neutral ){
    	super(goal);
        this.neutral = neutral;
    }
    
    @Override
    public Tagged<T> apply(Tagged<T> current, Tagged<T> next) {
        if( neutral )
            return goal.compare(current, next) <= 0? next : current;
        else
            return goal.compare(current, next) < 0? next : current;
    }    

    @Override
    public void init(){}   
}