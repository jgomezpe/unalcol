package unalcol.search.selection;

import unalcol.search.BasicGoalBased;
import unalcol.search.Goal;
import unalcol.object.Tagged;

public abstract class GoalBasedSelection<T,R> extends BasicGoalBased<T,R> implements Selection<T> {
	
	public GoalBasedSelection(){}
	public GoalBasedSelection( Goal<T,R> goal ){ setGoal(goal); }
	
	/**
	 * Selects a subset of candidate solutions from a set of candidates
	 * @param n Number of candidate solutions to be selected
	 * @param x Candidate solutions
	 * @return Indices of the selected candidate solutions
	 */
	public abstract int[] apply( int n, R[] x );

	/**
	 * Selects a candidate solution from a set of candidate solutions
	 * @param q Quality associated to each candidate solution
	 * @return Index of the selected candidate solution
	*/
	public abstract int choose_one( R[] x );
	
	public R[] quality( Tagged<T>[] x ){ return goal().set_apply(x);	}
	
	/**
	 * Selects a subset of candidate solutions from a set of candidates
	 * @param n Number of candidate solutions to be selected
	 * @param x Candidate solutions
	 * @return Indices of the selected candidate solutions
	 */
	public int[] apply( int n, Tagged<T>[] x ){ return apply(n, quality(x)); }

	/**
	 * Selects a candidate solution from a set of candidate solutions
	 * @param q Quality associated to each candidate solution
	 * @return Index of the selected candidate solution
	*/
	public int choose_one( Tagged<T>[] x ){ return choose_one(quality(x)); }
}