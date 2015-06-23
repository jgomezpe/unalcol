/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.space;

import unalcol.search.population.variation.Operator;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public abstract class ArityOne<T> extends Operator<T>{
    public T apply( Space<T> space, T x ){ return space.repair(apply(x)); }
    
    public abstract T apply( T x );   
    
    @SuppressWarnings("unchecked")
 	public Vector<T> apply(T... pop){
		Vector<T> v = new Vector<T>();
		for( T x : pop )
			v.add( apply(x) );
		return v; 
    }

    @Override
	public int arity() {
		return 1;
	}
}