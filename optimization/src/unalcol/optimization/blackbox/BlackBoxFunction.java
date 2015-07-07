package unalcol.optimization.blackbox;

import unalcol.optimization.OptimizationFunction;
import unalcol.search.Goal;
import unalcol.search.Solution;
import unalcol.search.space.Space;
import unalcol.types.collection.vector.Vector;

public abstract class BlackBoxFunction<T> extends OptimizationFunction<T>{
	public abstract void train( Vector<Solution<T>> input, Space<T> space, Goal<T> goal, Object... args );
}
