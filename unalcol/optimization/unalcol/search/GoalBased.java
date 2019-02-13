package unalcol.search;

public interface GoalBased<T,R>{
	public Goal<T,R> goal();
	
	public void setGoal(Goal<T,R> goal ); 
}
