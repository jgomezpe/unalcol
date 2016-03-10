package unalcol.search.variation;

import unalcol.search.solution.Solution;

public interface BuildOneSearchOperator<T> extends SearchOperator<T> {
	@SuppressWarnings("unchecked")
	public T build( T... pop );

	@SuppressWarnings("unchecked")
	public Solution<T> build( Solution<T>... pop );

	@SuppressWarnings("unchecked")
	@Override
	public default T[] apply(T... pop) {
		return (T[])new Object[]{build(pop)};
	}

	@SuppressWarnings("unchecked")
	@Override
	public default Solution<T>[] apply(Solution<T>... pop) {
		return (Solution<T>[])new Object[]{build(pop)};
	}
}