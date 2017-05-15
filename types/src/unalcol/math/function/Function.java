package unalcol.math.function;

import unalcol.types.collection.FiniteCollection;
import unalcol.types.collection.MutableCollection;

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
public interface Function<S, T>{
    /**
     * Computes the function
     * @param x Parameter of the function
     * @return Computed value of the function
     */
    public T apply(S x);
    
    /**
     * Computes the function for a given collection of objects
     * @param Objects to be computed
     * @return Values of the function, associated to the given collection of objects
     */	
    public default T[] apply( FiniteCollection<S> set ){
	@SuppressWarnings("unchecked")
	T[] tag = (T[])new Object[set.size()];
	int i=0;
	for( S x : set ){
	    tag[i] = apply(x);
	    i++;
	}
	return tag;    	
    }
    
	/**
	 * Gets the function values for a given collection of objects
	 * @param Objects to be evaluated
	 * @return Function values associated to the given collection of objects
	 */
    public default void apply( FiniteCollection<S> set, MutableCollection<T> labels ){
	labels.clear();
	for( S x : set ){
    		labels.add(apply(x));
	}	
    }
}