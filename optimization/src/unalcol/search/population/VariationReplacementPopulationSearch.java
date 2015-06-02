/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population;

import unalcol.search.Goal;
import unalcol.search.population.variation.PopulationVariation;
import unalcol.search.space.Space;

/**
 *
 * @author Jonatan
 */
public class VariationReplacementPopulationSearch<T> extends PopulationSearch<T> {
    protected PopulationVariation<T> variation; 
    protected PopulationReplacement<T> replace;
    public VariationReplacementPopulationSearch( int n, PopulationVariation<T> variation, PopulationReplacement<T> replace){    	
        super( n );
        this.variation = variation;
        this.replace = replace;
    }

    @Override
    public PopulationSolution<T> apply( PopulationSolution<T> pop, Space<T> space, Goal<T> goal ){
    	PopulationSolution<T> newPop = new PopulationSolution<T>(variation.apply(space, pop.value()), goal);
    	pop.quality(goal);
    	return replace.apply(pop, newPop);
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
