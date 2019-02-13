/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population;

import unalcol.search.Search;
import unalcol.search.space.Space;
import unalcol.object.Tagged;

/**
 *
 * @author Jonatan
 */
public abstract class PopulationSearch<T,R> extends Search<T,R>{

	public abstract Tagged<T>[] init( Space<T> space );
	public abstract Tagged<T> pick( Tagged<T>[] pop );
	
	@Override
    public Tagged<T> solve( Space<T> space ){ return pick(apply(init(space), space)); }   
    
    public abstract Tagged<T>[] apply( Tagged<T>[] pop, Space<T> space );    
}