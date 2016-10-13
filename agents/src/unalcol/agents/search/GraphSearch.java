package unalcol.agents.search;
import unalcol.types.collection.vector.*;
import unalcol.agents.*;
import unalcol.search.Goal;
import unalcol.search.local.LocalSearch;
import unalcol.search.solution.Solution;
import unalcol.search.space.Space;

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
public abstract class GraphSearch<T> extends LocalSearch<T,Boolean>{
  public abstract Vector<Action> apply( T initial, GraphSpace<T> space, Goal<T,Boolean> goal, ActionCost<T> cost  );
  
  public Solution<T> apply( Solution<T> x, Space<T> space ){
      @SuppressWarnings("unchecked")
      Goal<T,Boolean> goal = (Goal<T,Boolean>)x.data(Goal.class.getName());
      @SuppressWarnings("unchecked")
      ActionCost<T> cost = (ActionCost<T>)space.data(ActionCost.class.getName());
      Vector<Action> action = apply( x.object(), (GraphSpace<T>)space, goal, cost);
      PathUtil<T> path = new PathUtil<T>();
      T y = path.final_state(x.object(), (GraphSpace<T>)space, action);
      Solution<T> sol = new Solution<T>(y);
      sol.set(PathUtil.class.getName(), action);
      return sol;
  }
}
