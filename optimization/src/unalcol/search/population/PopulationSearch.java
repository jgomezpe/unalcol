/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population;

import unalcol.search.Goal;
import unalcol.search.Search;
import unalcol.search.Solution;
import unalcol.search.space.Space;
import unalcol.search.space.SpaceSampler;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author Jonatan
 */
public abstract class PopulationSearch<T> implements Search<T> {
    protected SpaceSampler<T> sampler;
    protected int n;
    
    public PopulationSearch( SpaceSampler<T> sampler, int n ){
        this.sampler = sampler;
        this.n = n;
    }
    
    public abstract PopulationSolution<T> apply( PopulationSolution<T> pop, Space<T> space, Goal<T> goal );
    
    public PopulationSolution<T> apply( Vector<T> pop, double[] quality, Space<T> space, Goal<T> goal ){
    	return apply( new PopulationSolution<T>(pop, quality), space, goal );
    }

    public PopulationSolution<T> apply( Vector<T> pop, Space<T> space, Goal<T> goal ){
        return apply( pop, goal.quality(pop), space, goal );
    }
    
    @Override
    public Solution<T> apply(Space<T> space, Goal<T> goal) {
        Vector<T> pop = sampler.apply(space, n);
        double[] quality = goal.quality(pop);
        PopulationSolution<T> solution = apply( pop, quality, space, goal );
        return solution.pick();
    }
}