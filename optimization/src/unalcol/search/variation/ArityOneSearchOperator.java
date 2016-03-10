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
public interface ArityOneSearchOperator<T> extends SearchOperator<T>{
    public default T apply( Space<T> space, T x ){ return space.repair(apply(x)); }
    
    public T apply( T x );   
    public Solution<T> apply( Solution<T> x );   
    
    @Override
	public default int arity() {
		return 1;
	}
}