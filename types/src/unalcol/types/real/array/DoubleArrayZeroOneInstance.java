/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array;

import unalcol.instance.Instance;
import unalcol.services.TaggedCallerNamePair;
import unalcol.types.tag.Tags;

/**
 *
 * @author jgomez
 */
public class DoubleArrayZeroOneInstance extends Tags implements TaggedCallerNamePair<Class<double[]>>, Instance<double[]> {
    public double[] create( int n ){  return DoubleArray.random(n); }
    
    @Override
    public double[] create( Object... n) { return create((int)n[0]); }
}