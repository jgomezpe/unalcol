package unalcol.random;

import unalcol.random.raw.RawGenerator;
import unalcol.random.raw.RawGeneratorWrapper;
import unalcol.services.AbstractMicroService;

public interface InverseGenerator<T> extends RandomGenerator<T> {
	public default AbstractMicroService<?> wrap(String id){
		if(id.equals(RawGenerator.name)) return new RawGeneratorWrapper();
		return null;
	}
	
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
    public default T next(){ return next(((RawGenerator)getMicroService(RawGenerator.name)).next()); }
}