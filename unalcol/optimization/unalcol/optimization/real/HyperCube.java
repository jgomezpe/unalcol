/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.real;

import unalcol.search.space.Space;
import unalcol.real.Array;
import unalcol.real.array.Instance;

/**
 *
 * @author jgomez
 */
public class HyperCube implements Space<double[]> {
    protected Instance instance;
    protected double[] min;
    protected double[] max;
    
    public HyperCube( int n ){
        this(Array.create(n, 0.0), Array.create(n, 1.0));
    }

    public HyperCube( int n, double min, double max ){
        this(Array.create(n, min),Array.create(n, max));
    }

    public HyperCube( double[] min, double[] max ){
        instance = new Instance(min, max);
        this.min = min;
        this.max = max;
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
    
    @Override
    public double[] pick(){
    	return instance.create(min.length);
    }
    
    public double[] repair(double[] x) {
        x = x.clone();
        for( int i=0; i<x.length; i++ ){
            if( x[i] < min[i] ){
                x[i] = min[i];
            }else{
                if( x[i] > max[i] ){
                     x[i] = max[i];
                }
            }
        }
        return x;        
    }
    
    public double[] min(){
    	return min;
    }

    public double[] max(){
    	return max;
    }    
}