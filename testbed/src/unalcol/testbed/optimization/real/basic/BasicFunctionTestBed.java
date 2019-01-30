package unalcol.testbed.optimization.real.basic;

import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.real.HyperCube;
import unalcol.search.space.Space;
import unalcol.testbed.optimization.FunctionTestBed;
import unalcol.types.real.array.DoubleArray;

public class BasicFunctionTestBed extends FunctionTestBed<double[]>{
	@SuppressWarnings("unchecked")
	protected static OptimizationFunction<double[]>[] f = new OptimizationFunction[]{
		new Rastrigin(),
		new RosenbrockSaddle(),
		new Schwefel(),
		new Griewangk(),
		new Ackley(),
		new Bohachevsky(),
		new Bohachevsky2(),
		new Shubert_1()
	};
	
	protected static double[] limit = new double[]{ 5.12, 2.048, 512, 600, 32.768, 100, 100, 5.12 };
	
	protected int k;
	protected int D; 
	
	public BasicFunctionTestBed(int k, int D){
		super(k);
		this.D = D;
	}
	
	@Override
	public Double compute(double[] x){ return f[k].compute(x); }

	@Override
	public Space<double[]> space() {
		double[] max = DoubleArray.create(D, limit[k]);
		double[] min = max.clone();
		for( int i=0; i<D; i++ ) min[i] = -min[i];
		return new HyperCube(min, max);
	}
}
