package unalcol.types.collection.array;
import unalcol.types.collection.*;

/**
 * <p>Title: ArrayCollection</p>
 *
 * <p>Description: An array collection (indexable collection)</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface ArrayCollection<T> extends FiniteCollection<T>{
        /**
         * Gets the object at the given position
         * @param index int
         * @return T
         * @throws ArrayIndexOutOfBoundsException
         */
        public T get( int index ) throws ArrayIndexOutOfBoundsException;
}
