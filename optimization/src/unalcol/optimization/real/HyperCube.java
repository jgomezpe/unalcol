/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.real;

import unalcol.search.space.Space;
import unalcol.types.real.array.DoubleArrayInit;

/**
 *
 * @author jgomez
 */
public class HyperCube extends Space<double[]> {
    protected double[] min;
    protected double[] max;
    
    public HyperCube( int n ){
        this(DoubleArrayInit.create(n, 0.0), DoubleArrayInit.create(n, 1.0));
    }

    public HyperCube( int n, double min, double max ){
        this(DoubleArrayInit.create(n, min),DoubleArrayInit.create(n, max));
    }

    public HyperCube( double[] min, double[] max ){
        this.max = max;
        this.min = min;
    }
    
    @Override
    public boolean feasible( double[] x ){ 
        int i=0;
        while(i<x.length && min[i] <= x[i] && x[i] <= max[i]){ i++; }
        return (i==x.length); 
    }
    
    @Override
    public double feasibility( double[] x ){ 
        double d = 0.0;
        for( int i=0; i<x.length; i++ ){
            if( x[i] < min[i] ){
                d += x[i] - min[i];
            }else{
                if( x[i] > max[i] ){
                    d += max[i] - x[i];
                }
            }
        }
        return d==0.0?1.0:d; 
    }    
}