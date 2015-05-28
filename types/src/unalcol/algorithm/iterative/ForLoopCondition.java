package unalcol.algorithm.iterative;

import unalcol.math.logic.Predicate;

/**
 * <p>Represents the condition of a for loop</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class ForLoopCondition<T> extends Predicate<T> implements Cloneable {

    /**
     * Iterator variable iter = start
     */
    protected int iter = -1;

    /**
     * The start value
     */
    protected int start = 0;
    /**
     * End value for the iteration process  iter < end
     */
    protected int end = 0;
    /**
     * Increment applied to the iterator in each iteration   iter += inc;
     */
    protected int inc = 1;

    /**
     * Constructor: Creates an iterate condition with start, end, inc values
     * @param start Start value for the iteration process
     * @param end End value for the iteration process
     * @param inc Increment applied to the iterator in each iteration
     */
    public ForLoopCondition(int start, int end, int inc) {
        this.start = start;
        this.end = end;
        this.inc = inc;
        this.iter = start - inc;
    }

    /**
     * Constructor: Creates an iterate condition with start, and end values. The inc value is fixed in one
     * @param start Start value for the iteration process
     * @param end End value for the iteration process
     */
    public ForLoopCondition(int start, int end) {
        this.start = start;
        this.iter = start - inc;
        this.end = end;
    }

    /**
     * Constructor: Creates an iterate condition with start, and end values.
     * The inc value is fixed in one, and the start value is fixed in zero
     * @param end End value for the iteration process
     */
    public ForLoopCondition(int end) {
        this.end = end;
    }

    /**
     * Creates a clone of the predicate
     * @return A copy of the predicate
     */
    public Object clone() {
        return new ForLoopCondition<T>(start, end, inc);
    }

    /**
     * Sets the values of the predicate
     * @param start Start value for the iteration process
     * @param end End value for the iteration process
     * @param inc Increment applied to the iterator in each iteration
     */
    public void set(int start, int end, int inc) {
        this.start = start;
        this.end = end;
        this.inc = inc;
        this.iter = start - inc;
    }


    /**
     * Evaluates the iteration condition and after that, it increments the iterator
     * @param object Predicates argument
     * @return true if the condition is satisfied by the iterator (iter < end), false in other case
     */
    public boolean evaluate(T object) {
        iter += inc;
        return (iter < end);
    }

    /**
     * Returns the initial value of the for-loop
     * @return the initial value of the for-loop
     */

    public int getStart() {
        return start;
    }

    /**
     * Returns the increment value of the for-loop
     * @return the increment value of the for-loop
     */
    public int getInc() {
        return inc;
    }

    /**
     * Returns the final value of the for-loop (SUP LIMIT)
     * @return the initial value of the for-loop (SUP LIMIT)
     */
    public int getEnd() {
        return end;
    }

    /**
     * Initializes the internal state of the predicate
     */
    public void init() {
        iter = start - inc;
    }
}
