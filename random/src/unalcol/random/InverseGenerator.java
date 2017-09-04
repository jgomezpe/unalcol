package unalcol.random;

import unalcol.services.TaggedCallerNamePair;
import unalcol.types.tag.Tags;

public abstract class InverseGenerator<T> extends Tags implements TaggedCallerNamePair<T>, RandomGenerator<T>, UsesRawGenerator<T> {
	
    public InverseGenerator(){}
    
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
    public T next(){ return next(getRawGenerator(caller()).next()); }
}