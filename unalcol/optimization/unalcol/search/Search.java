/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search;

import unalcol.search.space.Space;
import unalcol.search.space.SpaceSampler;
import unalcol.object.Tagged;

/**
 *
 * @author jgomez
 */
public abstract class Search<T,R> extends SpaceSampler<T> implements GoalBased<T, R> {	
	public abstract Tagged<T> solve( Space<T> space );
	
	@Override
	public T apply( Space<T> space ){ return solve( space ).unwrap(); }  
	
	public void init(){}
}