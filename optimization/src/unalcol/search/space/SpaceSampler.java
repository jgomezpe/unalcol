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
public abstract class SpaceSampler<T> {
    public abstract T apply( Space<T> space );
    public Vector<T> apply( Space<T> space, int n ){
        Vector<T> v = new Vector<T>();
        for( int i=0; i<n; i++ ){
            v.add(apply(space));
        }
        return v;
    }
}
