/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.space;

import unalcol.reflect.tag.TaggedObject;

/**
 *
 * @author jgomez
 */
public abstract class Space<T> extends TaggedObject<T>{
	public static final String FEASIBLE="feasible";
	public static final String FEASIBILITY="feasibility";
	
	public Space(){ super(null); }
	
    public abstract boolean feasible( T x );

    
    public abstract double feasibility( T x );

//    public abstract Neighbourhood<T> neighbourhood( T x );
    
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

    public T[] repair( T[] pop ){
        @SuppressWarnings("unchecked")
		T[] v = (T[])new Object[pop.length];
        for( int i=0; i<pop.length; i++ ){
            v[i] = repair(pop[i]);
        }
        return v;
    }           
}