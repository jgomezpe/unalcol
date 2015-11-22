/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.unsupervised.partition;

import unalcol.math.metric.Distance;
import unalcol.math.metric.DistanceFromSimmilarity;
import unalcol.math.metric.Simmilarity;

/**
 *
 * @author jgomez
 */
public class CentroidsRecognizerDistance<T> implements Distance<CentroidsRecognizer<T>> {

    @Override
    public double apply(CentroidsRecognizer<T> x, CentroidsRecognizer<T> y) {
        Distance<T> metric;
        if( x.metric() instanceof Simmilarity ){
            metric = new DistanceFromSimmilarity((Simmilarity)x.metric());
        }else{
            metric = (Distance<T>)x.metric();
        }
        double d = 0.0;
        for( int i=0; i<x.classesNumber(); i++ ){
            d += metric.apply(x.mu(i), y.mu(i));
        }
        return d;
    }    
}