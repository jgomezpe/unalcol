package unalcol.algorithm;
import unalcol.math.function.*;
/**
 * <p>Abstract Function defined as a Thread</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface ThreadFunction<S, T> extends Function<S,T>{

    /**
     * Stops the function computation
     */
    public void stop();
    /**
     * Determines if the function was stopped or not
     * @return true if the function was stopped, false otherwise
     */
    public boolean stopped();
}