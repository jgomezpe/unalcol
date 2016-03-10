/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.local;

import unalcol.search.Goal;
import unalcol.search.solution.Solution;
import unalcol.search.space.Space;
import unalcol.search.variation.ArityOneSearchOperator;
import unalcol.tracer.Tracer;

/**
 *
 * @author jgomez
 */
public class VariationReplaceLocalSearch<T> extends LocalSearch<T,Double> {
    protected ArityOneSearchOperator<T> variation;
    protected Replacement<T> replace;
    
    public VariationReplaceLocalSearch( ArityOneSearchOperator<T> variation, Replacement<T> replace ){
        super();
        this.variation = variation;
        this.replace = replace;
    }
        
    @Override
    public Solution<T> apply(Solution<T> x, Space<T> space){
        // Check if non stationary
        @SuppressWarnings("unchecked")
		Solution<T> y = variation.apply(space, x)[0];
        y.set(Goal.class.getName(), x.data(Goal.class.getName()));
        Solution<T> z = replace.apply(x, y);
        Tracer.trace(Solution.class, x, z);
        return z;
    }    
}