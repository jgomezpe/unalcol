/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population;

import unalcol.search.Goal;
import unalcol.search.Search;
import unalcol.search.solution.Solution;
import unalcol.search.solution.SolutionManager;
import unalcol.search.space.Space;

/**
 *
 * @author Jonatan
 */
public interface PopulationSearch<T,R> extends SolutionManager<T>, Search<T,R>{

	public Population<T> init( Space<T> space, Goal<T,R> goal );
	public Solution<T> pick( Population<T> pop );
	
	@Override
    public default Solution<T> solve( Space<T> space, Goal<T,R> goal ){
    	return pick(apply(init(space,goal), space));
    }   
    
    public Population<T> apply( Population<T> pop, Space<T> space );    
}