package unalcol.search;

public class BasicGoalBased<T,R> implements GoalBased<T, R>{
	protected Goal<T,R> goal;
	@Override
	public Goal<T, R> goal() { return goal; }
	
	@Override
	public void setGoal(Goal<T, R> goal) { this.goal = goal; }
}
