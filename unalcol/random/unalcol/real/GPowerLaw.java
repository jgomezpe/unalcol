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
public class GPowerLaw extends StdGPowerLaw{
    protected double c = 0.0;
    public GPowerLaw( double alpha, double b, double c ){
      super(alpha);
      coarse_b = (alpha-1.0)*b;
      this.c = c;
    }
    
    @Override
    public Double next(double x){
        return super.next(x) + c;
    }    

    /*
    @Override
    public DoubleGenerator new_instance(){
        double alpha = 1.0-1.0/coarse_alpha;
        double b = -coarse_b*coarse_alpha;
        RawGenerator g = RawGenerator.get(this);
        DoubleGenerator dg = new GeneralizedPowerLawGenerator(alpha, b, c);
        RawGenerator.set(dg, g.new_instance());
        return dg; 
    }        
    */
}


