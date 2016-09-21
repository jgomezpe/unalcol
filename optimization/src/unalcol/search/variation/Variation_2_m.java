package unalcol.search.variation;

import unalcol.search.solution.Solution;

/**
 * <p>Title:ArityTwo</p>
 * <p>Description: A binary operator </p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Variation_2_m<T> extends Variation<T> {
    @SuppressWarnings("unchecked")
	public Solution<T>[] apply( Solution<T> one, Solution<T> two ){
    	T[] next = apply( one.object(), two.object() );    	
    	Solution<T>[] s = new Solution[next.length];
    	for( int i=0; i<s.length; i++ ){
    		s[i] = new Solution<T>(next[i], one.tags(), false);
    	}	
    	return s;
    }   

    @SuppressWarnings("unchecked")
	public T[] apply( T one, T two ){
    	Solution<T>[] next = apply( new Solution<T>(one), new Solution<T>(two) );    	
    	T[] s = (T[])new Object[next.length];
    	for( int i=0; i<s.length; i++ ){
    		s[i] = next[i].object();
    	}	
    	return s;
    }   

    /**
     * Return the genetic operator arity
     * @return the genetic operator arity
     */
    @Override
    public int arity() { return 2; }
}
