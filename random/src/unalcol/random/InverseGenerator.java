package unalcol.random;

import unalcol.random.raw.RawGenerator;
import unalcol.types.tag.Tags;

public abstract class InverseGenerator<T> extends Tags implements RandomGenerator<T>, UsesRawGenerator {
	protected RawGenerator raw = null;
	
    public InverseGenerator(){}
    
    public InverseGenerator(RawGenerator raw){ setRawGenerator(raw); }
    
    /**
     * Returns a random double number
     * @param x Inverse value (cumulative probability)
     * @return A random double number
     */
    public abstract T next(double x);

    /**
     * Returns a random double number
     * @return A random double number
     */
    @Override
    public T next(){ return next(real()); }
}