/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author jgomez
 */
public class ArrayCollectionIterator<T>  implements Iterator<T> {
    protected int pos=-1;
    protected ArrayCollection<T> array;

    public ArrayCollectionIterator( int pos, ArrayCollection<T> array ) {
        this.array = array;
        this.pos = pos-1;
    }

    public ArrayCollectionIterator( ArrayCollectionLocation<T> location ) {
        this.array = location.array;
        this.pos = location.pos-1;
    }

    @Override
    public boolean hasNext(){
        return pos+1<array.size();
    }

    @Override
    public T next() throws NoSuchElementException{
        try{
            pos++;
            return array.get(pos);
        }catch( Exception e ){
            throw new NoSuchElementException( "" + pos );
        }
    }

    @Override
    public void remove() {
        if( array instanceof MutableArrayCollection ){
            ((MutableArrayCollection<T>)array).remove(pos);
        }
    }    
}
