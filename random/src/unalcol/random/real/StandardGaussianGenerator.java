package unalcol.random.real;

import unalcol.random.raw.RawGenerator;

/**
 * <p>Gaussian random number generator.</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class StandardGaussianGenerator extends DoubleGenerator{
    
    /**
     * Creates a standard Gaussian number generator
     */
    public StandardGaussianGenerator(){}

    /**
     * Returns a random double number following the standard Gaussian distribution
     * @param x Inverse value (cumulative probability)
     * @return A random double number
     */
    @Override
    public double generate() {
        RawGenerator g = RawGenerator.get(this);
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
    
    @Override
    public DoubleGenerator new_instance(){
        RawGenerator g = RawGenerator.get(this);
        DoubleGenerator dg = new StandardGaussianGenerator();
        RawGenerator.set(dg, g.new_instance());
        return dg;
    }    
}