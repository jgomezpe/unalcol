/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population;

import unalcol.Tagged;
import unalcol.TaggedManager;
import unalcol.Thing;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation;

/**
 *
 * @author Jonatan
 */
public abstract class VariationReplacePopulationSearch<T,R> extends Thing implements PopulationSearch<T, R>, TaggedManager<T> {
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
	public Tagged<T>[] init(Space<T> space) {
    	Tagged<T>[] pop = wrap(space.pick(mu));
    	return pop;
	}
}