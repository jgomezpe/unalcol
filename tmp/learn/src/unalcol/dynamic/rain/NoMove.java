/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.dynamic.rain;

import unalcol.dynamic.rain.interactionfunction.InteractionFunction;
import unalcol.math.metric.Distance;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public class NoMove<T> implements RainMove<T>{
    protected Distance<T> metric;
    protected double EPSILON;
    
    public NoMove( Distance<T> metric, double EPSILON ){
        this.EPSILON = EPSILON;
        this.metric = metric;
    }
    

    @Override
    public boolean apply(Vector<T> set, int i, int j) {
        T x = set.get(i);
        T y = set.get(j);
        return metric.apply(x, y) < EPSILON; 
    }

    @Override
    public void update() {
    }
    
}
