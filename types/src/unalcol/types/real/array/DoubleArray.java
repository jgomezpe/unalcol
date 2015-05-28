package unalcol.types.real.array;
import unalcol.types.real.*;

/**
 * <p>Set of constants and methods operating on an array of the primitive double data type</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */
public class DoubleArray{
    /**
     * Reverses the given array
     * @param a Double array to be reversed
     */
    public static void invert(double[] a) {
        int n = a.length;
        int j = n - 1;
        for (int i = 0; i < j; i++) {
            double tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            j--;
        }
    }

    /**
     * Normalizes the array to the interval [0,1] using the sum of the values in the array as the maximum value
     * (precondition: Values in the array should be non negatives and at least one value should be different of zero
     * @param x Array to be normalized
     */
    public static void normalize( double[] x ){
    int n = x.length;
        double sum = 0.0;
        for( int i=0; i<n; i++ ){
            sum +=  x[i];
        }
        if( !DoubleUtil.isZero(sum) ){
            for (int i = 0; i < n; i++) {
                x[i] /= sum;
            }
        }
    }
    
    

    /**
     * Obtains statistical information of the given array considering the array values as samples drawn from a population
     * @param x double[] Samples drawn from a population
     * @return Statistics information of the given data samples
     */
    public static Statistics statistics( double[] x ){
    return (new Statistics(x));
    }

    /**
     * Obtains statistical information (in array format) of the given array considering the array values as samples drawn from a population
     * @param x double[] Samples drawn from a population
     * @return Statistics information (in array format) of the given data samples
     */
    public static double[] statistics_vector( double[] x ){
        return statistics(x).get();
    }
}
