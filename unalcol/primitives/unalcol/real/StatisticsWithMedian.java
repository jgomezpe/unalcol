/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real;

/**
 *
 * @author jgomez
 */
public class StatisticsWithMedian extends Statistics{
    public double median;
    /**
     * Creates an empty statistical information
     */
    public StatisticsWithMedian() {
    }

    /**
     * Computes the statistical information of the given array of doubles
     * @param x Array to be statistically analyzed
     */
    public StatisticsWithMedian(double[] x) {
        super( x );
        compute_median(x.clone());
    }

    /**
     * Computes the statistical information of the given column of a double matrix
     * @param x Matrix to be statistically analyzed
     * @param c column to be analyzed
     */
    public StatisticsWithMedian(double[][] x, int c) {
        super( x, c );
        double[] y = new double[x.length];
        for( int i=0; i<y.length; i++ ){
            y[i] = x[i][c];
        }
        compute_median(y);
    }

    /**
     * Computes the statistical information of the given row of a double matrix
     * @param r Row to be analyzed
     * @param x Matrix to be statistically analyzed
     */
    public StatisticsWithMedian(int r, double[][] x ) {
        super( r, x );
        compute_median( x[r].clone() );
    }
    
    private void compute_median( double[] x){
        Array.merge(x);  
        int n = x.length;
        median = ((n%2)==0)?(x[n/2]+x[n/2-1])/2.0:x[n/2];
    }

    /**
     * Obtains the statistical information in an array of doubles format (min, max, average, variance, deviation)
     * @return Statistical information in an array of doubles format
     */
    @Override
    public double[] get() {
        double[] values = new double[6];
        values[0] = min;
        values[1] = max;
        values[2] = median;
        values[3] = avg;
        values[4] = variance;
        values[5] = deviation;
        return values;
    }    
}
