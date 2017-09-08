package unalcol.search.replacement;

import unalcol.search.Goal;

public abstract class GoalBasedReplacement<T,R> implements Replacement<T>{
	protected Goal<T,R> goal;
	public GoalBasedReplacement(Goal<T,R> goal){ this.goal = goal; }
	public Goal<T,R> goal(){ return goal; };
}
