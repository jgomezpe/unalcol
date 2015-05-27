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
    
    public SimplestGeneralizedPowerLawGenerator( RawGenerator g ){
        super(g);
    }
    
    @Override
    public double next(double x){
        return  super.next(x) - 1.0;
    }
    
    @Override
    public DoubleGenerator new_instance(){
        RawGenerator g = RawGenerator.get(this);
        return new SimplestGeneralizedPowerLawGenerator(g.new_instance());
    }        
    
}
