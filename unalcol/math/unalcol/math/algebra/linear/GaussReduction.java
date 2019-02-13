package unalcol.math.algebra.linear;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class GaussReduction extends LinearSystemSolver{
    public double[] solve( double[][] A, double[] b ){
        int n = unalcol.real.matrix.Util.rows(A);
        int m = unalcol.real.matrix.Util.columns(A);
        b = (double[]) b.clone();
        double[] x = null;
        if (b.length == n) {
          A = A.clone();
          int[] perm = new int[m];
          for (int i = 0; i < m; i++) {
            perm[i] = i;
          }
          for (int i = 0; i < n; i++) {
            int k = i;
            while (k < m && Math.abs(A[i][k]) <= unalcol.real.Util.PRECISION) { k++; }
            if (k < m) {
              if (k != i) {
                int ti = perm[i];
                perm[i] = perm[k];
                perm[k] = ti;
                double t;
                for (int j = 0; j < n; j++) {
                  t = A[i][j];
                  A[i][j] = A[k][j];
                  A[k][j] = t;
                }
              }
              double pivot = A[i][i];
              for (int j = 0; j < m; j++) {
                A[i][j] /= pivot;
              }
              b[i] /= pivot;
              for (k = 0; k < n; k++) {
                if (k != i) {
                  pivot = A[k][i];
                  for (int j = 0; j < m; j++) {
                    A[k][j] -= pivot * A[i][j];
                  }
                  b[k] -= pivot * b[i];
                }
              }
            } else {
              if (Math.abs(b[i]) > unalcol.real.Util.PRECISION) {
                return null;
              }else{
                  b[i] = Double.POSITIVE_INFINITY;
              }
            }
          }
          x = new double[m];
          for( int i=0; i<m; i++ ){
              x[i] = Double.POSITIVE_INFINITY;
          }
          for( int i=0; i<Math.min(n,m); i++ ){
              x[perm[i]] = b[i];
          }
        }
        return x;
    }
}