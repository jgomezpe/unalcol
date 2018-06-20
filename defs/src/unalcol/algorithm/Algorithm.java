package unalcol.algorithm;

import unalcol.math.function.Function;
import unalcol.math.function.ThingFunction;
import unalcol.types.object.dynamic.KeyMapDynObj;
import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.types.collection.keymap.KeyMap;

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

public abstract class Algorithm<I, O> extends KeyMapDynObj implements ThingFunction<I,O>, Function<I,O>{
	public Algorithm(){ this( new HTKeyMap<String,Object>()); }
	
    public Algorithm(KeyMap<String, Object> keymap){ super(keymap);	}

	/**
     * Adds the output of the algorithm to the tracers
     */
    public void addToTrace() throws Exception{ trace(output()); }
    
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