/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.random.real;

import unalcol.random.InverseGenerator;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 *
 * @author jgomez
 */
public class StandardPowerLawGenerator  extends InverseGenerator<Double> implements RandDouble{
    double coarse_alpha = -1.0;
    
    public StandardPowerLawGenerator(){ super(); }
    
    public StandardPowerLawGenerator( double alpha ){ coarse_alpha = 1.0/(1.0-alpha); }
        
    @Override
    public Double next(double x){
        return Math.pow(1.0-x, coarse_alpha);
    }    
}
