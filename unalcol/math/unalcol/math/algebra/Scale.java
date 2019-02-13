/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.math.algebra;

import java.util.Iterator;

import unalcol.clone.Cloneable;
import unalcol.collection.Array;
import unalcol.collection.Vector;
import unalcol.collection.array.Immutable;


/**
 *
 * @author jgomez
 */
public interface Scale<T> {
    public T fastApply( T x );
    
    @SuppressWarnings("unchecked")
	default T apply( T x ){ return fastApply((T)Cloneable.cast(x).clone()); }
    
    default Vector<T> apply( Immutable<T> a ){
        Vector<T> v = new Vector<T>();
        Iterator<T> iter = a.iterator();
        while( iter.hasNext() ) v.add(apply(iter.next()));
        return v;
    }
    
    default Array<T> fastApply( Array<T> a ){
    	try{ for( int i=0; i<a.size(); i++ ) a.set(i, fastApply(a.get(i))); }catch(Exception e){}
    	return a;
    }
}