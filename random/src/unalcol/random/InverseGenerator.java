package unalcol.random;

import unalcol.random.raw.UsesRawGenerator;
import unalcol.services.TaggedCallerNamePair;

public interface InverseGenerator<T> extends TaggedCallerNamePair<Object>, RandomGenerator<T>, UsesRawGenerator<Object> {
	
    /**
     * Returns a random double number
     * @param x Inverse value (cumulative probability)
     * @return A random double number
     */
    public T next(double x);

    /**
     * Returns a random double number
     * @return A random double number
     */
    @Override
    public default T next(){ return next(getRawGenerator(caller()).next()); }
}