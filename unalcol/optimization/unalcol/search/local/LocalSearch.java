/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.local;

import unalcol.instance.Instanteable;
import unalcol.search.Search;
import unalcol.search.space.Space;
import unalcol.object.Tagged;

/**
 *
 * @author Jonatan
 */
public abstract class LocalSearch<T,R> extends Search<T,R> {
    
    public abstract Tagged<T> apply( Tagged<T> x, Space<T> space );
    
    @Override
    public Tagged<T> solve(Space<T> space){
    	@SuppressWarnings("unchecked")
		Tagged<T> x = (Tagged<T>)Instanteable.cast(Tagged.class).create(space.pick());
        return apply(x, space);
    }
}