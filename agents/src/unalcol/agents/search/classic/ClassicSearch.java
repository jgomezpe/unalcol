package unalcol.agents.search.classic;

import unalcol.types.collection.vector.*;
import unalcol.agents.Action;
import unalcol.agents.search.*;
import unalcol.clone.Cloneable;
import unalcol.search.Goal;

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
public abstract class ClassicSearch<T> extends GraphSearch<T> {
  protected Vector<ClassicSearchNode<T>> list;
  protected int max_depth;
  public ClassicSearch( int _max_depth ) {
    max_depth = _max_depth;
    list = new Vector<ClassicSearchNode<T>>();
  }

  public abstract void add( ClassicSearchNode<T> child );

  public double evaluate( T state, Action action, double actual_cost, ActionCost<T> action_cost ){
    return actual_cost + action_cost.evaluate(state, action);
  }

  @SuppressWarnings("unchecked")
@Override
  public Vector<Action> apply( T initial, GraphSpace<T> space, Goal<T,Boolean> goal, ActionCost<T> cost ){
    list.clear();
    ClassicSearchNode<T> node = new ClassicSearchNode<T>( new Vector<Action>(), 0.0 );
    list.add(node);
    T state = initial;
    while( node != null && !goal.apply(state) ){
       list.remove(0);
       if( node.path.size() < max_depth ){
         Vector<Action> actions = space.succesor(state);
         for (int i = 0; i < actions.size(); i++) {
           Action action = actions.get(i);
           T child_state = space.succesor(state, action);
           if( space.feasible(child_state) ){
             double path_cost = evaluate(state, action, node.cost, cost);
             Vector<Action> path;
             path = (Vector<Action>)Cloneable.cast(node.path).clone();
             path.add(action);
             ClassicSearchNode<T> child_node = new ClassicSearchNode<T>( path, path_cost);
             add(child_node);
           }
         }
       }
       node = list.get(0);
       if( node!=null){
    	   state = node.state(space, initial);
       }
    }
    if( node != null ){
      return node.path;
    }
    return null;
  }
}
