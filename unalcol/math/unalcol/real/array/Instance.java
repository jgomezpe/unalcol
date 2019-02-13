/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real.array;

/**
 *
 * @author jgomez
 */
public class Instance extends Uniform01 {
    protected LinealScale scale;
    public Instance(  double[] min, double[] max  ){
        super();
        scale = new LinealScale(min, max);
    }
    
    @Override
    public double[] create(int n){ return scale.inverse(super.create(n)); }
}