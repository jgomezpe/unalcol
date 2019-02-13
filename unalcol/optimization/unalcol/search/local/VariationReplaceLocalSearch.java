/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.local;

import unalcol.search.Goal;
import unalcol.search.replacement.GoalBasedReplacement;
import unalcol.search.replacement.Replacement;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation_1_1;
import unalcol.object.Tagged;

/**
 *
 * @author jgomez
 */
public class VariationReplaceLocalSearch<T> extends LocalSearch<T,Double>{
	
	protected Goal<T,Double> goal;
	
	protected Variation_1_1<T> variation;
    protected Replacement<T> replace;
    
	@SuppressWarnings("unchecked")
	public VariationReplaceLocalSearch( Variation_1_1<T> variation, Replacement<T> replace ){
        super();
        this.variation = variation;
        this.replace = replace;
        if( replace!=null && replace instanceof GoalBasedReplacement ) this.goal = ((GoalBasedReplacement<T,Double>)replace).goal();  
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void setGoal(Goal<T,Double> goal){
    	if( replace instanceof GoalBasedReplacement ) ((GoalBasedReplacement<T,Double>)replace).setGoal(goal);
    	this.goal = goal;
    }
        
    @Override 
	public Goal<T,Double> goal(){ return goal; }
    
    @Override
    public Tagged<T> apply(Tagged<T> x, Space<T> space){
        // Check if non stationary
		Tagged<T> y = replace.apply(x,variation.apply(space, x));
        trace(Tagged.class, x, PathTracer.PARENT, y);
        return y;
    }
}