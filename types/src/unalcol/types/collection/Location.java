package unalcol.types.collection;

import java.util.NoSuchElementException;

/**
 * <p>Title: Location</p>
 *
 * <p>Description: Creates a Locator for </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface Location<T> {
    public T get() throws NoSuchElementException;
}
