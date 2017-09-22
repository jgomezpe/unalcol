package unalcol.data;

import java.util.Iterator;
import unalcol.types.collection.array.ArrayIterator;
import unalcol.types.collection.array.ImmutableArray;


/**
 * <p>Title: SampledArrayCollection</p>
 * <p>Description: A class representing a sampling process from a data source</p>
 * <p>Copyright:    Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class SampledArray<T> implements ImmutableArray<T>{

    /**
     * The source of objects to be sampled
     */
    protected ImmutableArray<T> source = null;
    /**
     * The subset of objects to be sampled (marked objects).
     * The sampling only uses the marked objects for generating the sample set
     * If there is no marked objects the sampling uses all the objects in the
     * source for generating the sample set
     */
    protected int[] index;

    /**
     * Constructor: Creates a sampling source
     * @param source Object source to be sampled
     * @param index Objects that are marked (used for generating the sample set)
     */
    public SampledArray(ImmutableArray<T> source, int[] index) {
        this.source = source;
        if (index != null) {
            this.index = index;
        } else {
            this.index = new int[0];
        }
    }
    
    public int[] sample( int[] sample_index ){
        int n = sample_index.length;
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = this.index[sample_index[i]];
        }
        return v;
    }
    
    public ImmutableArray<T> source(){
        return source;
    }
    
    /**
     * Gets the Object at an specific position
     * @param i The index of the object to be returned
     * @return The object at the given position, null if there is not object at that position
     */
    @Override
    public T get(int i) {
        T d = null;
        try {
            d = source.get(index[i]);
        } catch (Exception e) {
            System.err.println(
                    "ERROR[Invalid index value for sampling source..]");
        }
        return d;
    }

    /**
     * Return the number of objects in the object source
     * @return Number of data objects in the object source
     */
    @Override
    public int size(){
       return index.length;
    }
    
    @Override
    public boolean isEmpty(){
        return index.length == 0;
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayIterator<T>(0,this);
    }
}