package unalcol.search.variation;

import unalcol.instance.Instanteable;
import unalcol.object.Tagged;

public interface Variation_n_1<T> extends Variation<T> {
	public default T build( @SuppressWarnings("unchecked") T... pop ){ return build(wrap(pop)).unwrap();	}

	@SuppressWarnings("unchecked")
	public default Tagged<T> build( Tagged<T>... pop ){ return (Tagged<T>)Instanteable.cast(pop[0]).create(build(unwrap(pop))); }
	
	@SuppressWarnings("unchecked")
	@Override
	public default T[] apply(T... pop){ return (T[])new Object[]{build(pop)}; }

	@SuppressWarnings("unchecked")
	@Override
	public default Tagged<T>[] apply(Tagged<T>... pop){ return (Tagged<T>[])new Tagged[]{build(pop)};	}

	@Override
	public default int range_arity(){ return 1;	}	
}