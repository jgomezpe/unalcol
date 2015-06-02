/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.math.algebra;

import java.util.Iterator;

import unalcol.clone.Clone;
import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.array.MutableArrayCollection;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public abstract class InvertableScale<T> extends Scale<T>{
    public abstract T fastInverse( T x );
    @SuppressWarnings("unchecked")
	public T inverse( T x ){
        return fastInverse( (T)Clone.create(x) );
    }
    public Vector<T> inverse( ArrayCollection<T> a ){
        Vector<T> v = new Vector<T>();
        Iterator<T> iter = a.iterator();
        while( iter.hasNext() ){
            v.add(inverse(iter.next()));
        }
        return v;
    } 
    
    public void fastInverse( MutableArrayCollection<T> a ){
        for( int i=0; i<a.size(); i++ ){
            a.set(i, fastInverse(a.get(i)));
        }
    } 
    
}
