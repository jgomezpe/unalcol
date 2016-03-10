package unalcol.search.variation;

import unalcol.search.solution.Solution;

/**
 * <p>Title:ArityTwo</p>
 * <p>Description: A binary operator </p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public interface ArityTwoSearchOperator<T> extends SearchOperator<T> {

  /**
   * Apply the unary genetic operator over the individual
   * @param obj Individual to be modified by the genetic operator
   * @return extra information of the genetic operator
   */
  public T[] apply(T one, T two);

  public Solution<T>[] apply(Solution<T> one, Solution<T> two);

  /**
   * Return the genetic operator arity
   * @return the genetic operator arity
   */
  @Override
  public default int arity() { return 2; }
}
