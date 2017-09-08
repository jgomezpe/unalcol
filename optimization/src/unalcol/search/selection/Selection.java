package unalcol.search.selection;

import unalcol.Tagged;

/**
 * <p>Title: Selection</p>
 * <p>Description: Abstract selection operator on populations of candidate solutions.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 * @param <T> Type of solutions to be selected
 */

public interface Selection<T>{

  /**
   * Selects a subset of candidate solutions from a set of candidates
   * @param n Number of candidate solutions to be selected
   * @param x Candidate solutions
   * @return Indices of the selected candidate solutions
   */
	public int[] apply( int n, Tagged<T>[] x );

  /**
   * Selects a candidate solution from a set of candidate solutions
   * @param q Quality associated to each candidate solution
   * @return Index of the selected candidate solution
   */
	public int choose_one( Tagged<T>[] x );
	
	public default Tagged<T>[] pick(int n, Tagged<T>[] x ){
		@SuppressWarnings("unchecked")
		Tagged<T>[] obj = new Tagged[n];
		int[] idx = apply(n,x);
		for( int i=0; i<n; i++ ){
			obj[i] = x[idx[i]];
		}
		return obj;
	}
}