/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.unsupervised.partition;

import unalcol.learn.Aggregator;
import unalcol.math.algebra.ScalarProduct;
import unalcol.math.metric.QuasiMetric;
import unalcol.types.real.array.sparse.SparseRealVector;
import unalcol.types.real.array.sparse.SparseRealVectorDotProduct;
import unalcol.types.real.array.sparse.SparseRealVectorSpace;

/**
 *
 * @author jgomez
 */
public class SphereSparseCentroidsRecognizer extends CentroidsRecognizer<SparseRealVector> {
    protected SparseRealVectorDotProduct product = new SparseRealVectorDotProduct();
    protected ScalarProduct<SparseRealVector> scale = new SparseRealVectorSpace();
    private void normalize(){
        for( int i=0; i<mu.length; i++ ){
            mu[i] = scale.fastDivide(mu[i], product.norm(mu[i]));
        }
    }
    
    public SphereSparseCentroidsRecognizer( SparseRealVector[] mu, QuasiMetric<SparseRealVector> metric, Aggregator aggregator ){
        super(mu, metric, aggregator);
        normalize();
    }

    public SphereSparseCentroidsRecognizer( SparseRealVector[] mu, QuasiMetric<SparseRealVector> metric, boolean simmilarity ){
        super(mu,metric,simmilarity);
        normalize();
    }
    
    @Override
    public void setMu( int i, SparseRealVector mu ){
        this.mu[i] = mu;
        this.mu[i] = scale.fastDivide(this.mu[i], product.norm(this.mu[i]));
    }    
}
