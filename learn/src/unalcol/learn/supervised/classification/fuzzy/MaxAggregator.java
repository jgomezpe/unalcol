/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.supervised.classification.fuzzy;

import unalcol.learn.Prediction;

/**
 *
 * @author jgomez
 */
public class MaxAggregator implements Aggregator{

    @Override
    public Prediction<Integer> apply(double[] confidence) {
        if( confidence.length == 0 )
            return new Prediction<Integer>(0,Double.MIN_VALUE);
        int m = 0;
        for( int i=1; i<confidence.length; i++ ){
            if( confidence[m] < confidence[i] ){
                m = i;
            }
        }
        return new Prediction<Integer>(m, confidence[m]);
    }
    
}
