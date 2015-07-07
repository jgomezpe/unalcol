package unalcol.optimization.blackbox;

import unalcol.search.Goal;
import unalcol.search.Solution;
import unalcol.search.space.Space;
import unalcol.tracer.Tracer;
import unalcol.types.collection.vector.Vector;

public class BlackBoxTracer<T> extends Tracer{
	protected Vector<Solution<T>> input = new Vector<Solution<T>>();
	protected BlackBoxFunction<T> bb_function = null;
	protected Object[] args;
	protected Space<T> space;
	protected Goal<T> goal;
	
	public BlackBoxTracer( Space<T> space,  Goal<T> goal, BlackBoxFunction<T> bb_function, Object... args ) {
		this.bb_function = bb_function;
		this.space = space;
		this.args = args;
		this.goal = goal;
	}
	
	public void add( Solution<T> solution ){
		input.add(solution);
		while( input.size() > 100 ){
			input.remove(0);
		}
		bb_function.train(input, space, goal, args);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void add(Object... data) {
		add( (Solution<T>)data[0] );
	}

	@Override
	public void clean() {
		input.clear();
	}

	@Override
	public void close() {
	}

	@Override
	public Object get() {
		// TODO Auto-generated method stub
		return bb_function;
	}

}
