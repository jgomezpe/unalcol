/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.bitarray;

import java.util.Iterator;
import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.array.ArrayCollectionIterator;

/**
 *
 * @author jgomez
 */
public class BitArrayCollection implements ArrayCollection<Boolean>{
   protected BitArray array;
   
   public BitArrayCollection( boolean[] bits ){
       array = new BitArray(bits);
   }

    @Override
    public Boolean get(int index) throws ArrayIndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        return array.dimension();
    }

    @Override
    public Iterator<Boolean> iterator() {
        return new ArrayCollectionIterator<>(0, this);
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }
}
