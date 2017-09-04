/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.math.algebra;

import java.util.Iterator;

import unalcol.clone.Clone;
import unalcol.services.Service;
import unalcol.types.collection.array.Array;
import unalcol.types.collection.array.MutableArray;
import unalcol.types.collection.vector.Vector;


/**
 *
 * @author jgomez
 */
public abstract class Scale<T> {
    public abstract T fastApply( T x );
    @SuppressWarnings("unchecked")
	public T apply( T x ){ try{ return apply( (T)Service.run(Clone.name,x)); }catch(Exception e){ return apply(x); } }
    
    public Vector<T> apply( Array<T> a ){
        Vector<T> v = new Vector<T>();
        Iterator<T> iter = a.iterator();
        while( iter.hasNext() ){
            v.add(apply(iter.next()));
        }
        return v;
    }
    
    public void fastApply( MutableArray<T> a ){
        for( int i=0; i<a.size(); i++ ){
            a.set(i, fastApply(a.get(i)));
        }
    }
}