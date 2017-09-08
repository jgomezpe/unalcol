/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.space;

import unalcol.Tagged;
import unalcol.Thing;

/**
 *
 * @author jgomez
 */
public abstract class Space<T> extends Thing{
	public static final String FEASIBLE="feasible";
	public static final String FEASIBILITY="feasibility";
	
	public abstract boolean feasible( T x );
	
	public abstract double feasibility( T x );

	public abstract T repair( T x );
    
	public abstract T pick();

	public T[] pick( int n ){
		@SuppressWarnings("unchecked")
		T[] v = (T[])new Object[n];
		for( int i=0; i<n; i++ ){
			v[i] = pick();
		}
		return v;
	}

	@SuppressWarnings("unchecked")
	public T[] repair( T... pop ){
		T[] v = (T[])new Object[pop.length];
		for( int i=0; i<pop.length; i++ ) v[i] = repair(pop[i]);
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Tagged<T>[] repair( Tagged<T>... pop ){
		Tagged<T>[] v = (Tagged<T>[])new Tagged[pop.length];
		for( int i=0; i<pop.length; i++ ) v[i] = new Tagged<T>(repair(pop[i].unwrap()));
		return v;
	}           	
}