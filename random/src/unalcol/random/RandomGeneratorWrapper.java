package unalcol.random;

import unalcol.services.Service;
import unalcol.services.TaggedCallerNamePair;
import unalcol.types.tag.Tags;

public class RandomGeneratorWrapper<T>  extends Tags implements TaggedCallerNamePair<Object>, RandomGenerator<T>{
    @SuppressWarnings("unchecked")
    @Override
    public T next() {
	try{ return (T)Service.run(RandomGenerator.name,caller()); }catch(Exception e){ return null; }    }
}