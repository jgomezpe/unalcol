package math;

import unalcol.math.algebra.linear.GaussReduction;

public class GaussReductionDemo {
    public static void main( String[] args ){
        double[][] A = new double[3][3];
        A[0][0] = 1.0;
        A[0][1] = 3.0;
        A[0][2] = 5.0;
        A[1][0] = 3.0;
        A[1][1] = 3.0;
        A[1][2] = 4.0;
        A[2][0] = 1.0;
        A[2][1] = 3.0;
        A[2][2] = 5.0;
        double[] b = new double[]{ 1.0, 2.0, 1.0};
        GaussReduction gauss = new GaussReduction();
        double[] x = gauss.solve(A,b);
        for( int i=0; i<3; i++ ){
            System.out.print(" " + x[i]);
        }
    }
}
