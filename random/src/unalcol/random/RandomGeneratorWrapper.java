package unalcol.random;

import unalcol.services.MicroService;
import unalcol.services.Service;

public class RandomGeneratorWrapper<T> extends MicroService<T> implements RandomGenerator<T>{
    @SuppressWarnings("unchecked")
    @Override
    public T next() {
	try{ return (T)Service.run(RandomGenerator.name,caller()); }catch(Exception e){ return null; }    }
}