package unalcol.types.collection;
import java.util.NoSuchElementException;

/**
 * <p>Title: Iterator</p>
 *
 * <p>Description: A data structure iterator</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface Iterator<T> {
    /**
     * Returns <i>true</i> Determines if the iterator has more elements
     * @return <i>true</i> if the iterator has more elements, <i>false</i> otherwise
     */
    public boolean hasNext();

    /**
     * Returns the next element in the iteration
     * @return The next element in the iteration
     * @throws NoSuchElementException If there is not a next element in the iteration
     */
    public T next() throws NoSuchElementException;
}
