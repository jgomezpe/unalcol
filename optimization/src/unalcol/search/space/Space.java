/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.space;

import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public abstract class Space<T>{
    public abstract boolean feasible( T x );
    
    public abstract double feasibility( T x );
    
    public abstract T repair( T x );
    
    public abstract T get();
    
    public Vector<T> get( int n ){
        Vector<T> v = new Vector<T>();
        for( int i=0; i<n; i++ ){
            v.add(get());
        }
        return v;
    }

    public Vector<T> repair( Vector<T> pop ){
        Vector<T> v = new Vector<>();
        for( T x:pop ){
            v.add(repair(x));
        }
        return v;
    }

    public Vector<T> repair( T[] pop ){
        Vector<T> v = new Vector<T>();
        for( T x:pop ){
            v.add(repair(x));
        }
        return v;
    }           
}