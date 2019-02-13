/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real.array.sparse;

import unalcol.math.metric.QuasiMetric;

/**
 *
 * @author jgomez
 */
public class MetricSpace  extends VectorSpace 
        implements unalcol.math.metric.MetricVectorSpace<Vector> {
    
    protected QuasiMetric<Vector> metric;
    
    public MetricSpace( QuasiMetric<Vector> metric ){
        this.metric = metric;
    }
    
    @Override
    public double apply(Vector x, Vector y) {
        return metric.apply(x, y);
    } 
    
}
