/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array;

/**
 *
 * @author jgomez
 */
public class DoubleArrayInstance extends DoubleArrayZeroOneInstance {
    protected RealVectorLinealScale scale;
    public DoubleArrayInstance(  double[] min, double[] max  ){
        super();
        scale = new RealVectorLinealScale(min, max);
    }
    
    @Override
    public double[] create(int n){ return scale.inverse(super.create(n)); }
}