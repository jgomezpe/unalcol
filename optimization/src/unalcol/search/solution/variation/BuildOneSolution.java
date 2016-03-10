package unalcol.search.solution.variation;

import unalcol.search.solution.Solution;

public abstract class BuildOneSolution<T> implements SolutionOperator<T> {
	@SuppressWarnings("unchecked")
	public abstract Solution<T> build( Solution<T>... pop );

	@SuppressWarnings("unchecked")
	@Override
	public Solution<T>[] apply(Solution<T>... pop) {
		return (Solution<T>[])new Solution[]{build(pop)};
	}
}