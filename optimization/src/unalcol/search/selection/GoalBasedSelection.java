package unalcol.search.selection;

import unalcol.Tagged;
import unalcol.search.Goal;

public abstract class GoalBasedSelection<T,R> implements Selection<T> {
	protected Goal<T,R> goal;
	
	public GoalBasedSelection( Goal<T,R> goal ){ this.goal=goal; }
	
	public Goal<T,R> goal(){ return goal; };

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
	
	public R[] quality( Tagged<T>[] x ){ return goal().array_apply(x);	}
	
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