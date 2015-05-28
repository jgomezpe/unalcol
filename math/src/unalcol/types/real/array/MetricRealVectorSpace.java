/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array;

import unalcol.math.metric.MetricVectorSpace;
import unalcol.math.metric.QuasiMetric;

/**
 *
 * @author jgomez
 */
public class MetricRealVectorSpace extends RealVectorSpace 
        implements MetricVectorSpace<double[]> {
    protected QuasiMetric<double[]> metric;
    public MetricRealVectorSpace( QuasiMetric<double[]> metric ){
        this.metric = metric;
    }
    @Override
    public double apply(double[] x, double[] y) {
        return metric.apply(x, y);
    }    
}
