/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.real;

import unalcol.search.space.Space;
import unalcol.search.space.SpaceSampler;

/**
 *
 * @author Jonatan
 */
public class HyperCubeRandomSampler extends SpaceSampler<double[]> {
    @Override
    public double[] apply(Space<double[]> space) {
        if( space instanceof HyperCube ){
        	return apply( (HyperCube)space );
        }
        return null;
    }
    
 
    public double[] apply(HyperCube space) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
