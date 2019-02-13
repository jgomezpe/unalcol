package unalcol.collection.vector;
import unalcol.clone.Cloneable;
import unalcol.collection.Vector;
import unalcol.math.function.Function;
import unalcol.sort.Merge;
import unalcol.sort.Order;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Company: Kunsamu </p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Sort<T> extends Function<Vector<T>,Vector<T>> {

    protected boolean overwrite = true;

    protected unalcol.sort.Sort<T> sort = null;

    public Sort(){
        sort = new Merge<T>();
    }

    public Sort( unalcol.sort.Sort<T> _sort ) {
        sort = _sort;
    }

    public Sort( Order _order ) {
        sort = new Merge<T>( _order );
    }

    public void apply( Vector<T> input, Order order ){
        apply( input, 0, input.size(), order );
    }

    public void apply( Vector<T> input, int start, int end, Order order ){
        sort.apply( input.buffer(), start, end, order );
    }

    @SuppressWarnings("unchecked")
	public Vector<T> apply( Vector<T> input ){
        if( input.size() > 0 ){
            if (!overwrite){
            	Cloneable c = Cloneable.cast(input);
            	input = (Vector<T>)c.clone();
            }
            Order order = sort.order();
            apply(input, 0, input.size(), order);
        }
        return input;
    }
}