/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population;

import unalcol.search.Goal;
import unalcol.search.solution.Solution;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation;

/**
 *
 * @author Jonatan
 */
public abstract class VariationReplacePopulationSearch<T,R> implements PopulationSearch<T, R> {
	protected int mu; // Population size
    protected Variation<T> variation; 
    protected PopulationReplacement<T> replace;

    public VariationReplacePopulationSearch( int mu, Variation<T> variation, PopulationReplacement<T> replace){    	
    	this.mu = mu;
    	this.variation = variation;
    	this.replace = replace;
    }

    @Override
    public Population<T> apply( Population<T> pop, Space<T> space ){
    	String gName = Goal.class.getName();
    	Population<T> newPop =
    			new Population<T>(variation.apply(space, pop.object()));
    	newPop.set( gName, pop.data(gName) );
    	return replace.apply(pop, newPop);
    }

	@Override
	public Population<T> init(Space<T> space, Goal<T, R> goal) {
    	Population<T> pop = new Population<T>((Solution<T>[])wrap(space.pick(mu)));
    	pop.set( Goal.class.getName(), goal);
    	return pop;
	}
}