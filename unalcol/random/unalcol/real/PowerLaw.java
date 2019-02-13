/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 *
 * @author jgomez
 */
public class PowerLaw extends StdPowerLaw{
    double x_min = 1.0;
    
    public PowerLaw( double _alpha, double _x_min ){
        super( _alpha );
        x_min = _x_min;
    }
    
    @Override
    public Double next(double x){
        return x_min*super.next(x);
    }    
    
    /*@Override
    public DoubleGenerator new_instance(){
        RawGenerator g = RawGenerator.get(this);
        DoubleGenerator dg = new PowerLawGenerator(1.0-1.0/coarse_alpha, x_min);
        RawGenerator.set(dg, g.new_instance());
        return dg; 
    }*/        
    
}