package unalcol.search.population.variation;

import unalcol.search.space.Variation;

public class VariationToArityOne<T> extends ArityOne<T>{
    protected Variation<T> variation;
    
	public VariationToArityOne( Variation<T> variation ){
		this.variation = variation;
	}

	@Override
	public T get(T x) {
		// TODO Auto-generated method stub
		return variation.apply(x);
	}

}
