package unalcol.random.real;

import unalcol.services.MicroService;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 * <p>Uniform random number generator.</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */

public class StandardUniformGenerator extends MicroService<Double> implements InverseRandDouble {        
    /**
     * Returns a random double number
     * @return A random double number
     */
    @Override
    public Double next( double x ) {
        return x;
    }        
    
    /*@Override
    public DoubleGenerator new_instance(){
        RawGenerator g = RawGenerator.get(this);
        DoubleGenerator dg = new StandardUniformGenerator();
        RawGenerator.set(dg, g.new_instance());
        return dg; 
    }*/
}
