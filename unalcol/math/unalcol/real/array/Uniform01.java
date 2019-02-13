/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real.array;

import unalcol.random.raw.Generator;;

/**
 *
 * @author jgomez
 */
public class Uniform01 implements unalcol.instance.Instance {
	
    public double[] create( int n ){ return Generator.cast(this).raw(n); }
    
    public double[] create( double[] x ) { return create(x.length); }

	@Override
	public Object create(Object caller, Object... args) { return create((double[])caller); }
}