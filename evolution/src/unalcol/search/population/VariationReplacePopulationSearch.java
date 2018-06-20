/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population;

import unalcol.search.space.Space;
import unalcol.search.variation.Variation;
import unalcol.types.object.tagged.Tagged;

/**
 *
 * @author Jonatan
 */
public abstract class VariationReplacePopulationSearch<T,R> implements PopulationSearch<T, R>{
	protected int mu; // Population size
    protected Variation<T> variation; 
    protected PopulationReplacement<T> replace;

    public VariationReplacePopulationSearch( int mu, Variation<T> variation, PopulationReplacement<T> replace){    	
    	this.mu = mu;
    	this.variation = variation;
    	this.replace = replace;
    }

    @Override
    public Tagged<T>[] apply( Tagged<T>[] pop, Space<T> space ){
    	return replace.apply(pop, variation.apply(space, pop));
    }

	@Override
	public Tagged<T>[] init(Space<T> space){
		T[] sol = space.pick(mu);
    	@SuppressWarnings("unchecked")
		Tagged<T>[] pop = new Tagged[sol.length];
    	for( int i=0; i<pop.length; i++ ) pop[i] = new Tagged<T>(sol[i]);
    	return pop;
	}
}