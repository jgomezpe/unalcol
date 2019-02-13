package unalcol.collection.vector;


import unalcol.collection.Vector;
import unalcol.collection.array.BinarySearch;
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
public class SortedInsert<T> {
    protected BinarySearch<T> search = new BinarySearch<T>(null, null);
    public SortedInsert(){}

	public void apply( Vector<T> set, T x, Order order, boolean multiSet ){
		try{
			search.set(set.buffer());
			search.set(order);
			int pos = search.findRight(x);
			if( pos > 0 ){
				if( multiSet || order.compare( set.get(pos-1), x )!=0 )
					if( pos == set.size() ){ set.add(x); }else{ set.add(pos, x); }
			}else	set.add(0, x); 
		}catch(Exception e ){}	
    }
}