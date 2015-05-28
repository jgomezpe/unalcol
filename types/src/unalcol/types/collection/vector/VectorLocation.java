package unalcol.types.collection.vector;
import unalcol.types.collection.*;
import java.util.NoSuchElementException;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class VectorLocation<T> implements Location<T> {
    protected int pos;
    protected ImmutableVector<T> vector;

    public VectorLocation( int pos, ImmutableVector<T> vector ) {
        this.vector = vector;
        this.pos = pos;
    }

    public T get() throws NoSuchElementException{
        try{
            return vector.buffer[pos];
        }catch( Exception e ){
            throw new NoSuchElementException("Invalid index .." + pos);
        }
    }

}
