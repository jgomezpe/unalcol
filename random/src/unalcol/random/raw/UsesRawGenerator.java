package unalcol.random.raw;

import unalcol.services.Service;
import unalcol.types.tag.AbstractTags;

public interface UsesRawGenerator<T> extends AbstractTags{  
    public default void setRawGenerator(RawGenerator<T> raw){ set(Service.USES+RawGenerator.name,raw); }
    public default RawGenerator<T> getRawGenerator(T caller ){
	@SuppressWarnings("unchecked")
	RawGenerator<T> raw = (RawGenerator<T>)data(Service.USES+RawGenerator.name);
    	if( raw==null ) raw = new RawGeneratorWrapper<T>();
    	raw.setCaller(caller);
	return raw;
    }
}