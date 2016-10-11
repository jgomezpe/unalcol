package unalcol.agents.search.classic;
import unalcol.sort.*;

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
public class ClassicSearchNodeOrder<T> extends Order<ClassicSearchNode<T>>{
  public String getCanonicalName(){
    return ClassicSearchNode.class.getCanonicalName();
  }

  public ClassicSearchNodeOrder() {
  }

  public int compare( ClassicSearchNode<T> one, ClassicSearchNode<T> two ){
    return (int)(one.cost - two.cost);
  }
}
