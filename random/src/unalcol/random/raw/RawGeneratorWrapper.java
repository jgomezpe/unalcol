package unalcol.random.raw;

import unalcol.services.Service;
import unalcol.services.TaggedCallerNamePair;
import unalcol.types.tag.Tags;

public class RawGeneratorWrapper<T> extends Tags implements TaggedCallerNamePair<T>, RawGenerator<T>{
    @Override
    public double next() {
	try{ return (double)Service.run(RawGenerator.name,caller()); }catch(Exception e){ return Math.random(); }
    }
}