package unalcol.agents.search.classic;

import unalcol.collection.Vector;
import unalcol.agents.Action;
import unalcol.agents.search.GraphSpace;
import unalcol.search.Goal;
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
public class IteratedDepthFirstSearch<T> extends ClassicSearch<T>{
  protected DepthFirstSearch<T> depth_search = null;
  public IteratedDepthFirstSearch( int _max_depth ) {
    super( _max_depth );
  }

  public void add( ClassicSearchNode<T> child ){}

  public Vector<Action> apply( T initial, GraphSpace<T> space, Goal<T,Boolean> goal, ActionCost<T> cost ){
    Vector<Action> path = null;
    int depth = 0;
    while( path != null && depth < max_depth ){
      depth++;
      depth_search = new DepthFirstSearch<T>(depth);
      path = depth_search.apply(initial, space, goal, cost );
    }
    return path;
  }
}
