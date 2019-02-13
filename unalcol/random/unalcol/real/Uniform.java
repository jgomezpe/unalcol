package unalcol.real;

import unalcol.random.InverseGenerator;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 * <p>Simple uniform random number generator.</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */

public class Uniform extends InverseGenerator<Double> implements Rand {

    /**
     * The inferior range of the random number generator interval
     */
    protected double min = 0.0;

    /**
     * The length of the range
     */
    protected double length = 1.0;

    /**
     * Constructor: Creates a uniform random number generator that generates numbers in the interval [minVal, maxVal)
     * @param minVal Inf limit
     * @param maxVal Sup Limit
     */
    public Uniform(double minVal, double maxVal) {
        super();
        min = minVal;
        length = maxVal - minVal;
    }

    /**
     * Returns a random double number
     * @param x Inverse value (cumulative probability)
     * @return A random double number
     */
    @Override
    public Double next(double x) {
        return (min + length * x);
    }
    
  /*@Override
  public DoubleGenerator new_instance(){
    RawGenerator g = RawGenerator.get(this);
    DoubleGenerator dg = new UniformGenerator(min, length+min);
    RawGenerator.set(dg, g.new_instance());
    return dg;  
  }*/    
    
}
