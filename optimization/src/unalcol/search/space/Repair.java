/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.space;

import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public class Repair<T> {
    public T apply( Space<T> space, T x ){
        return x;
    }
    
    public Vector<T> apply( Space<T> space, Vector<T> pop ){
        Vector<T> v = new Vector<>();
        for( T x:pop ){
            v.add(apply(space,x));
        }
        return v;
    }    
    
}
