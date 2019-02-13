/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.dynamic.rain;

import unalcol.clone.Clone;
import unalcol.dynamic.rain.interactionfunction.InteractionFunction;
import unalcol.types.real.array.MetricRealVectorSpace;
import unalcol.types.real.array.metrics.Euclidean;

/**
 *
 * @author jgomez
 */
public class EuclideanMove extends AttractionMove<double[]>{
    MetricRealVectorSpace space;
    
    public EuclideanMove(InteractionFunction strength, double EPSILON ){
        super( new Euclidean(), strength, EPSILON );
       space = new MetricRealVectorSpace(metric);
    }
    
    @Override
    public double[][] move(double[] x, double[] y, double r) {
        double[] z = space.minus(x, y);
        z = space.fastMultiply(z, r);
        x = space.fastMinus(x, z);
        y = space.fastPlus(y, z);
        return new double[][]{x,y};
    }
    
    @Override
    public EuclideanMove clone(){
        return new EuclideanMove((InteractionFunction)Clone.get(strength), EPSILON);
    }
    
}
