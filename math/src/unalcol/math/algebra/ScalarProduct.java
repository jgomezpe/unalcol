package unalcol.math.algebra;
import unalcol.clone.Cloneable;

/**
 * <p>Title: ScalarProduct</p>
 * <p>Description: Abstract class, multiplies and divide one
 *  object for one scalar.</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public interface ScalarProduct<T> {
    /**
     * Multiplies object one and the scalar x
     * @param one The object
     * @param x The scalar
     */
    public T fastMultiply(T one, double x);

    /**
     * Divide object one by the scalar x
     * @param one The object
     * @param x The scalar
     */
    public T fastDivide(T one, double x);

    /**
     * Multiplies object one clone and the scalar x
     * @param one The object
     * @param x The scalar
     * @return The result
     */
    @SuppressWarnings("unchecked")
	default T multiply(T one, double x){ return fastMultiply((T)Cloneable.cast(one), x); }

    /**
     * Divide object one clone by the scalar x
     * @param one The object
     * @param x The scalar
     * @return The result
     */
    @SuppressWarnings("unchecked")
	default T divide(T one, double x){ return fastDivide((T)Cloneable.cast(one), x); }
}
