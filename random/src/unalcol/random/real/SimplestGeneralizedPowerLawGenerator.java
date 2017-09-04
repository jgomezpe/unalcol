/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.random.real;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 *
 * @author jgomez
 */
public class SimplestGeneralizedPowerLawGenerator extends SimplestPowerLawGenerator{
    public SimplestGeneralizedPowerLawGenerator(){
        super();
    }
    
    @Override
    public Double next(double x){
        return  super.next(x) - 1.0;
    }
    
    /*@Override
    public DoubleGenerator new_instance(){
        RawGenerator g = RawGenerator.get(this);
        DoubleGenerator dg = new SimplestGeneralizedPowerLawGenerator();
        RawGenerator.set(dg, g.new_instance());
        return dg; 
    }*/           
}