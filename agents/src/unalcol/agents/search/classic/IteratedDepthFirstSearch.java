package unalcol.agents.search.classic;

import unalcol.agents.search.GoalTest;
import unalcol.types.collection.vector.*;
import unalcol.agents.Action;
import unalcol.agents.search.SearchSpace;
import unalcol.agents.search.State;
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
public class IteratedDepthFirstSearch extends ClassicSearch{
  protected DepthFirstSearch depth_search = null;
  public IteratedDepthFirstSearch( int _max_depth ) {
    super( _max_depth );
  }

  public void add( ClassicSearchNode child ){}

  public Vector<Action> apply( State initial, SearchSpace space, GoalTest goal,
                               ActionCost cost ){
    Vector<Action> path = null;
    int depth = 0;
    while( path != null && depth < max_depth ){
      depth++;
      depth_search = new DepthFirstSearch(depth);
      path = depth_search.apply(initial, space, goal, cost );
    }
    return path;
  }
}
