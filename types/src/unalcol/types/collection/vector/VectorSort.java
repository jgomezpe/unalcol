package unalcol.types.collection.vector;
import unalcol.algorithm.*;
import unalcol.sort.*;
import unalcol.sort.algorithm.*;
import unalcol.clone.*;

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
public class VectorSort<T> extends ThreadFunction<ImmutableVector<T>,ImmutableVector<T>> {

    protected boolean overwrite = true;

    protected Sort<T> sort = null;

    public VectorSort(){
        sort = new MergeSort<T>();
    }

    public VectorSort( Sort<T> _sort ) {
        sort = _sort;
    }

    public VectorSort( Order<T> _order ) {
        sort = new MergeSort<T>( _order );
    }

    public void apply( ImmutableVector<T> input, Order<T> order ){
        apply( input, 0, input.size, order );
    }

    public void apply( ImmutableVector<T> input, int start, int end, Order<T> order ){
        T[] obj = (T[])input.buffer;
        sort.apply( obj, start, end, order );
    }

    @SuppressWarnings("unchecked")
	public ImmutableVector<T> apply( ImmutableVector<T> input ){
        if( input.size() > 0 ){
            if (!overwrite) {
                input = (Vector<T>) Clone.create(input);
            }
            Order<T> order = sort.getOrder(input.buffer);
            apply(input, 0, input.size, order);
        }
        return input;
    }
}
