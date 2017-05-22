package unalcol.algorithm;

import unalcol.tracer.Tracer;

/**
 * <p>Abstract version of an algorithm, can be used in a Thread</p>
 * <p>A subclass that implements the Algorithm class can receive the
 * algorithm parameters as arguments of the constructor.</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public abstract class Algorithm<I, O> implements ThreadFunction<I,O>, Runnable {
    /**
     * If the algorithm has finished or not
     */
    protected boolean done = false;

    // Running as thread

    /**
     * Algorithm input
     */
    protected I input = null;

    /**
     * Algorithm output
     */
    protected O output = null;

    /**
     * Initializes the internal state of the algorithm.
     * @param input Input of the algorithm
     */
    public void init(I input) {
        this.input = input;
    }

    /**
     * Gets the input of algorithm
     * @return The input of algorithm
     */
    public I input() {
        return input;
    }

    /**
     * Gets the result generated by the algorithm
     * @return Answer produced by the algorithm
     */
    public O output() {
        return output;
    }

    /**
     * Executes the algorithm on the given input
     */
    public void run() {
        this.continueFlag = true;
        output = this.apply(input);
    }

    /**
     * Adds the output of the algorithm to the tracers
     */
    public void addToTrace() {
        Tracer.trace(this, output() );
    }
    
    /**
     * Flag used for determining if the function was stopped or not
     */
    protected boolean continueFlag = true;

    /**
     * Stops the function computation
     */
    public void stop() {
        continueFlag = false;
    }

    /**
     * Determines if the function was stopped or not
     * @return true if the function was stopped, false otherwise
     */
    public boolean stopped() {
        return continueFlag;
    }    
}