/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.single;

import unalcol.search.Solution;
import unalcol.search.Goal;
import unalcol.search.space.Space;
import unalcol.search.space.SpaceSampler;
import unalcol.search.space.Variation;

/**
 *
 * @author jgomez
 */
public class VariationReplaceSimplePointSearch<T> extends SimplePointSearch<T> {
    protected Variation<T> variation;
    protected Replacement<T> replace;
    
    public VariationReplaceSimplePointSearch(  SpaceSampler<T> sampler, Variation<T> variation,
                          Replacement<T> replace ){
        super( sampler );
        this.variation = variation;
        this.replace = replace;
    }
    
    @Override
    public void init(){}

    @Override
    public Solution<T> apply(Solution<T> x, Space<T> space, Goal<T> goal){
        // Check if non stationary
        x.quality(goal);        
        return replace.apply(x, new Solution<T>(variation.apply(space, x.value()),goal));
    }    
}
