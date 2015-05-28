package unalcol.types.real.array;

import unalcol.random.raw.RawGenerator;

/**
 * <p>Set of methods for creating an array of doubles</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */
public class DoubleArrayInit{
    /**
     * Generates a vector of doubles with two limits array.
     * @param min The inferior limit array
     * @param max The superior limit array
     * @return A vector of doubles
     */
    public static double[] random(double[] min, double[] max) {
        int n = min.length;
        double[] x = random(n);
        for (int i = 0; i < n; i++) {
            x[i] = min[i] + x[i] * (max[i] - min[i]);
        }
        return x;
    }

    /**
     * Generates a vector of doubles with size n.
     * @param n Size of the vector
     * @return A vector of doubles
     */
    public static double[] random(int n) {
        return RawGenerator.raw(double[].class, n);
    }

    /**
     * Creates a double array of size <i>n</i> with the same value in each compoment
     * @param n Size of the array to be created
     * @param value Value that will be copied in each position of the array
     * @return double[]
     */
    public static double[] create(int n, double value) {
    double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = value;
        }
        return x;
    }
}
