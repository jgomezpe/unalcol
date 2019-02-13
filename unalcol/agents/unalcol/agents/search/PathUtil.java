package unalcol.agents.search;
import unalcol.agents.*;
import unalcol.collection.Vector;

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
public class PathUtil<T>{
  public double evaluate( T state, GraphSpace<T> space,
                          Vector<Action> path, ActionCost<T> cost ){
    double c = 0.0;
    for(Action action : path){
      c += cost.evaluate( state, action);
      state = space.succesor( state, action );
    }
    return c;
  }
  
  public T final_state( T initial,  GraphSpace<T> space, Vector<Action> path ){
      for(Action action : path){
        initial = space.succesor( initial, action );
      }
      return initial;
  }
}
