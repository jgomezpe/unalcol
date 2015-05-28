/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array;

import unalcol.instance.*;

/**
 *
 * @author jgomez
 */
public class DoubleArrayZeroOneInstance extends Instance<double[]> {
    public DoubleArrayZeroOneInstance(){
    }
    
    @Override
    public double[] get(double[] x) {
        return DoubleArrayInit.random(x.length);
    }

    @Override
    public Object owner() {
        return double[].class;
    }    
}
