package unalcol.search.selection;

import unalcol.types.collection.vector.*;

/**
 * <p>Title: Selection</p>
 * <p>Description: Abstract selection operator on populations of candidate solutions.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 * @param <T> Type of solutions to be selected
 */

public abstract class Selection<T>{
  /**
   * Selects a subset of candidate solutions from a set of candidates
   * @param n Number of candidate solutions to be selected
   * @param x Candidate solutions
   * @param q Quality associated to each candidate solution
   * @return Indices of the selected candidate solutions
   */
  public Vector<Integer> apply( int n, Vector<T> x, double[] q ){
      return apply(n, q);
  }

  /**
   * Selects a subset of candidate solutions from a set of candidates
   * @param n Number of candidate solutions to be selected
   * @param q Quality associated to each candidate solution
   * @return Indices of the selected candidate solutions
   */
  public abstract Vector<Integer> apply( int n, double[] q );

  /**
   * Selects a candidate solution from a set of candidate solutions
   * @param x Candidate solutions
   * @param q Quality associated to each candidate solution
   * @return Index of the selected candidate solution
   */
  public int choose_one( Vector<T> x, double[] q ){
      return choose_one(q);
  }

  /**
   * Selects a candidate solution from a set of candidate solutions
   * @param q Quality associated to each candidate solution
   * @return Index of the selected candidate solution
   */
  public abstract int choose_one( double[] q );
  
}