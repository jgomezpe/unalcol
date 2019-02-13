/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.real.mutation;

import unalcol.integer.Uniform;

/**
 *
 * @author jgomez
 */
public class PickOne implements PickComponents{
    protected Uniform g;
    protected int d=1;
    
    public PickOne(){
        g = new Uniform(d);
    }
    
    @Override
    public int[] get(int DIMENSION) {
        if(d != DIMENSION){
            d = DIMENSION;
            g = new Uniform(d);
        }
        return new int[]{g.next()};
    }    
}