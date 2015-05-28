package unalcol.types.collection.vector;

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
public interface VectorReader<T> {
    /**
     * Locates the object located at the given position (according to some linearization process of the structure)
     * @param pos Position of the data object to be located
     * @return The data object at the given position, <i>null</i> if there is not data object at that position
     */
    public T get(int pos);
}
