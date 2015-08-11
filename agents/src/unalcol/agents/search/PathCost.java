package unalcol.agents.search;
import unalcol.agents.*;
import java.util.*;

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
public abstract class PathCost{
  public double evaluate( State state, SearchSpace space,
                          Vector<Action> path, ActionCost cost ){
    double c = 0.0;
    Iterator<Action> iter = path.iterator();
    while( iter.hasNext() ){
      Action action = iter.next();
      c += cost.evaluate( state, action);
      state = space.succesor( state, action );
    }
    return c;
  }
}
