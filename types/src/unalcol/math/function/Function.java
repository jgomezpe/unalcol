package unalcol.math.function;

import unalcol.service.Service;

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
public abstract class Function<S, T> extends Service{
    /**
     * Computes the function
     * @param x Parameter of the function
     * @return Computed value of the function
     */
    public abstract T apply(S x);
}