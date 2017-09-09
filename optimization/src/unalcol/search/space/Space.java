/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.space;

import unalcol.Tagged;
import unalcol.services.AbstractMicroService;

/**
 *
 * @author jgomez
 */
public interface Space<T> extends AbstractMicroService<T>{
	public static final String FEASIBLE="feasible";
	public static final String FEASIBILITY="feasibility";
	
	public boolean feasible( T x );
	
	public double feasibility( T x );

	public T repair( T x );
    
	public T pick();

	public default T[] pick( int n ){
		@SuppressWarnings("unchecked")
		T[] v = (T[])new Object[n];
		for( int i=0; i<n; i++ ){
			v[i] = pick();
		}
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
		for( int i=0; i<pop.length; i++ ) v[i] = new Tagged<T>(repair(pop[i].unwrap()));
		return v;
	}           	
	
	// The MicroService methods
	public static final String name="space";
	public static final String pick=name+".pick";
	public static final String repair=name+".repair";
	public static final String feasible=name+".feasible";
	public static final String feasibility=name+".feasibility";
	
	public static final String[] methods = new String[]{name,pick,repair,feasible,feasibility};
	
	@Override
	public default String[] provides(){ return methods; }

	@Override
	public default Object run( Object... args ) throws Exception{
		// @TODO Not sure how it is going to be...
		String service = name();
		if(service.equals(name) || service.equals(pick) ){
		}
		throw new Exception("Undefined service "+service);		
	}
	
	
}