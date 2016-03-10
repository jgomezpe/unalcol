/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.local;

import unalcol.search.Goal;
import unalcol.search.Search;
import unalcol.search.solution.Solution;
import unalcol.search.space.Space;
import unalcol.tracer.Tracer;

/**
 *
 * @author Jonatan
 */
public abstract class LocalSearch<T,R> implements Search<T,R> {
    
    public LocalSearch(){}
    
    public abstract Solution<T> apply( Solution<T> x, Space<T> space );
    
    @Override
    public Solution<T> solve(Space<T> space, Goal<T,R> goal){
    	Solution<T> x = new Solution<T>(space.pick());
    	x.set(Goal.class.getName(), goal);
        Tracer.trace(Solution.class, x);
        return apply(x, space);
    }
}