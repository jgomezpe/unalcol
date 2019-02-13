/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.fuzzy;

import unalcol.learn.Prediction;
import unalcol.learn.SimplePrediction;

/**
 *
 * @author jgomez
 */
public class MinAggregator implements Aggregator{
    @Override
    public Prediction<Integer> apply(double[] confidence) {
        if( confidence.length == 0 )
            return new SimplePrediction<Integer>(0,Double.MAX_VALUE);
        int m = 0;
        for( int i=1; i<confidence.length; i++ ){
            if( confidence[m] > confidence[i] ){
                m = i;
            }
        }
        return new SimplePrediction<Integer>(m, confidence[m]);
    }
}