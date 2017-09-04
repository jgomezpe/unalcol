/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import unalcol.clone.Clone;
import unalcol.services.Service;
import unalcol.types.collection.sparse.vector.SparseVector;

/**
 *
 * @author jgomez
 */
public class SparseRealVectorCloneService implements Clone<SparseRealVector>{
    public SparseRealVectorCloneService() {}

    /**
     * Clones a Java Vector
     * @param obj The Java Vector to be cloned
     * @return A clone of the Java Vector
     */
    @SuppressWarnings("unchecked")
	@Override
    public SparseRealVector clone(SparseRealVector obj) throws Exception{    
        SparseRealVector x = new SparseRealVector( obj.dim() );
        x.values = (SparseVector<Double>)Service.run(Clone.name, obj.values);
        return x;
    }    
}