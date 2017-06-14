package unalcol.math.logic;

/**
 * <p>Defines the basic behavior of a logic predicate.</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public abstract class Predicate<T> {

    /**
     * Evaluates the predicate
     * @param object Predicates argument
     * @return true if the attributes of the predicate (arguments) satisfy the predicate, false in other case
     */
    public abstract boolean evaluate(T object);

    /**
     * Initializes the internal state of the predicate
     */
    public void init() {}

}
