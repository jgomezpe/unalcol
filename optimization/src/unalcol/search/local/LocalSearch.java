/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.local;

import unalcol.Tagged;
import unalcol.search.Search;
import unalcol.search.space.Space;
import unalcol.services.Service;
import unalcol.tracer.Tracer;

/**
 *
 * @author Jonatan
 */
public interface LocalSearch<T,R> extends Search<T,R> {
    
    public Tagged<T> apply( Tagged<T> x, Space<T> space );
    
    @Override
    public default Tagged<T> solve(Space<T> space){
    	Tagged<T> x = new Tagged<T>(space.pick());
        try{ Service.run(Tracer.name,Tagged.class, x); }catch(Exception e){}
        return apply(x, space);
    }
}