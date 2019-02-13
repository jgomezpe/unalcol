/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real.array;

import unalcol.math.metric.MetricVectorSpace;
import unalcol.math.metric.QuasiMetric;

/**
 *
 * @author jgomez
 */
public class MetricSpace extends VectorSpace 
        implements MetricVectorSpace<double[]> {
    protected QuasiMetric<double[]> metric;
    public MetricSpace( QuasiMetric<double[]> metric ){
        this.metric = metric;
    }
    @Override
    public double apply(double[] x, double[] y) {
        return metric.apply(x, y);
    }    
}
