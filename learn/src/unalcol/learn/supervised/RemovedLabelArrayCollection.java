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
public class RemovedLabelArrayCollection<T> implements ArrayCollection<T>{
    protected ArrayCollection<InputOutputPair<T,Object>> labeled;
    
    public RemovedLabelArrayCollection( ArrayCollection<InputOutputPair<T,Object>> labeled ){
        this.labeled = labeled;
    }
    
    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        return labeled.get(index).input();
    }

    @Override
    public int size() {
        return labeled.size();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayCollectionIterator<>(0,this);
    }

    @Override
    public boolean isEmpty() {
        return labeled.isEmpty();
    }
}