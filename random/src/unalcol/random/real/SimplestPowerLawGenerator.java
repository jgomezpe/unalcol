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
public class SimplestPowerLawGenerator extends StandardPowerLawGenerator{
    // A standard power law  with alpha = 2 and b = 1
    public SimplestPowerLawGenerator(){    
        super(2.0);        
    }
    
    public SimplestPowerLawGenerator(RawGenerator g){            
        super(2.0, g);        
    }
    
    @Override
    public double next(double x){
        return 1.0/(1.0-x);
    } 

    @Override
    public DoubleGenerator new_instance(){
        RawGenerator g = RawGenerator.get(this);
        return new SimplestPowerLawGenerator(g.new_instance());
    }            
}