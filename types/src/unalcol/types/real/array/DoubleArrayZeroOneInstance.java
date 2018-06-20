/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array;

import unalcol.instance.Instance;
import unalcol.random.raw.JavaGenerator;
import unalcol.random.raw.RawGenerator;

/**
 *
 * @author jgomez
 */
public class DoubleArrayZeroOneInstance implements Instance {
	protected RawGenerator g;
	
	public DoubleArrayZeroOneInstance() { this(new JavaGenerator()); }
	
	public DoubleArrayZeroOneInstance( RawGenerator g ){ this.g = g; }

	public void setRaw( RawGenerator g ){ this.g = g; }
	
    public double[] create( int n) { return g.raw(n); }

	@Override
	public Object create(Object obj, Object... args){ return create((int)args[0]); }
	
	@Override
	public String toString(){ return "DoubleArrayZeroOneInstance"; }	
}