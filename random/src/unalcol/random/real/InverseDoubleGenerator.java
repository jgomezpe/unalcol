package unalcol.random.real;

import unalcol.random.raw.RawGenerator;

/**
 * <p>Creates a double number generator from a uniform number generator (using the inverse notion)</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public abstract class InverseDoubleGenerator extends DoubleGenerator {
     
    public InverseDoubleGenerator(){}
    
    /**
     * Returns a random double number
     * @param x Inverse value (cumulative probability)
     * @return A random double number
     */
    public abstract double next(double x);

    /**
     * Returns a random double number
     * @return A random double number
     */
    @Override
    public double generate() {
        RawGenerator g = RawGenerator.get(this);
        return next(g.next());
    }
}
