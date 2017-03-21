package unalcol.search.selection;

import unalcol.search.Goal;
import unalcol.search.solution.Solution;
import unalcol.sort.Order;

@SuppressWarnings("rawtypes")
public interface QualityBasedSelection<R> extends Selection {
	/**
	 * Selects a subset of candidate solutions from a set of candidates
	 * @param n Number of candidate solutions to be selected
	 * @param x Candidate solutions
	 * @return Indices of the selected candidate solutions
	 */
	public int[] apply( int n, R[] x, Order<R> order );

	/**
	 * Selects a candidate solution from a set of candidate solutions
	 * @param q Quality associated to each candidate solution
	 * @return Index of the selected candidate solution
	*/
	public int choose_one( R[] x, Order<R> order );
	
	@SuppressWarnings("unchecked")
	public default R[] quality( Solution[] x ){
		String gName = Goal.class.getName();
		R[] q = (R[])(new Object[x.length]);
		for( int i=0; i<x.length; i++ ) q[i] = (R)x[i].info(gName);
		return q;
	}
	
	public default Order<R> order( Solution x ){
		  String gName = Goal.class.getName();
		  @SuppressWarnings("unchecked")
		  Goal<Object,R> goal = (Goal<Object,R>)x.data(gName);
		  return goal.order();
	}
	
	/**
	 * Selects a subset of candidate solutions from a set of candidates
	 * @param n Number of candidate solutions to be selected
	 * @param x Candidate solutions
	 * @return Indices of the selected candidate solutions
	 */
	public default int[] apply( int n, Solution[] x ){
		return apply(n, quality(x), order(x[0]));
	}

	/**
	 * Selects a candidate solution from a set of candidate solutions
	 * @param q Quality associated to each candidate solution
	 * @return Index of the selected candidate solution
	*/
	public default int choose_one( Solution[] x ){
		return choose_one(quality(x), order(x[0])); 
	}
}