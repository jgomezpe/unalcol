/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.real.array.sparse;

import unalcol.math.metric.MetricVectorSpace;
import unalcol.math.metric.QuasiMetric;

/**
 *
 * @author jgomez
 */
public class MetricSparseRealVectorSpace  extends SparseRealVectorSpace 
        implements MetricVectorSpace<SparseRealVector> {
    
    protected QuasiMetric<SparseRealVector> metric;
    
    public MetricSparseRealVectorSpace( QuasiMetric<SparseRealVector> metric ){
        this.metric = metric;
    }
    
    @Override
    public double apply(SparseRealVector x, SparseRealVector y) {
        return metric.apply(x, y);
    } 
    
}
