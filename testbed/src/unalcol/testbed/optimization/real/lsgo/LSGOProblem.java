package unalcol.testbed.optimization.real.lsgo;

import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.Problem;
import unalcol.optimization.real.HyperCube;
import unalcol.search.space.Space;
import unalcol.types.real.array.DoubleArray;

public class LSGOProblem  implements Problem<double[]> {
	protected lsgo.Function f;
	protected OptimizationFunction<double[]> tf;
	
	public LSGOProblem( String function ) {
	    ClassLoader classLoader = this.getClass().getClassLoader();
	    try {
	        @SuppressWarnings("rawtypes")
			Class aClass = classLoader.loadClass("lsgo."+function);
	        System.out.println("aClass.getName() = " + aClass.getName());
	        f = (lsgo.Function)aClass.newInstance();
			tf = new OptimizationFunction<double[]>() {
				@Override
				public java.lang.Double compute(double[] x) { return f.compute(x); }
			};
	    } catch (Exception e){ e.printStackTrace(); }
	}
	
	@Override
	public OptimizationFunction<double[]> f() { return tf; }
	
	public Space<double[]> space(){
		int D = f.getDimension();
		double minL = f.getMin();
		double maxL = f.getMax();
		double[] max = DoubleArray.create(D, maxL);
		double[] min = DoubleArray.create(D, minL);
		return new HyperCube(min, max);		
	}
}
