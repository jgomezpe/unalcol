package unalcol.math.function;

import unalcol.AbstractThing;
import unalcol.Tagged;
import unalcol.types.collection.Collection;
import unalcol.types.collection.GrowCollection;

/**
 * <p>Abstract definition of a function</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 * @param <S> Codomain of the function
 * @param <T> Domain of the function
 */
public interface Function<S, T> extends AbstractThing, Runnable{
	public static final String input="input"; 
	public static final String output="output"; 
	
	public default void setInput(S in){ set(Function.input,in); }
	public default void setOutput(T out){ set(Function.output,out); }
	@SuppressWarnings("unchecked")
	public default S input(){ return (S)get(Function.input); }
	@SuppressWarnings("unchecked")
	public default T output(){ return (T)get(Function.output); }
	/**
	 * Executes the algorithm on the given input
	 */
	public default void run() {
		start();
		setOutput(this.apply(input()));
	}

	/**
	 * Computes the function
	 * @param x Parameter of the function
	 * @return Computed value of the function
	 */
	public T apply(S x);
	    
	/**
	 * Determines if the fitness function is deterministic or not, i.e.,
	 * if the value of the function for a given value can change in time (non-stationary) 
	 * or not (stationary)
	 * @return <i>true</i> if the fitness function does not changes with time, <i>false</i> otherwise
	 */
	public default boolean deterministic(){ return true; }
 
	@SuppressWarnings("unchecked")
	public default T[] array( int n ){ return (T[])new Object[n]; }
	
	public default T[] array_apply( S[] x ){
		T[] r = array(x.length);
		for( int i=0; i<x.length; i++) r[i] = apply(x[i]);
		return r;
	}
	
	/**
	 * Computes the function for a given collection of objects
	 * @param Objects to be computed
	 * @return Values of the function, associated to the given collection of objects
	 */	
	public default Collection<T> col_apply( Collection<S> set ){ return new ApplyFunctionCollection<S,T>(this, set); }

	/**
	 * Gets the function values for a given collection of objects
	 * @param Objects to be evaluated
	 * @return Function values associated to the given collection of objects
	 */
	public default void col_apply( Collection<S> set, GrowCollection<T> target ){
		for( S x : set ) target.add(apply(x));
	}

	// Tagged methods
	
	/**
	 * Computes the function
	 * @param x Parameter of the function
	 * @return Computed value of the function
	 */
	@SuppressWarnings("unchecked")
	public default T apply(Tagged<S> x){
		if( !deterministic() || x.get(this) == null ) x.set(this, apply(x.unwrap()));
		return (T)x.get(this);
	}

	public default T[] array_apply( Tagged<S>[] x ){
		T[] r = array(x.length);
		for( int i=0; i<x.length; i++) r[i] = apply(x[i]);
		return r;
	}

	/**
	 * Computes the function for a given collection of objects
	 * @param Objects to be computed
	 * @return Values of the function, associated to the given collection of objects
	 */	
	public default Collection<T> tagged_col_apply( Collection<Tagged<S>> set ){ return new ApplyFunctionTaggedCollection<S,T>(this, set); }

	/**
	 * Gets the function values for a given collection of objects
	 * @param Objects to be evaluated
	 * @return Function values associated to the given collection of objects
	 */
	public default void tagged_col_apply( Collection<Tagged<S>> set, GrowCollection<T> target ){
		for( Tagged<S> x : set ) target.add(apply(x));
	}

	/**
	 * Stops the function computation
	 */
	public default void stop(){};

	/**
	 * Stars the possibility of computing the function
	 */
	public default void start(){};

	/**
	 * Determines if the function is running or not
	 * @return <i>true</i> if the function is running, <i>false</i> otherwise
	 */
	public default boolean running(){ return true; };
}