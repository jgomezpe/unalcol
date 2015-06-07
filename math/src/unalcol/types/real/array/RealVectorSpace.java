package unalcol.types.real.array;

import unalcol.math.algebra.*;

/**
 * <p>Title: VectorAddition</p>
 * <p>Description: Adds and substracts vectors.</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 *
 */
public class RealVectorSpace implements VectorSpace<double[]> {
    @Override
    public double[] identity( double[] x ){
        return DoubleArray.create(x.length, 0.0);
    }

    @Override
    public double[] fastInverse( double[] x ){
        for( int i=0; i<x.length; i++ ){
            x[i] = -x[i];
        }
        return x;
    }

    @Override
    public double[] inverse( double[] x ){
        double[] y = new double[x.length];
        for( int i=0; i<x.length; i++ ){
            y[i] = -x[i];
        }
        return y;
    }

    /**
     * Adds the second vector to the first vector.
     * The addition process is component by component.
     * The result of the operation is stored in the first vector.
     * @param x The first vector
     * @param y The second vector
     */
    @Override
    public double[] fastPlus(double[] x, double[] y) {
        int n = x.length;
        for (int i = 0; i < n; i++) {
            x[i] += y[i];
        }
        return x;
    }

    /**
     * Substracts the ssecond vector from the first vector.
     * The substraction process is component by component.
     * The result of the operation is stored in the first vector.
     * @param x The first vector
     * @param y The second vector
     */
    @Override
    public double[] fastMinus(double[] x, double[] y) {
        int n = x.length;
        for (int i = 0; i < n; i++) {
            x[i] -= y[i];
        }
        return x;
    }

    @Override
    public double[] minus(double[] one, double[] two) {
        return fastMinus(one.clone(),two);
    }

    @Override
    public double[] plus(double[] one, double[] two) {
        return fastPlus(one.clone(),two);
    }

    @Override
    public double[] divide(double[] one, double x) {
        return fastDivide(one.clone(), x);
    }

    @Override
    public double[] fastDivide(double[] one, double x) {
        int n = one.length;
        for (int i = 0; i < n; i++) {
            one[i] /= x;
        }
        return one;
    }

    @Override
    public double[] fastMultiply(double[] one, double x) {
        int n = one.length;
        for (int i = 0; i < n; i++) {
            one[i] *= x;
        }
        return one;
    }

    @Override
    public double[] multiply(double[] one, double x) {
        return fastMultiply(one.clone(), x);
    }    
}
