package unalcol.random.raw;

import unalcol.services.Service;
import unalcol.services.MicroService;

public class RawGeneratorWrapper extends MicroService<Object> implements RawGenerator{
	public RawGeneratorWrapper() { setCaller(Object.class);	}
	
    @Override
    public double next(){ try{ return (double)Service.run(RawGenerator.name,caller()); }catch(Exception e){ return Math.random(); } }
}