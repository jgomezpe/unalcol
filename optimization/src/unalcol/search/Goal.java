/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search;

import unalcol.reflect.tag.TaggedMethod;
import unalcol.reflect.tag.TaggedObject;
import unalcol.search.solution.Solution;

/**
 *
 * @author jgomez
 */
public interface Goal<T, R> extends TaggedMethod<T,R> {
	public static final String GOAL_TEST = "goal.test";

	@SuppressWarnings("unchecked")
	public default R[] array( int n ){
		return (R[])new Object[n];
	}
	
	public R apply( T x );

	public default R[] apply( T[] x ){
		R[] r = array(x.length);
		for( int i=0; i<x.length; i++) r[i] = apply(x[i]);
		return r;
	}
	
    @SuppressWarnings("unchecked")
	@Override
    public default R apply( TaggedObject<T> x ){
    	if( !nonStationary() || x.info(GOAL_TEST)==null ){
    		x.set(GOAL_TEST, apply(x.object())); 
    	}
    	return (R)x.info(GOAL_TEST);
    }

	public default R[] apply( Solution<T>[] x ){
		R[] r = array(x.length);
		for( int i=0; i<x.length; i++) r[i] = apply(x[i]);
		return r;
	}

	public abstract boolean nonStationary();      
}