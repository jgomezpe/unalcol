package unalcol.agents.search;

import unalcol.agents.*;
import unalcol.search.space.Space;
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
public interface GraphSpace<T>  extends Space<T>{
	public T succesor( T state, Action action );
	public Vector<Action> succesor( T state );
	public ActionCost<T> cost();
}