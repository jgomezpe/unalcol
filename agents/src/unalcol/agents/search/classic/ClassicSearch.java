package unalcol.agents.search.classic;

import unalcol.types.collection.vector.*;
import unalcol.agents.Action;
import unalcol.agents.search.*;
import unalcol.clone.Clone;

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
public abstract class ClassicSearch implements Search {
  protected Vector<ClassicSearchNode> list;
  protected int max_depth;
  public ClassicSearch( int _max_depth ) {
    max_depth = _max_depth;
    list = new Vector<ClassicSearchNode>();
  }

  public abstract void add( ClassicSearchNode child );

  public double evaluate( State state, Action action, double actual_cost,
                          ActionCost action_cost ){
    return actual_cost + action_cost.evaluate(state, action);
  }

  public Vector<Action> apply( State initial, SearchSpace space, GoalTest goal,
                               ActionCost cost ){
    list.clear();
    ClassicSearchNode node = new ClassicSearchNode(initial, new Vector<Action>(), 0.0 );
    list.add(node);
    while( node != null && !goal.test(node.state) ){
       list.remove(0);
       if( node.path.size() < max_depth ){
         State state = node.state;
         Vector<Action> actions = space.succesor(state);
         for (int i = 0; i < actions.size(); i++) {
           Action action = actions.get(i);
           State child_state = space.succesor(state, action);
           if( space.feasible(child_state) ){
             double path_cost = evaluate(state, action, node.cost, cost);
             @SuppressWarnings("unchecked")
			 Vector<Action> path = (Vector<Action>)Clone.create(node.path);
             path.add(action);
             ClassicSearchNode child_node = new ClassicSearchNode(child_state,
                 path, path_cost);
             add(child_node);
           }
         }
       }
       node = list.get(0);
    }
    if( node != null ){
      return node.path;
    }
    return null;
  }
}
