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
    
    public double[] get( int n ) {
        return DoubleArrayInit.random(n);
    }
    
    @Override
    public double[] get( Object... n) {
        return get((Double)n[0]);
    }

	@Override
	public Class<?> type() {
		return double[].class;
	}   
}