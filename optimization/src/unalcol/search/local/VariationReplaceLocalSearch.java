/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.local;

import unalcol.Tagged;
import unalcol.Thing;
import unalcol.search.replacement.Replacement;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation_1_1;
import unalcol.services.Service;
import unalcol.tracer.Tracer;

/**
 *
 * @author jgomez
 */
public class VariationReplaceLocalSearch<T> extends Thing implements LocalSearch<T,Double> {
    protected Variation_1_1<T> variation;
    protected Replacement<T> replace;
    
    public VariationReplaceLocalSearch( Variation_1_1<T> variation, Replacement<T> replace ){
        super();
        this.variation = variation;
        this.replace = replace;
    }
        
    @Override
    public Tagged<T> apply(Tagged<T> x, Space<T> space){
        // Check if non stationary
		Tagged<T> y = replace.apply(x,variation.apply(space, x));
        try{ Service.run(Tracer.name,this, Tagged.class, x, PathTracer.PARENT, y); }catch(Exception e){}
        return y;
    }    
}