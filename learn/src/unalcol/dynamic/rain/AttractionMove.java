/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.dynamic.rain;

import unalcol.clone.Clone;
import unalcol.dynamic.rain.interactionfunction.InteractionFunction;
import unalcol.math.metric.Distance;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public abstract class AttractionMove<T> implements RainMove<T> {
    protected InteractionFunction strength;
    protected Distance<T> metric;
    protected double EPSILON;
    
    public AttractionMove( Distance<T> metric, 
            InteractionFunction strength, double EPSILON ){
        this.EPSILON = EPSILON;
        this.strength = strength;
        this.metric = metric;
    }
    
    public abstract T[] move( T x, T y, double r );
    
    public void setIteraction( InteractionFunction strength ){
        this.strength = strength;
    }
    
    @Override
    public boolean apply(Vector<T> set, int i, int j) {
        T x = set.get(i);
        T y = set.get(j);
        double d = metric.apply(x, y);
        double r = strength.evaluate(d);
        T[] z = move( x, y, r );
        x = z[0];
        y = z[1];
        set.set(i, x);
        set.set(j, y);
        return metric.apply(x, y) < EPSILON; 
    }

    @Override
    public void update() {
        strength.update();
    }
    
}
