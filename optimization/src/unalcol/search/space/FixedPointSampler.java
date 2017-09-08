/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.space;

import unalcol.Thing;

/**
 *
 * @author Jonatan
 */
public class FixedPointSampler<T> extends Thing implements SpaceSampler<T>{
    protected T x;
    public FixedPointSampler( T x ){
        this.x = x;
    }
    
    @Override
    public T apply(Space<T> space) {
        return x;
    }    
}