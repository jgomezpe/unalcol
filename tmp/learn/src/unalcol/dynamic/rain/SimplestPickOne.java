/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.dynamic.rain;

import unalcol.types.collection.array.ArrayCollection;

/**
 *
 * @author jgomez
 */
public class SimplestPickOne extends PickOne {
    int N = 0;
    
    public SimplestPickOne(){
        N = 0;
    }

    public SimplestPickOne( int N ){
        this.N = N;
    }
    
    @Override
    protected int apply(int i, ArrayCollection set) {
        if( N != set.size()){
            set(set);
        }
        return pick(i);
    }
    
    @Override
    public SimplestPickOne clone(){
        return new SimplestPickOne(N);
    }
    
}
