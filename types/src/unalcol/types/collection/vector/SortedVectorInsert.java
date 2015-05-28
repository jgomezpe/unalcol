package unalcol.types.collection.vector;


import unalcol.sort.*;

/**
 * <p>Title: SortedInsert</p>
 *
 * <p>Description: Insert operation for sorted vectors</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class SortedVectorInsert<T> {
    protected SortedVectorSearch<T> search = new SortedVectorSearch<T>();
    public SortedVectorInsert() {}

    public void apply( Vector<T> set, T x, Order<T> order, boolean multiSet ){
        int pos = search.findRight(set, x, order);
        if( pos > 0 ){
          if( multiSet || order.compare( set.get(pos-1), x )!=0 ){
              if( pos == set.size() ){ set.add(x); }else{ set.add(pos, x); }
          }
        }else{
          set.add(0, x);
        }
    }
}
