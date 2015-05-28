/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.vector.sparse;

import unalcol.clone.Clone;
import unalcol.types.collection.vector.SortedVector;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public class ImmutableSparseVectorCloneService<T> extends Clone<ImmutableSparseVector<T>>{
    public ImmutableSparseVectorCloneService() {
        set(Clone.class, Vector.class, this);
    }

    /**
     * Clones a Java Vector
     * @param obj The Java Vector to be cloned
     * @return A clone of the Java Vector
     */
    @SuppressWarnings("unchecked")
	@Override
    public ImmutableSparseVector<T> clone(ImmutableSparseVector<T> obj){    
        return new ImmutableSparseVector<T>( (SortedVector<SparseElement<T>>)Clone.create(obj.vector) );
    }    
}