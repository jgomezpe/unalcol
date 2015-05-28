/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.vector;

import unalcol.clone.*;

/**
 *
 * @author jgomez
 */
public class VectorCloneService<T> extends Clone<Vector<T>>{
    public VectorCloneService() {
    }

    @Override
    public Object owner(){
        return Vector.class;
    }

    /**
     * Clones a Vector
     * @return The vector's clone
     */
    @SuppressWarnings("unchecked")
	@Override
    public Vector<T> clone(Vector<T> obj){
        return new Vector<T>( (T[])ImmutableVectorCloneService.toArray(obj), obj.size() );
    }
}
