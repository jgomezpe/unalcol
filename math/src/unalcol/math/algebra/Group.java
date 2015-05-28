package unalcol.math.algebra;


/**
 * <p>Title: Group</p>
 *
 * <p>Description: Abstract definition of a Group</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface Group<T> extends Monoid<T>{

    public T fastInverse( T x );

    public T inverse( T x );

    public T fastMinus( T one, T two );

    public T minus( T one, T two );
}
