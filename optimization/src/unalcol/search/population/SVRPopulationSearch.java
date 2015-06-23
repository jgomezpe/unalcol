/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population;

import unalcol.search.Goal;
import unalcol.search.population.variation.PopulationVariation;
import unalcol.search.selection.Selection;
import unalcol.search.space.Space;

/**
 *
 * @author Jonatan
 */
public class SVRPopulationSearch<T> extends PopulationSearch<T> {
	protected Selection<T> selection;
    protected PopulationVariation<T> variation; 
    protected PopulationReplacement<T> replace;
    public SVRPopulationSearch( int n, Selection<T> selection, 
    							PopulationVariation<T> variation, PopulationReplacement<T> replace){    	
        super( n );
        this.selection = selection;
        this.variation = variation;
        this.replace = replace;
    }

    @Override
    public PopulationSolution<T> apply( PopulationSolution<T> pop, Space<T> space, Goal<T> goal ){
    	PopulationSolution<T> newPop = new PopulationSolution<T>(variation.apply(space, selection.apply(n, pop.value(), pop.quality())), goal);
    	pop.quality(goal);
    	return replace.apply(pop, newPop);
    }

    @Override
    public void init() {
    }
    
}
