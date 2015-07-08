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
		int best = 0;
		for( int i=1; i<input.size(); i++ ){
//			System.out.println(input.get(i).quality() + "#" + input.get(best).quality());
			if( input.get(i).quality() <= input.get(best).quality() ){
				best = i;
			}
		}
//		System.out.print( "Preliminar Best:"+ best );
		while( input.size() > 10 && best > 5 ){
			input.remove(0);
			best--;
		}
		while( input.size() > 20 ){
			input.remove(Math.min(best+1, input.size()-1));
		}
//		System.out.println( " Best:"+ best );
		bb_function.train(input, space, goal, args);
		// if( Math.random() < 0.1 )	System.gc();
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
