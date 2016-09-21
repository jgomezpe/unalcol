/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn;

import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.array.MutableArrayCollection;

/**
 *
 * @author jgomez
 */
public interface Labeler<T> {
	/**
	 * Gets a label for the given object
	 * @param obj Object to be labeled
	 * @return A label for the object 
	 */
	public int label( T obj );
	
	/**
	 * Gets the labels for a given collection of objects
	 * @param Objects to be labeled
	 * @return Labels associated to the given collection of objects
	 */
    public default int[] label( ArrayCollection<T> set ){
        int[] tag = new int[set.size()];
        int i=0;
        for( T x : set ){
            tag[i] = label(x);
            i++;
        }
        return tag;    	
    }
    
	/**
	 * Gets the labels for a given collection of objects
	 * @param Objects to be labeled
	 * @return Labels associated to the given collection of objects
	 */
    public default void label( ArrayCollection<T> set, MutableArrayCollection<Integer> labels ){
    	labels.clear();
        for( T x : set ){
        	labels.add(label(x));
        }
    }
}
