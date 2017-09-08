package unalcol.search.variation;

import unalcol.Tagged;

public interface Variation_n_1<T> extends Variation<T> {
	public default T build( @SuppressWarnings("unchecked") T... pop ){ return build(set(pop)).unwrap();	}

	public default Tagged<T> build( @SuppressWarnings("unchecked") Tagged<T>... pop ){
		return new Tagged<T>(build(get(pop)));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public default T[] apply(T... pop){ return (T[])new Object[]{build(pop)}; }

	@SuppressWarnings("unchecked")
	@Override
	public default Tagged<T>[] apply(Tagged<T>... pop){ return (Tagged<T>[])new Tagged[]{build(pop)};	}

	@Override
	public default int range_arity(){ return 1;	}	
}