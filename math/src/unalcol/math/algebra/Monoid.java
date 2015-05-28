package unalcol.math.algebra;


/**
 * <p>Title: Monoid</p>
 *
 * <p>Description: Abstract definition of a Monoid</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface Monoid<T> extends Groupoid<T>{
    public T identity( T x );
}
