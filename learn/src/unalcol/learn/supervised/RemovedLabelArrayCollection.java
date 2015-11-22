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
    protected ArrayCollection<LabeledObject<T>> labeled;
    
    public RemovedLabelArrayCollection( ArrayCollection<LabeledObject<T>> labeled ){
        this.labeled = labeled;
    }
    
    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        return labeled.get(index).object();
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
