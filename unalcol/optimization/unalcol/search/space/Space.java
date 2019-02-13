/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.space;

import unalcol.instance.Instanteable;
import unalcol.object.Tagged;

/**
 *
 * @author jgomez
 */
public interface Space<T>{
	public static final String FEASIBLE="feasible";
	public static final String FEASIBILITY="feasibility";
	
	public boolean feasible( T x );
	
	public double feasibility( T x );

	public T repair( T x );
    
	public T pick();

	public default T[] pick( int n ){
		@SuppressWarnings("unchecked")
		T[] v = (T[])new Object[n];
		for( int i=0; i<n; i++ ) v[i] = pick();
		return v;
	}

	@SuppressWarnings("unchecked")
	public default T[] repair( T... pop ){
		T[] v = (T[])new Object[pop.length];
		for( int i=0; i<pop.length; i++ ) v[i] = repair(pop[i]);
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public default Tagged<T>[] repair( Tagged<T>... pop ){
		Tagged<T>[] v = (Tagged<T>[])new Tagged[pop.length];
		for( int i=0; i<pop.length; i++ ) v[i] = (Tagged<T>)Instanteable.cast(pop[i]).create(pop[i].unwrap());
		return v;
	}	
}