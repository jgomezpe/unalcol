package unalcol.search.variation;

import unalcol.search.solution.Solution;

public class Variation_n_1<T> extends Variation<T> {
	protected T build( @SuppressWarnings("unchecked") T... pop ){
		return build(set(pop)).object();
	}

	protected Solution<T> build( @SuppressWarnings("unchecked") Solution<T>... pop ){
		return new Solution<T>(build(get(pop)), pop[0].tags(), false);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] apply(T... pop) {
		return (T[])new Object[]{build(pop)};
	}

	@SuppressWarnings("unchecked")
	@Override
	public Solution<T>[] apply(Solution<T>... pop) {
		return (Solution<T>[])new Object[]{build(pop)};
	}

    @Override
	public int range_arity() {
		return 1;
	}	
}