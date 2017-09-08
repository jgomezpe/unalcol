/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search;

import unalcol.Tagged;
import unalcol.search.space.Space;
import unalcol.search.space.SpaceSampler;

/**
 *
 * @author jgomez
 */
public interface Search<T,R> extends SpaceSampler<T>{
	public Tagged<T> solve( Space<T> space, Goal<T,R> goal );
	
	@Override
	public default T apply( Space<T> space ) {
		@SuppressWarnings("unchecked")
		Goal<T,R> goal = (Goal<T,R>)space.get(Goal.class.getName());
		if( goal != null ){
			return solve( space, goal ).unwrap();
		}	
		return null;
	}  
	
	public default void init(){}
}