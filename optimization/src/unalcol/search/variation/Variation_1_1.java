/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.variation;

import unalcol.search.solution.Solution;
import unalcol.search.space.Space;

/**
 *
 * @author jgomez
 */
public class Variation_1_1<T> extends Variation_n_1<T>{

    public T apply( Space<T> space, T x ){ return space.repair(apply(x)); }

    public Solution<T> apply( Space<T> space, Solution<T> x ){
    	Solution<T> s = apply( x );
    	s.repair(space);
    	return s;
    }
    
    public T apply( T x ){
    	return apply( new Solution<T>(x) ).object();
    }   
    
    public Solution<T> apply( Solution<T> x ){
    	return new Solution<T>(apply(x.object()), x.tags(), false);
    }   
    
    @Override
	public int arity() {
		return 1;
	}
    
    @SuppressWarnings("unchecked")
 	public T[] apply(T... pop){
		T[] v = (T[])(new Object[pop.length]);
		for( int i=0; i<pop.length; i++ )
			v[i] = apply(pop[i]);
		return v; 
    }    

 	@SuppressWarnings("unchecked")
 	public Solution<T>[] apply(Solution<T>... pop){
		Solution<T>[] v = new Solution[pop.length];
		for( int i=0; i<pop.length; i++ )
			v[i] = apply(pop[i]);
		return v; 
    }
    
}