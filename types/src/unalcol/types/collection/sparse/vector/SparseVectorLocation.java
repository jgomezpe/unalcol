/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.sparse.vector;

import java.util.NoSuchElementException;
import unalcol.types.collection.Location;
import unalcol.types.collection.vector.SortedVector;

/**
 *
 * @author jgomez
 */
public class SparseVectorLocation<T> implements Location<T> {
    protected int pos;
    protected SortedVector<SparseElement<T>> sparse_vector;

    public SparseVectorLocation( int pos, ImmutableSparseVector<T> sparse_vector ) {
        this.sparse_vector = sparse_vector.vector;
        this.pos = pos;
    }

    @Override
    public T get() throws NoSuchElementException{
        try{
            return sparse_vector.get(pos).value();
        }catch( Exception e ){
            throw new NoSuchElementException("Invalid index .." + pos);
        }
    }

}
