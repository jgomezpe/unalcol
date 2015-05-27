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
public class GeneralizedPowerLawGenerator extends StandardGeneralizedPowerLawGenerator{
    protected double c = 0.0;
    public GeneralizedPowerLawGenerator( double alpha, double b, double c ){
      super(alpha);
      coarse_b = (alpha-1.0)*b;
      this.c = c;
    }
    
    public GeneralizedPowerLawGenerator( double alpha, double b, double c, RawGenerator g ){
      super(alpha, g);
      coarse_b = (alpha-1.0)*b;
      this.c = c;
    }
    
    @Override
    public double next(double x){
        return super.next(x) + c;
    }    

    @Override
    public DoubleGenerator new_instance(){
        double alpha = 1.0-1.0/coarse_alpha;
        double b = -coarse_b*coarse_alpha;
        RawGenerator g = RawGenerator.get(this);
        return new GeneralizedPowerLawGenerator(alpha, b, c, g.new_instance());
    }        
    
}


