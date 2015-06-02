package unalcol.search.population.variation;

import unalcol.search.space.Space;
import unalcol.search.space.Variation;
import unalcol.types.collection.vector.Vector;

public class VariationToPopulationVariation<T> extends PopulationVariation<T> {
    protected Variation<T> variation;
    
	public VariationToPopulationVariation( Variation<T> variation ){
		this.variation = variation;
	}

	@Override
	public Vector<T> apply(Vector<T> pop) {
		Vector<T> v = new Vector<T>();
		for( T x : pop ){ v.add( variation.apply(x) ); }
		
		return v;
	}

	@Override
	public Vector<T> apply(Space<T> space, Vector<T> pop) {
		Vector<T> v = new Vector<T>();
		for( T x : pop ){ v.add( variation.apply(space, x) ); }
		return v;
	}

}
