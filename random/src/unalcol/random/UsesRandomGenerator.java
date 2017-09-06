package unalcol.random;

import unalcol.services.Service;
import unalcol.types.tag.AbstractTags;

public interface UsesRandomGenerator<T> extends AbstractTags{  
    public default void setRandomGenerator(RandomGenerator<T> raw){ set(Service.USES+RandomGenerator.name,raw); }
    public default RandomGenerator<T> getRandomGenerator(Object caller ){
	@SuppressWarnings("unchecked")
	RandomGenerator<T> raw = (RandomGenerator<T>)data(Service.USES+RandomGenerator.name);
    	if( raw==null ) raw = new RandomGeneratorWrapper<T>();
    	raw.setCaller(caller);
	return raw;
    }
}
