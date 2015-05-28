/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search;

import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public abstract class Goal<T> {
    public abstract boolean test( T x );
    public abstract double quality( T x );
    public abstract boolean nonStationary();
    public abstract boolean qTest( double q );    
    
    public double[] quality( Vector<T> x ){
        double[] q = new double[x.size()];
        int k=0;
        for( T y : x ){
            q[k] = quality(y);
            k++;
        }
        return q;
    }
}
