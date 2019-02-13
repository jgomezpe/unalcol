package unalcol.math.function;

/**
 * <p>Definition of a Function that has Inverse</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public abstract class InvertableFunction<S, T> extends Function<S, T> {
	/**
	 * Inverse value
	 * @param y computed value (in the range of the function)
	 * @return Argument value (in the domain of the function)
	 */
	public abstract S inverse(T y);
}