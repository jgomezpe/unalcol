package unalcol.random.real;

import unalcol.random.raw.RawGenerator;
import unalcol.random.raw.UsesRawGenerator;

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

public class StandardGaussianGenerator extends UsesRawGenerator implements RandDouble{
	public StandardGaussianGenerator() { super(); }

	public StandardGaussianGenerator( RawGenerator raw_g ){ super( raw_g ); }
    
    /**
     * Returns a random double number following the standard Gaussian distribution
     * @param x Inverse value (cumulative probability)
     * @return A random double number
     */
    @Override
    public Double next() {
        double x,y;
        double r;
        do {
            x = 2.0 * raw().next() - 1.0;
            y = 2.0 * raw().next() - 1.0;
            r = x * x + y * y;
        } while (r >= 1.0);

        double z = Math.sqrt( -2.0 * Math.log(r) / r);
        return (y * z);
    }
}