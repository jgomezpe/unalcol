/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.variation;

import unalcol.Tagged;
import unalcol.search.space.Space;

/**
 *
 * @author jgomez
 */
public interface Variation_1_1<T> extends Variation_n_1<T>{

	public default T apply( Space<T> space, T x ){ return space.repair(apply(x)); }

	@SuppressWarnings("unchecked")
	public default Tagged<T> apply( Space<T> space, Tagged<T> x ){
		return space.repair(apply(x))[0];
	}
    
	public default T apply( T x ){ return apply( new Tagged<T>(x) ).unwrap(); }   
    
	public default Tagged<T> apply( Tagged<T> x ){ return new Tagged<T>(apply(x.unwrap())); }   
    
	@Override
	public default int arity(){	return 1; }    
}