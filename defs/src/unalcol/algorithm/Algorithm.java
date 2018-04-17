package unalcol.algorithm;

import unalcol.Thing;
import unalcol.math.function.Function;
import unalcol.services.Service;
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

public abstract class Algorithm<I, O> extends Thing implements Function<I,O>{
    /**
     * Adds the output of the algorithm to the tracers
     */
    public void addToTrace() throws Exception{ Service.run(Tracer.name, this, output()); }
    
    /**
     * Flag used for determining if the function was stopped or not
     */
    protected boolean continueFlag = true;

    /**
     * Stars the possibility of computing the function
     */
    public void start(){ continueFlag = true; };

    
    /**
     * Stops the function computation
     */
    public void stop(){ continueFlag = false; }

    /**
     * Determines if the function was stopped or not
     * @return true if the function was stopped, false otherwise
     */
    public boolean running(){ return continueFlag; }    
}