package unalcol.algorithm.iterative;

import unalcol.math.function.Function;
import unalcol.algorithm.*;
import unalcol.math.logic.Predicate;

/**
 * <p>Abstract version of an iterative algorithm.</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public abstract class IterativeAlgorithm<I, O> extends Algorithm<I, O> {

    /**
     * The continuation condition
     */
    protected Predicate<Function<I, O>> condition = null;

    /**
     * Delay between each iteration (in millisecs)
     */
    private long delay = 0;

    /**
     * Constructor: Creates an iterative algorithm with the given continuation condition
     * @param condition  The algorithm stop condition (the algorithm is executed
     * until the condition is false)
     * @param delay Elapsed time between iterations (millisecs)
     */
    public IterativeAlgorithm(Predicate<Function<I, O>> condition, long delay) {
        this.condition = condition;
        this.delay = delay;
    }

    /**
     * Constructor: Creates an iterative algorithm with the given continuation condition
     * @param condition  The algorithm stop condition (the algorithm is executed
     * until the condition is false)
     */
    public IterativeAlgorithm(Predicate<Function<I, O>> condition) {
        this.condition = condition;
    }

    /**
     * Constructor: Creates an iterative algorithm without the continuation condition
     * useful for running the algorithm iteration by iteration.
     */
    public IterativeAlgorithm() {}

    /**
     * Inits the algorithm. Useful to initialize internal variables
     */
    public void init() {
        if (condition != null) {
            condition.init();
        }
        continueFlag = true;
    }

    /**
     * Determines the output produced by the iterative algorithm if no iterations are performed
     * @param input The algorithm input
     * @return O The output produced by the iterative algorithm if no iterations are performed
     */
    public abstract O nonIterOutput(I input);

    /**
     * An algorithm's iteration
     * @param k The current algorithm's iteration
     * @param input Current input
     * @param output Current output
     * @return The output of the algorithm after k iterations
     */
    public abstract O iteration(int k, I input, O output);

    /**
     * Updates the input according to the previous input and output
     * @param input Current input
     * @param output Current output
     * @return New input used by the iterative algorithm
     */
    protected I update(I input, O output) {
        return input;
    }

    private void sleep(){
        if (delay > 0) {
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
    }
    
    /**
     * Executes the iterative algorithm. Keeps a vector with the results of each
     * iteration of the algorithm
     * @param input Input of the algorithm
     * @return Output produced by the algorithm
     */
    @Override
    public O apply(I input) {
        output = nonIterOutput(input);
        if (condition != null) {
            int k = 0;
            while (condition.evaluate(this) && continueFlag) {
                sleep();
                output = iteration(k, input, output);
                input = update(input, output);
                k++;
            }
        }
        return output;
    }

    /**
     * Returns the current iterative algorithm condition
     * @return The current iterative algorithm condition
     */
    public Predicate<Function<I, O>> getCondition() {
        return condition;
    }

    /**
     * Sets the iterative algorithm condition
     * @param condition  The algorithm condition (the algorithms is executed
     * until the condition is false)
     */
    public void setCondition(Predicate<Function<I, O>> condition) {
        this.condition = condition;
    }
}