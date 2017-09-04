package unalcol.random;

import unalcol.random.raw.RawGenerator;
import unalcol.services.Service;
import unalcol.types.tag.AbstractTags;

public interface UsesRawGenerator extends AbstractTags{  
    public default void setRawGenerator(RawGenerator raw){ set(RawGenerator.name,raw); }
    
    public default double real(){
    	RawGenerator raw = (RawGenerator)data(RawGenerator.name);
    	if( raw!=null ) return raw.next();
		double x=0.0;
		try{ x = (double)Service.run(RawGenerator.name, this); }catch(Exception e){}
        return x;
    }
}