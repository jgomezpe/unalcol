/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.sparse.vector;

import java.util.Iterator;
import java.util.NoSuchElementException;
import unalcol.types.collection.array.ArrayCollectionIterator;

/**
 *
 * @author jgomez
 */
public class SparseVectorIterator<T> implements Iterator<T> {
    protected ArrayCollectionIterator<SparseElement<T>> inner;

    public SparseVectorIterator( int pos, ImmutableSparseVector<T> vector ) {
        this.inner = new ArrayCollectionIterator<>(pos,vector.vector);
    }

    public SparseVectorIterator( SparseVectorLocation<T> location ) {
        inner = new ArrayCollectionIterator<>(location.pos,location.sparse_vector);
    }

    @Override
    public boolean hasNext(){
        return inner.hasNext();
    }

    @Override
    public T next() throws NoSuchElementException{
        return inner.next().value();
    }

    @Override
    public void remove() {
        inner.remove();
    }
    
    
}

