package unalcol.agents.search.classic;

import unalcol.agents.Action;
import unalcol.agents.search.ActionCost;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gómez
 * @version 1.0
 */
public class Astar<T> extends UniformCostSearch<T>{
  protected Heuristic<T> heuristic;
  public Astar( int _max_depth, Heuristic<T> _heuristic ) {
    super( _max_depth );
    heuristic = _heuristic;
  }

  public double evaluate( T state, Action action, double actual_cost,
                          ActionCost<T> action_cost ){
    return actual_cost + action_cost.evaluate(state, action) +
           heuristic.evaluate(state,action);
  }

}
