package unalcol.optimization;

import unalcol.search.space.Space;

public interface Problem<T> {
	public OptimizationFunction<T> f();
	public Space<T> space();
}