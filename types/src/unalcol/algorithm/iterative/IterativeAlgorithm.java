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

public class IterativeAlgorithm<I, O> extends Algorithm<I, O> {

    /**
     * The continuation condition
     */
    protected Predicate<Function<I, O>> condition;
    
    protected StepFunction<I,O> step;

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
    public IterativeAlgorithm( StepFunction<I,O> step, Predicate<Function<I, O>> condition, long delay) {
        this.condition = condition;
        this.step = step;
        this.delay = delay;
    }

    /**
     * Constructor: Creates an iterative algorithm with the given continuation condition
     * @param condition  The algorithm stop condition (the algorithm is executed
     * until the condition is false)
     */
    public IterativeAlgorithm(StepFunction<I,O> step, Predicate<Function<I, O>> condition ) {
        this(step,condition,0);
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
        O output = step.init(input);
        while (condition.evaluate(this) && continueFlag) {
            sleep();
            output = step.apply(output);
        }
        return output;
    }
}