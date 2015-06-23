package unalcol.optimization;

import unalcol.search.Solution;
import unalcol.tracer.Tracer;
import unalcol.types.collection.vector.Vector;

public class OptimizationGoalTracer<T> extends Tracer {
	protected Vector<Solution<T>> values = new Vector<Solution<T>>();
	
	@Override
	public void add(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	public void add(Solution<T> s) {
		values.add(s);
	}

	@Override
	public void clean() {
		values.clear();
	}

	@Override
	public void close() {
	}

	@Override
	public Object get() {
		return values;
	}

}
