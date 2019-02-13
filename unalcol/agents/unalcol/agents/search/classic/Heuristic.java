package unalcol.agents.search.classic;
import unalcol.agents.search.*;

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
public abstract class Heuristic<T> implements ActionCost<T>{
  protected GraphSpace<T> space;
  public Heuristic( GraphSpace<T> _space ) {
    space = _space;
  }
}
