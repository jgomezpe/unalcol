/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.math.algebra;

import java.util.Iterator;

import unalcol.clone.Cloneable;
import unalcol.types.collection.array.ImmutableArray;
import unalcol.types.collection.array.Array;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public interface InvertableScale<T> extends Scale<T>{
    public T fastInverse( T x );
    
    @SuppressWarnings("unchecked")
	default T inverse( T x ){ return fastInverse((T)Cloneable.cast(x).clone()); }
    
    default Vector<T> inverse( ImmutableArray<T> a ){
        Vector<T> v = new Vector<T>();
        Iterator<T> iter = a.iterator();
        while( iter.hasNext() ) v.add(inverse(iter.next()));
        return v;
    } 
    
    default Array<T> fastInverse( Array<T> a ){
        for( int i=0; i<a.size(); i++ ) a.set(i, fastInverse(a.get(i)));
        return a;
    }    
}