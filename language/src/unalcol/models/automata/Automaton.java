package unalcol.models.automata;

import java.io.*;

import unalcol.io.*;
import unalcol.language.*;

/**
 * <p>Title: Automaton</p>
 * <p>Description: Abstract Finite State Machine (Automaton)</p>
 * <p>Copyright:  Copyright (c) 2008</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public interface Automaton extends LanguageAcceptator{
    /**
     * Constant used for indicating an acceptation state
     */
    public static final int ACCEPTED = 1;
    /**
     * Constant used for indicating a rejection state
     */
    public static final int REJECTED = 0;
    /**
     * Constant used for indicating that the automaton rejects the input
     */
    public static final int STOP_REJECTED = -2;
    /**
     * Constant used for indicating that the automaton accepts the input
     */
    public static final int STOP_ACCEPTED = -1;

	/**
	 * Simulates the automaton using the given symbols set and stating at the current state.
	 * @param initial_state Initial state of the dfa.
	 * @param input Set of symbols that will be used for simulating the automaton.
	 * @return Number of symbols consumed by the automaton.
	 */
	public default LanguageConsumed simulate(ShortTermMemoryReader<?> input) throws IOException{
		int s;
		int c = 0;
		s = input.read();
		int state=state();
		int new_state = simulate(state, s);
		while( s != -1 && new_state>0 ){
			state = new_state;
			c++;
			s = input.read();
			new_state = simulate(state, s);
		}
		if( s!=-1 ) input.back();
		switch( new_state ){
			case LanguageConsumed.ACCEPTED_BUT_WRONG_CONTINUATION_SYMBOL:
			case LanguageConsumed.NOT_ACCEPTED:
				return new LanguageConsumed(new_state, c);
			default:
				return new LanguageConsumed(state, c);
		}
    }

    /**
     * Simulates the behavior of the automaton (according to the current state) for the given input symbol.
     * @param s Input symbol for simulating the automaton.
     * @return true If there is a transition for the given symbol from the actual state, false otherwise
     */
    public int simulate( int state, int s );

    /**
     * Sets the current state of the automaton
     * @param state State of the automaton
     */
    public void setState( int state );
    
    public int state();

    public default void reset(){ setState(0); }
}