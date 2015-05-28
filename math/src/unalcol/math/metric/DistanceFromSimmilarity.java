/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.math.metric;

/**
 *
 * @author jgomez
 */
public class DistanceFromSimmilarity<T> implements Distance<T> {
    protected Simmilarity<T> sim;
    
    public DistanceFromSimmilarity( Simmilarity<T> sim ){
        this.sim = sim;
    }

    @Override
    public double apply(T x, T y) {
        return sim.max(x) - sim.apply(x, y);
    }
    
}
