package unalcol.algorithm;
import unalcol.math.function.*;
import unalcol.tracer.Tracer;

/**
 * <p>Abstract Function defined as a Thread</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public abstract class ThreadFunction<S, T> implements Function<S,T>{

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

    /**
     * Adds the given object to the set of tracers defined for that object
     */
    public void addToTrace( Object obj ) {
        Tracer.trace(this, obj);
    }
}