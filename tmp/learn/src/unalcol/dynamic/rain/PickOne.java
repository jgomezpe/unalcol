/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.dynamic.rain;

import unalcol.random.integer.*;
import unalcol.types.collection.array.ArrayCollection;

/**
 *
 * @author jgomez
 */
public abstract class PickOne<T> {
    protected RandInt g;
    
    public int pick( int i ){
        int k = g.next();
        if( k >= i){
            k++;
        }
        return k;
    }
    
    protected abstract int apply( int i, ArrayCollection<T> set);
    
    public void set(ArrayCollection<T> set){
        g = new IntUniform(set.size()-1);
    }
}