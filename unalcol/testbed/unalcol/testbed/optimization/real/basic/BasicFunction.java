package unalcol.testbed.optimization.real.basic;

import unalcol.optimization.OptimizationFunction;

public abstract class BasicFunction extends OptimizationFunction<double[]> {
	public abstract double limit();
}