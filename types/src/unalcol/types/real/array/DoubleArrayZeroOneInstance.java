/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array;

import unalcol.instance.Instance;

/**
 *
 * @author jgomez
 */
public class DoubleArrayZeroOneInstance extends Instance<double[]> {
    public DoubleArrayZeroOneInstance(){
    }
    
    public double[] create( int n ) {
        return DoubleArray.random(n);
    }
    
    @Override
    public double[] create( Object... n) {
        return create((int)n[0]);
    }
}