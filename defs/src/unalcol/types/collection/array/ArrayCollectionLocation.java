/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.array;

import java.util.NoSuchElementException;
import unalcol.types.collection.Location;

/**
 *
 * @author jgomez
 */
public class ArrayCollectionLocation<T> implements Location<T> {
    protected int pos;
    protected ArrayCollection<T> array;

    public ArrayCollectionLocation( int pos, ArrayCollection<T> array ) {
        this.array = array;
        this.pos = pos;
    }

    @Override
    public T get() throws NoSuchElementException{
        try{
            return array.get(pos);
        }catch( Exception e ){
            throw new NoSuchElementException("Invalid index .." + pos);
        }
    }
    
    public int getPos(){
        return pos;
    }

}
