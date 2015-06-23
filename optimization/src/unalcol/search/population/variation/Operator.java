package unalcol.search.population.variation;

import unalcol.search.space.Space;
import unalcol.types.collection.vector.Vector;

public abstract class Operator<T> {
	
	public int arity(){ return 0; };
	
	public void adapt( double productivity ){};

    @SuppressWarnings("unchecked")
	public abstract Vector<T> apply(T... pop);

    @SuppressWarnings("unchecked")
	public Vector<T> apply(Space<T> space, T... pop){
        return space.repair(apply(pop));
    }
    
	public Vector<T> apply(Vector<T> pop){
        return apply(pop.toArray());
    }	
    
	public Vector<T> apply(Space<T> space, Vector<T> pop){
        return space.repair(apply(pop));
    }    
}