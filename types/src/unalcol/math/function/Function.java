package unalcol.math.function;

/**
 * <p>Abstract definition of a function</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 * @param <S> Codomain of the function
 * @param <T> Domain of the function
 */
public interface Function<S, T>{
    /**
     * Computes the function
     * @param x Parameter of the function
     * @return Computed value of the function
     */
    public T apply(S x);
}