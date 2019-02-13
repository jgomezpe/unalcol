/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.real;

import unalcol.search.space.Repair;
import unalcol.search.space.Space;

/**
 *
 * @author jgomez
 */
public class HyperCubeRepair implements Repair<double[]> {

    public double[] apply(HyperCube space, double[] x) {
        x = x.clone();
        for( int i=0; i<x.length; i++ ){
            if( x[i] < space.min[i] ){
                x[i] = space.min[i];
            }else{
                if( x[i] > space.max[i] ){
                     x[i] = space.max[i];
                }
            }
        }
        return x;        
    }

    @Override
    public double[] apply(Space<double[]> space, double[] x) {
        return apply((HyperCube)space, x);
    }
    
}
