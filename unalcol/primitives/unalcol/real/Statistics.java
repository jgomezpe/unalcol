package unalcol.real;

/**
 * <p>Statistical information of numeric variables</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Statistics {
    /**
     * Index of the min value of the set
     */
    public int minIndex = 0;
    /**
     * Index of the max value of the set
     */
    public int maxIndex = 0;
    /**
     * Min value of the set
     */
    public double min = 0.0;
    /**
     * Max value of the set
     */
    public double max = 0.0;
    /**
     * Average value of the set
     */
    public double avg = 0.0;
    /**
     * Variance of the set
     */
    public double variance = 0.0;
    /**
     * Deviation of the set
     */
    public double deviation = 0.0;

    /**
     * Creates an empty statistical information
     */
    public Statistics() {
    }

    /**
     * Computes the statistical information of the given array of doubles
     * @param x Array to be statistically analized
     */
    public Statistics(double[] x) {
        int n = x.length;
        min = max = avg = x[0];
        for (int i = 1; i < n; i++) {
            if (x[i] < min) {
                min = x[i];
                minIndex = i;
            } else {
                if (x[i] > max) {
                    max = x[i];
                    maxIndex = i;
                }
            }
            avg += x[i];
        }
        avg /= n;
        for (int i = 0; i < n; i++) {
            variance += (x[i] - avg) * (x[i] - avg);
        }                
        variance /= (n>1)?(n - 1):1.0;
        deviation = Math.sqrt(variance);
    }

    /**
     * Computes the statistical information of the given column of a double matrix
     * @param x Matrix to be statistically analized
     * @param c column to be analized
     */
    public Statistics(double[][] x, int c) {
        int n = x.length;
        min = max = avg = x[0][c];
        for (int i = 1; i < n; i++) {
            if (x[i][c] < min) {
                min = x[i][c];
                minIndex = i;
            } else {
                if (x[i][c] > max) {
                    max = x[i][c];
                    maxIndex = i;
                }
            }
            avg += x[i][c];
        }
        avg /= n;
        for (int i = 0; i < n; i++) {
            variance += (x[i][c] - avg) * (x[i][c] - avg);
        }
        variance /= (n>1)?(n - 1):1.0;
        deviation = Math.sqrt(variance);
    }

    /**
     * Computes the statistical information of the given row of a double matrix
     * @param r Row to be analized
     * @param x Matrix to be statistically analized
     */
    public Statistics(int r, double[][] x ) {
        this( x[r] );
    }

    /**
     * Obtains the statistical information in an array of doubles format (min, max, average, variance, deviation)
     * @return Statistical information in an array of doubles format
     */
    public double[] get() {
        double[] values = new double[5];
        values[0] = min;
        values[1] = max;
        values[2] = avg;
        values[3] = variance;
        values[4] = deviation;
        return values;
    }
}
