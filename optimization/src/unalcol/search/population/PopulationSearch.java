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
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author Jonatan
 */
public abstract class PopulationSearch<T> implements Search<T> {
    protected int n;
    
    public PopulationSearch( int n ){
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
    	PopulationSolution<T> solution = new PopulationSolution<T>(space.get(n), goal);
        solution = apply( solution, space, goal );
        return solution.pick();
    }
}