package unalcol.types.real.matrix;

import unalcol.types.real.*;

/**
 * <p>Title: DoubleMatrixUtil</p>
 * <p>Description: A set of constants and methods operating on a matrix of double numbers.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class DoubleMatrixUtil {
    /**
     * Returns the number of rows of the matrix
     * @param A Matrix to be analized
     * @return The number of rows of the matrix
     */
    public static int rows(double[][] A) {
        return A.length;
    }

    /**
     * Returns the number of columns of the matrix
     * @param A Matrix to be analized
     * @return The number of columns of the matrix
     */
    public static int columns(double[][] A) {
        return A[0].length;
    }

    /**
     * Returns the <i>i</i>-th column of the matrix
     * @param A Matrix from which the column will be obtained
     * @param i The column of the matrix to be returned
     * @return The <i>i</i>-th column of the matrix
     */
    public static double[] getColumn(double[][] A, int i) {
        return getColumn(A, rows(A), 0, i);
    }

    /**
     * Returns the <i>i</i>-th column of the matrix
     * @param A Matrix from which the column will be obtained
     * @param n Number of rows of the matrix
     * @param m Number of columns of the matrix
     * @param i The column of the matrix to be returned
     * @return The <i>i</i>-th column of the matrix
     */
    public static double[] getColumn(double[][] A, int n, int m, int i) {
        double[] x = new double[n];
        for (int j = 0; j < n; j++) {
            x[j] = A[j][i];
        }
        return x;
    }

    /**
     * Returns the <i>i</i>-th row of the matrix
     * @param A Matrix from which the row will be obtained
     * @param i The row of the matrix to be returned
     * @return The <i>i</i>-th row of the matrix
     */
    public static double[] getRow(double[][] A, int i) {
        return getRow(A, columns(A), 0, i);
    }

    /**
     * Returns the <i>i</i>-th row of the matrix
     * @param A Matrix from which the row will be obtained
     * @param n Number of rows of the matrix
     * @param m Number of columns of the matrix
     * @param i The row of the matrix to be returned
     * @return The <i>i</i>-th row of the matrix
     */
    public static double[] getRow(double[][] A, int n, int m, int i) {
        double[] x = new double[m];
        for (int j = 0; j < m; j++) {
            x[j] = A[i][j];
        }
        return x;
    }

    /**
     * Transpose the matrix (rows becomes columns, columns become rows)
     * @param A Matrix to be transposed
     */
    public static double[][] transpose(double[][] A) {
        return transpose(A, rows(A), columns(A));
    }

    /**
     * Transpose the matrix (rows becomes columns, columns become rows)
     * @param A Matrix to be transposed
     * @param n Number of rows of the matrix
     * @param m Number of columns of the matrix
     */
    public static double[][] transpose(double[][] A, int n, int m) {
        double[][] At = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                At[i][j] = A[j][i];
            }
        }
        return At;
    }

    /**
     * Calculates the sum of the elements in the given row
     * @param A Matrix to be analized
     * @param i The row index to be added
     * @return The sum of the elements in the given row. Return 0 if the index is not valid
     */
    public static double rowSum(double[][] A, int i) {
        return rowSum(A, rows(A), columns(A), i);
    }

    /**
     * Calculates the sum of the elements in the given row
     * @param A Matrix to be analized
     * @param n Number of rows of the matrix
     * @param m Number of columns of the matrix
     * @param i The row index to be added
     * @return The sum of the elements in the given row. Return 0 if the index is not valid
     */
    public static double rowSum(double[][] A, int n, int m, int i) {
        double val = 0.0;
        if (0 <= i && i < n) {
            for (int j = 0; j < m; j++) {
                val += A[i][j];
            }
        }
        return val;
    }

    /**
     * Calculates the sum of the elements in the given column
     * @param A Matrix to be analyzed
     * @param i The column index to be added
     * @return The sum of the elements in the given column. Return 0 if the index is not valid
     */
    public static double columnSum(double[][] A, int i) {
        return columnSum(A, rows(A), columns(A), i);
    }

    /**
     * Calculates the sum of the elements in the given column
     * @param A Matrix to be analyzed
     * @param n Number of rows of the matrix
     * @param m Number of columns of the matrix
     * @param j The column index to be added
     * @return The sum of the elements in the given column. Return 0 if the index is not valid
     */
    public static double columnSum(double[][] A, int n, int m, int j) {
        double val = 0.0;
        if (0 <= j && j < m) {
            for (int i = 0; i < n; i++) {
                val += A[i][j];
            }
        }
        return val;
    }

    /**
     * Calculates the sum of the elements in the matrix
     * @param A Matrix to be analized
     * @return The sum of the elements in the matrix
     */
    public static double sum(double[][] A) {
        return sum(A, rows(A), columns(A));
    }

    /**
     * Calculates the sum of the elements in the matrix
     * @param A Matrix to be analized
     * @param n Number of rows of the matrix
     * @param m Number of columns of the matrix
     * @return The sum of the elements in the matrix
     */
    public static double sum(double[][] A, int n, int m) {
        double val = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                val += A[i][j];
            }
        }
        return val;
    }

    /**
     * Calculates the sum of the elements in the principal diagonal, i.e.
     * sum( x[i,i] ) for all i=1..n
     * @param A Matrix to be analized
     * @return The sum of the elements in the principal diagonal
     */
    public static double diagonalSum(double[][] A) {
        return diagonalSum(A, rows(A), columns(A));
    }

    /**
     * Calculates the sum of the elements in the principal diagonal, i.e.
     * sum( x[i,i] ) for all i=1..n
     * @param A Matrix to be analized
     * @param n Number of rows of the matrix
     * @param m Number of columns of the matrix
     * @return The sum of the elements in the principal diagonal
     */
    public static double diagonalSum(double[][] A, int n, int m) {
        int x = Math.min(n, m);
        double val = 0.0;
        for (int i = 0; i < x; i++) {
            val += A[i][i];
        }
        return val;
    }

    /**
     * Determines if the matrix is squared or not
     * @param A Matrix to be analized
     * @return true if the matrix is squared false otherwise
     */
    public static boolean isSquare(double[][] A) {
        return (isSquare(A, rows(A), columns(A)));
    }

    /**
     * Determines if the matrix is squared or not
     * @param A Matrix to be analized
     * @param n Number of rows of the matrix
     * @param m Number of columns of the matrix
     * @return true if the matrix is squared false otherwise
     */
    public static boolean isSquare(double[][] A, int n, int m) {
        return (n == m);
    }

    /**
     * Determines if the matrix is symmetric
     * @param A Matrix to be analized
     * @return true if the matrix is symmetric, false otherwise
     */
    public static boolean isSymmetric(double[][] A) {
        return isSymmetric(A, rows(A), columns(A));
    }

    /**
     * Determines if the matrix is symmetric
     * @param A Matrix to be analized
     * @param n Number of rows of the matrix
     * @param m Number of columns of the matrix
     * @return true if the matrix is symmetric, false otherwise
     */
    public static boolean isSymmetric(double[][] A, int n, int m) {
        boolean flag = isSquare(A, n, m);
        if (flag) {
            for (int i = 0; flag && i < n - 1; i++) {
                for (int j = i + 1; flag && j < n; j++) {
                    flag = DoubleUtil.equal(A[i][j], A[j][i]);
                }
            }
        }
        return flag;
    }

    /**
     * Determines if the matrix is diagonal
     * @param A Matrix to be analized
     * @return true if the matrix is diagonal, false otherwise
     */
    public static boolean isDiagonal(double[][] A) {
        return isDiagonal(A, rows(A), columns(A));
    }

    /**
     * Determines if the matrix is diagonal
     * @param A Matrix to be analized
     * @param n Number of rows of the matrix
     * @param m Number of columns of the matrix
     * @return true if the matrix is diagonal, false otherwise
     */
    public static boolean isDiagonal(double[][] A, int n, int m) {
        boolean flag = isSquare(A, n, m);
        if (flag) {
            for (int i = 0; flag && i < n - 1; i++) {
                for (int j = i + 1; flag && j < n; j++) {
                    flag = (DoubleUtil.isZero(A[i][j]) &&
                            DoubleUtil.isZero(A[j][i]));
                }
            }
        }
        return flag;
    }

    /**
     * Obtains statistical (min, max, avg, standard deviation) information from a matrix by considering each column as
     * an independent variable
     * @param x Matrix to be statistically analyzed
     * @return statistical (min, max, avg, standard deviation) information from a matrix by considering each column as
     * an independent variable
     */
    public static Statistics[] statistics(double[][] x, boolean include_median) {
        int m = x[0].length;
        Statistics[] s = new Statistics[m];
        for (int j = 0; j < m; j++) {
            if( include_median ){
                s[j] = new StatisticsWithMedian(x, j);
            }else{
                s[j] = new Statistics(x, j);
            }
        }
        return s;
    }

    /**
     * Obtains statistical (min, max, avg, standard deviation) information from a matrix by considering each column as
     * an independent variable
     * @param x Matrix to be statistically analyzed
     * @return statistical (min, max, avg, standard deviation) information from a matrix by considering each column as
     * an independent variable
     */
    public static double[][] statistics_matrix(double[][] x, boolean include_median) {
        int m = x[0].length;
        double[][] s = new double[m][];
        Statistics[] st = statistics(x, include_median);
        for (int j = 0; j < m; j++) {
            s[j] = st[j].get();
        }
        return s;
    }

}
