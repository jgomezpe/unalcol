/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real.array;

/**
 *
 * @author jgomez
 */
public class DotProduct {
    public double apply(double[] x, double[] y){
        int n = (x.length<y.length)?x.length:y.length;
        double prod = 0.0;
        for(int i=0; i<n; i++){
            prod += x[i] * y[i];
        }
        return prod;
    }
    
    public double sqr_norm(double[] x){
        return apply(x,x);
    }
    
    public double norm( double[] x ){
        return Math.sqrt(sqr_norm(x));
    }
}
