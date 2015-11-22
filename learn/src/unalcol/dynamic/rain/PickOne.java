/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.dynamic.rain;

import unalcol.random.integer.IntegerGenerator;
import unalcol.random.integer.UniformIntegerGenerator;
import unalcol.types.collection.array.ArrayCollection;

/**
 *
 * @author jgomez
 */
public abstract class PickOne<T> {
    protected IntegerGenerator g;
    
    public int pick( int i ){
        int k = g.next();
        if( k >= i){
            k++;
        }
        return k;
    }
    
    protected abstract int apply( int i, ArrayCollection<T> set);
    
    public void set(ArrayCollection<T> set){
        g = new UniformIntegerGenerator(set.size()-1);
    }
}
