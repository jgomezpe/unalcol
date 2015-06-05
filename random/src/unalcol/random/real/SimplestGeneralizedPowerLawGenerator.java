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
public class SimplestGeneralizedPowerLawGenerator extends SimplestPowerLawGenerator{
    public SimplestGeneralizedPowerLawGenerator(){
        super();
    }
    
    @Override
    public double next(double x){
        return  super.next(x) - 1.0;
    }
    
    @Override
    public DoubleGenerator new_instance(){
        RawGenerator g = RawGenerator.get(this);
        DoubleGenerator dg = new SimplestGeneralizedPowerLawGenerator();
        RawGenerator.set(dg, g.new_instance());
        return dg; 
    }        
    
}
