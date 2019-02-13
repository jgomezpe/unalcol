/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.method.hillclimbing;


import unalcol.search.BasicGoalBased;
import unalcol.search.Goal;
import unalcol.search.replacement.GoalBasedReplacement;
import unalcol.object.Tagged;

/**
 *
 * @author jgomez
 */
public class HillClimbingReplacement<T> extends BasicGoalBased<T, Double> implements GoalBasedReplacement<T,Double> {
    protected boolean neutral = false;
    
    public HillClimbingReplacement(){}
    
    public HillClimbingReplacement( boolean neutral ){ this.neutral = neutral; }
    
    @Override
    public Tagged<T> apply(Tagged<T> current, Tagged<T> next) {
    	Goal<T,Double> goal=goal();
        if( neutral )
            return goal.compare(current, next) <= 0? next : current;
        else
            return goal.compare(current, next) < 0? next : current;
    }
}