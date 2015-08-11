package unalcol.optimization.blackbox;

import unalcol.optimization.OptimizationGoal;
import unalcol.search.Goal;
import unalcol.search.Solution;
import unalcol.search.single.SinglePointSearch;
import unalcol.search.space.Space;
import unalcol.types.collection.vector.Vector;

public class BlackBoxSearch<T> extends SinglePointSearch<T>{
	protected Vector<Solution<T>> input = new Vector<Solution<T>>();
	protected BlackBoxFunction<T> bb_function;
	protected OptimizationGoal<T> bb_goal;
	protected Object[] args;
	protected SinglePointSearch<T> step;
	
	public BlackBoxSearch( SinglePointSearch<T> step, BlackBoxFunction<T> bb_function, Object... args ) {
		this.step = step;
		this.bb_function = bb_function;
		this.args = args;
		bb_goal = new OptimizationGoal<>(bb_function);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		input.clear();
	}

	@Override
	public Solution<T> apply(Solution<T> solution, Space<T> space, Goal<T> goal) {
		input.add(solution);
		for( int i=0; i<input.size(); i++){
			if( solution.quality() < input.get(i).quality()){
				solution = input.get(i);
			}
		}
		bb_function.train(input, space, goal, args);
		System.out.print(bb_function.apply(solution.value()));
		step.init();
		solution = step.apply(solution, space, bb_goal);
		System.out.println("$"+bb_function.apply(solution.value()));
		return new Solution<T>(solution.value(), goal);
	}

}
