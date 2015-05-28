/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.single;

import unalcol.search.Goal;
import unalcol.search.Search;
import unalcol.search.Solution;
import unalcol.search.space.Space;
import unalcol.search.space.SpaceSampler;

/**
 *
 * @author Jonatan
 */
public abstract class SimplePointSearch<T> implements Search<T> {
    protected SpaceSampler<T> sampler;
    
    public SimplePointSearch( SpaceSampler<T> sampler ){
        this.sampler = sampler;
    }
    
    public abstract Solution<T> apply( Solution<T> solution, Space<T> space, Goal<T> goal );
    
    @Override
    public Solution<T> apply(Space<T> space, Goal<T> goal){
        return apply(new Solution<T>(sampler.apply(space), goal), space, goal);
    }
}
