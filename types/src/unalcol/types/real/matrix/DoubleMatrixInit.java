package unalcol.types.real.matrix;

/**
 * <p>Title: DoubleMatrixInit</p>
 * <p>Description:  A set of methods for creating a matrix of doubles</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class DoubleMatrixInit{
    /**
     * Creates the zero matrix of <i>n</i> rows by <i>m</i> columns
     * @param n Number of rows
     * @param m Number of columns
     * @return The zero matrix (<i>n</i> by <i>m</i>)
     */
    public static double[][] zero(int n, int m) {
        return new double[n][m];
        /*        double[][] data;
                if (n >= 0 && m >= 0) {
                    data = new double[n][m];
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            data[i][j] = 0.0;
                        }
                    }
                } else {
                    data = null;
                }
                return data;
         */
    }

    /**
     * Creates the zero square matrix (<i>n</i> by <i>n</i>)
     * @param n Number of rows and columns (dimension)
     * @return The zero matrix (<i>n</i> by <i>n</i>)
     */
    public static double[][] zero(int n) {
        return zero(n, n);
    }


    /**
     * Creates the identity matrix (<i>n</i> by <i>n</i>)
     * @param n Dimension of the identity matrix
     * @return The identity matrix (<i>n</i> by <i>n</i>)
     */
    public static double[][] identity(int n) {
        double[][] id = zero(n);
        for (int i = 0; i < n; i++) {
            id[i][i] = 1.0;
        }
        return id;
    }

    /**
     * Constructor: Create a new matrix with the same dimensions and components as the given matrix
     * @param A The source matrix
     */
    public static double[][] clone(double[][] A) {
        double[][] data = null;
        if (A != null) {
            data = (double[][]) A.clone();
        }
        return data;
    }

    /**
     * Creates a matrix from the given vector. The matrix will have dimension <i>n</i> b <i>1</i>
     * where element [i][0] will correspond with element x[i] of the vector
     * @param x Vector used for creating a matrix from it
     * @return The matrix version of the given vector (column vector)
     */
    public static double[][] vector(double[] x) {
        int n = x.length;
        int m = 1;
        double[][] data = new double[n][m];
        for (int i = 0; i < n; i++) {
            data[i][0] = x[i];
        }
        return data;
    }
}
