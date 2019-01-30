package unalcol.testbed.optimization;

import unalcol.optimization.OptimizationFunction;
import unalcol.search.space.Space;

public abstract class FunctionTestBed<T> extends OptimizationFunction<T>{
	protected int k;
	public FunctionTestBed( int k ){ this.k = k; }
	public abstract Space<double[]> space();
}
