package unalcol.search.variation;

import unalcol.search.solution.Solution;

public interface Variation_n_1<T> extends Variation<T> {
	public default T build( @SuppressWarnings("unchecked") T... pop ){ return build(set(pop)).object();	}

	public default Solution<T> build( @SuppressWarnings("unchecked") Solution<T>... pop ){
		return new Solution<T>(build(get(pop)), pop[0].tags(), false);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public default T[] apply(T... pop){ return (T[])new Object[]{build(pop)}; }

	@SuppressWarnings("unchecked")
	@Override
	public default Solution<T>[] apply(Solution<T>... pop){ return (Solution<T>[])new Object[]{build(pop)};	}

	@Override
	public default int range_arity(){ return 1;	}	
}