package unalcol.search.variation;

import unalcol.object.Tagged;
import unalcol.clone.Cloneable;

public interface Variation_2_2<T> extends Variation_2_m<T>{
	@Override
	public default int range_arity() { return 2; };	
	
	/**
	 * Apply the genetic operator to the first and second individuals in the population of parents
	 * This method is parent compatible
	 * @param parents Collection of parents used by the genetic operator (selects just the first and second
	 * individuals in the collection
	 * @return A collection of individuals generated by the genetic operator
	 */
	@SuppressWarnings("unchecked")
	@Override
	public default T[] apply( T... parents ){
		T[] v = (T[])(new Object[parents.length]);
		int n = (v.length>>1)<<1;
		for( int i=0; i<n;i+=2){
			T[] p = apply( parents[i], parents[i+1] );
			v[i] = p[0]; 
			v[i+1] = p[1];
		}
		if( n < v.length ) v[n] = (T)Cloneable.cast(parents[n]).clone();
		return v;
	}    
    
	/**
	 * Apply the genetic operator to the first and second individuals in the population of parents
	 * This method is parent compatible
	 * @param parents Collection of parents used by the genetic operator (selects just the first and second
	 * individuals in the collection
	 * @return A collection of individuals generated by the genetic operator
	 */
	@Override
	@SuppressWarnings("unchecked")
	public default Tagged<T>[] apply( Tagged<T>... parents ){
		Tagged<T>[] v = new Tagged[parents.length];
		int n = (v.length>>1)<<1;
		for( int i=0; i<n;i+=2){
			Tagged<T>[] p = apply( parents[i], parents[i+1] );
			v[i] = p[0]; 
			v[i+1] = p[1];
		}
		if( n < v.length ) v[n]=(Tagged<T>)Cloneable.cast(parents[n]).clone();
		return v;
	}     
}