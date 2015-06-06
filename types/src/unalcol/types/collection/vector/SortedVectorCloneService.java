/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.vector;

import unalcol.clone.Clone;

/**
 *
 * @author jgomez
 */
public class SortedVectorCloneService<T> extends Clone<SortedVector<T>>{
    public SortedVectorCloneService() {
    }

    /**
     * Clones a Java Vector
     * @param obj The Java Vector to be cloned
     * @return A clone of the Java Vector
     */
    @SuppressWarnings("unchecked")
	@Override
    public SortedVector<T> clone(SortedVector<T> obj){    
        return new SortedVector<>( (T[])ImmutableVectorCloneService.toArray(obj), obj.size(), obj.order );
    }    

}
