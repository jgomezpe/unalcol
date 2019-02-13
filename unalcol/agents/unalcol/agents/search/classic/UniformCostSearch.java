package unalcol.agents.search.classic;
import unalcol.collection.vector.*;

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
public class UniformCostSearch<T> extends ClassicSearch<T> {
  public UniformCostSearch( int _max_depth ) {
    super( _max_depth );
    list = new Sorted<ClassicSearchNode<T>>( new ClassicSearchNodeOrder<T>() );
  }


  public void add( ClassicSearchNode<T> child ){
     list.add(child);
  }

}
