/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.supervised;

import java.util.Iterator;
import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.array.ArrayCollectionIterator;

/**
 *
 * @author jgomez
 */
public class RemovedLabelArrayCollection<S,T> implements ArrayCollection<S>{
    protected ArrayCollection<InputOutputPair<S,T>> labeled;
    
    public RemovedLabelArrayCollection( ArrayCollection<InputOutputPair<S,T>> labeled ){
        this.labeled = labeled;
    }
    
    @Override
    public S get(int index) throws ArrayIndexOutOfBoundsException {
        return labeled.get(index).input();
    }

    @Override
    public int size() {
        return labeled.size();
    }

    @Override
    public Iterator<S> iterator() {
        return new ArrayCollectionIterator<S>(0,this);
    }

    @Override
    public boolean isEmpty() {
        return labeled.isEmpty();
    }
}