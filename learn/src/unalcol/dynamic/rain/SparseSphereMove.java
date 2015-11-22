/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.dynamic.rain;

import unalcol.clone.Clone;
import unalcol.dynamic.rain.interactionfunction.InteractionFunction;
import unalcol.types.real.array.sparse.MetricSparseRealVectorSpace;
import unalcol.types.real.array.sparse.SparseEuclidean;
import unalcol.types.real.array.sparse.SparseRealVector;
import unalcol.types.real.array.sparse.SparseRealVectorDotProduct;

/**
 *
 * @author jgomez
 */
public class SparseSphereMove extends AttractionMove<SparseRealVector>{
    MetricSparseRealVectorSpace space;
    SparseRealVectorDotProduct norm = new SparseRealVectorDotProduct();
    
    public SparseSphereMove(InteractionFunction strength, double EPSILON ){
        super( new SparseEuclidean(), strength, EPSILON );
       space = new MetricSparseRealVectorSpace(metric);
    }
    
    @Override
    public SparseRealVector[] move(SparseRealVector x, SparseRealVector y, double r) {
        return new SparseRealVector[]{x,y};
        /*
        double epsilon = 0.001;
        if( 0.05<r && r<0.5 ){
            SparseRealVector z = space.minus(x, y);
            z = space.fastMultiply(z, r);
            z.removeZeroes(epsilon);
            x = space.fastMinus(x, z);
            x.removeZeroes(epsilon);            
            x = space.fastDivide(x, norm.norm(x));
            y = space.fastPlus(y, z);
            y.removeZeroes(epsilon);            
            y = space.fastDivide(y, norm.norm(y));
        }else{
            if( r >= 0.5 ){
                SparseRealVector z = space.plus(x, y);
                z.removeZeroes(epsilon);
                z = space.fastDivide(z, norm.norm(z));
                x = z;
                y = (SparseRealVector)Clone.get(z);
            }    
        }    
        return new SparseRealVector[]{x,y};
        * 
        */
    }
    
    @Override
    public SparseSphereMove clone(){
        return new SparseSphereMove((InteractionFunction)Clone.get(strength), EPSILON);
    }    
}
