/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn;

/**
 *
 * @author jgomez
 */
public class MinAggregator implements Aggregator{

    @Override
    public Prediction apply(double[] confidence) {
        if( confidence.length == 0 )
            return new Prediction(0,Double.MAX_VALUE);
        int m = 0;
        for( int i=1; i<confidence.length; i++ ){
            if( confidence[m] > confidence[i] ){
                m = i;
            }
        }
        return new Prediction(m, confidence[m]);
    }
}
