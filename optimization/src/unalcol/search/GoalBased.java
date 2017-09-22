package unalcol.search;

import unalcol.AbstractThing;

public interface GoalBased<T,R> extends AbstractThing{
	@SuppressWarnings("unchecked")
	public default Goal<T,R> goal(){ return (Goal<T,R>)get(Goal.name); }
	
	public default void setGoal(Goal<T,R> goal ){ set(Goal.name, goal); } 
}
