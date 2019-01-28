/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.local;

import unalcol.instance.Instanteable;
import unalcol.search.Search;
import unalcol.search.space.Space;
import unalcol.types.object.Tagged;

/**
 *
 * @author Jonatan
 */
public interface LocalSearch<T,R> extends Search<T,R> {
    
    public Tagged<T> apply( Tagged<T> x, Space<T> space );
    
    @Override
    public default Tagged<T> solve(Space<T> space){
    	@SuppressWarnings("unchecked")
		Tagged<T> x = (Tagged<T>)Instanteable.cast(Tagged.class).create(space.pick());
        trace(x);
        return apply(x, space);
    }
}