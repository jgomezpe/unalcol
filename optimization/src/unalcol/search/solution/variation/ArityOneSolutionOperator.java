/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.solution.variation;

import unalcol.search.solution.Solution;
import unalcol.search.variation.ArityOneSearchOperator;

/**
 *
 * @author jgomez
 */
public interface ArityOneSolutionOperator<T> extends ArityOneSearchOperator<T>, SolutionOperator<T>{    
 	public default T apply(T x){
    	return apply(new Solution<T>(x)).object();
    }

 	@SuppressWarnings("unchecked")
 	public default Solution<T>[] apply(Solution<T>... pop){
		Solution<T>[] v = (Solution<T>[])(new Object[pop.length]);
		for( int i=0; i<pop.length; i++ )
			v[i] = apply(pop[i]);
		return v; 
    }
}