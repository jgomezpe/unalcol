/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.vector.sparse;

import unalcol.clone.Clone;
import unalcol.types.collection.vector.SortedVector;

/**
 *
 * @author jgomez
 */
public class SparseVectorCloneService<T> extends Clone<SparseVector<T>>{
    public SparseVectorCloneService() {
    }

    public Object owner(){
        return SparseVector.class;
    }

    /**
     * Clones a Java Vector
     * @param obj The Java Vector to be cloned
     * @return A clone of the Java Vector
     */
    @SuppressWarnings("unchecked")
	@Override
    public SparseVector<T> clone(SparseVector<T> obj){    
        return new SparseVector<T>( (SortedVector<SparseElement<T>>)Clone.create(obj.vector) );
    }    
}
