package unalcol.agents.search;

import unalcol.agents.*;
import unalcol.types.collection.vector.*;

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
public interface SearchSpace {
  public boolean feasible( State state );
  public State succesor( State state, Action action );
  public Vector<Action> succesor( State state );
}
