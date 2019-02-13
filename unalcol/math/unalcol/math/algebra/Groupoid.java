package unalcol.math.algebra;

/**
 * <p>Title: grupoid</p>
 *
 * <p>Description: Abstract definition of a Grupoid</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface Groupoid<T> {
    /**
     * Adds the object one and the object two
     * @param one The first Object
     * @param two The second Object
     * @return The first object after being modified by the group operation
     */
    public abstract T fastPlus(T one, T two);

    /**
     * Adds object the one clone and the two clone
     * @param one The first Object
     * @param two The second Object
     * @return The result
     */
    public T plus(T one, T two);

}
