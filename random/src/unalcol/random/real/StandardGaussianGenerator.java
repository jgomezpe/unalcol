package unalcol.random.real;

import unalcol.random.raw.RawGenerator;
import unalcol.random.raw.RawGeneratorWrapper;
import unalcol.services.AbstractMicroService;
import unalcol.services.MicroService;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 * <p>Gaussian random number generator.</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class StandardGaussianGenerator extends MicroService<Double> implements RandDouble{
    
	public AbstractMicroService<?> wrap( String id ){
		if(id.equals(RawGenerator.name)){ return new RawGeneratorWrapper(); }
		
		return null;
	}
	

    /**
     * Returns a random double number following the standard Gaussian distribution
     * @param x Inverse value (cumulative probability)
     * @return A random double number
     */
    @Override
    public Double next() {
    	RawGenerator g = (RawGenerator)getMicroService(RawGenerator.name);
        double x,y;
        double r;
        do {
            x = 2.0 * g.next() - 1.0;
            y = 2.0 * g.next() - 1.0;
            r = x * x + y * y;
        } while (r >= 1.0);

        double z = Math.sqrt( -2.0 * Math.log(r) / r);
        return (y * z);
    }

    /*@Override
    public DoubleGenerator new_instance(){
        RawGenerator g = RawGenerator.get(this);
        DoubleGenerator dg = new StandardGaussianGenerator();
        RawGenerator.set(dg, g.new_instance());
        return dg;
    }*/
}