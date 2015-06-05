/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.random.real;

import unalcol.random.raw.RawGenerator;

/**
 *
 * @author jgomez
 */
public class StandardPowerLawGenerator  extends InverseDoubleGenerator{
    double coarse_alpha = -1.0;
    
    public StandardPowerLawGenerator(){
        super();
    }
    
    public StandardPowerLawGenerator( double alpha ){
        coarse_alpha = 1.0/(1.0-alpha);
    }
        
    @Override
    public double next(double x){
        return Math.pow(1.0-x, coarse_alpha);
    }
    
    @Override
    public DoubleGenerator new_instance(){
        RawGenerator g = RawGenerator.get(this);
        DoubleGenerator dg = new StandardPowerLawGenerator(1.0-1.0/coarse_alpha);
        RawGenerator.set(dg, g.new_instance());
        return dg;
    }        
}
