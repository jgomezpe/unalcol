/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.hillclimbing;

import unalcol.search.single.Replacement;
import unalcol.search.Solution;

/**
 *
 * @author jgomez
 */
public class HillClimbingReplacement<T> implements Replacement<T> {
    protected boolean neutral = false;
    
    public HillClimbingReplacement(){}
    
    public HillClimbingReplacement( boolean neutral ){
        this.neutral = neutral;
    }
    
    @Override
    public Solution<T> apply(Solution<T> current, Solution<T> next) {
        if( neutral )
            return current.quality() <= next.quality()? next : current;
        else
            return current.quality() < next.quality()? next : current;
    }    

    @Override
    public void init(){}   
}