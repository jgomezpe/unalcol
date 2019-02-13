package unalcol.real.array.sparse.metric;

import unalcol.math.metric.Simmilarity;
import unalcol.real.array.sparse.Vector;
import unalcol.real.array.sparse.DotProduct;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jgomez
 */
public class NormalizedCosineSimilarity implements Simmilarity<Vector> {
    protected DotProduct prod = new DotProduct();
    @Override
    public double apply(Vector x, Vector y) {
        return prod.apply(x, y);
    }
    
    public double max(Vector x){
        return 1.0;
    }
}
