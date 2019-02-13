package unalcol.testbed.optimization.real.basic;

import unalcol.optimization.Problem;


import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.real.HyperCube;
import unalcol.search.space.Space;
import unalcol.real.array.Array;

public class BasicRealsProblem implements Problem<double[]>{
	
	protected BasicFunction f;
	
	protected int D; 
	
	public BasicRealsProblem(String function, int D){
	    ClassLoader classLoader = this.getClass().getClassLoader();
	    try {
	        @SuppressWarnings("rawtypes")
			Class aClass = classLoader.loadClass("unalcol.testbed.optimization.real.basic."+function);
	        System.out.println("aClass.getName() = " + aClass.getName());
	        f = (BasicFunction)aClass.newInstance();
	    } catch (Exception e){ e.printStackTrace(); }
		this.D = D;
	}
	
	@Override
	public OptimizationFunction<double[]> f(){ return f; }

	@Override
	public Space<double[]> space() {
		double[] max = Array.create(D, f.limit());
		double[] min = Array.create(D, -f.limit());
		return new HyperCube(min, max);
	}
}