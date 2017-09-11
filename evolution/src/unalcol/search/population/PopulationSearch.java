/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population;

import unalcol.Tagged;
import unalcol.search.Search;
import unalcol.search.space.Space;

/**
 *
 * @author Jonatan
 */
public interface PopulationSearch<T,R> extends Search<T,R>{

	public Tagged<T>[] init( Space<T> space );
	public Tagged<T> pick( Tagged<T>[] pop );
	
	@Override
    public default Tagged<T> solve( Space<T> space ){
    	return pick(apply(init(space), space));
    }   
    
    public Tagged<T>[] apply( Tagged<T>[] pop, Space<T> space );    
}