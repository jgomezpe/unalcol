/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.real.mutation;

import unalcol.random.integer.IntUniform;

/**
 *
 * @author jgomez
 */
public class PickOne implements PickComponents{
    protected IntUniform g;
    protected int d=1;
    
    public PickOne(){
        g = new IntUniform(d);
    }
    
    @Override
    public int[] get(int DIMENSION) {
        if(d != DIMENSION){
            d = DIMENSION;
            g = new IntUniform(d);
        }
        return new int[]{g.next()};
    }    
}