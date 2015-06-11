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
public abstract class Variation<T>{
    public T apply( Space<T> space, T x ){ return space.repair(apply(x)); }
    public abstract T apply( T x );
    public void adapt( double productivity ){}    
    
    public Vector<T> apply( Vector<T> pop ){
    	Vector<T> v = new Vector<T>();
    	for( T x : pop ){
    		v.add(apply(x));
    	}
    	return v;    	
    }
    
    public Vector<T> apply( Space<T> space, Vector<T> pop ){
    	return space.repair(apply(pop));
    }
}
