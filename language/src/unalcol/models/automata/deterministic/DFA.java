package unalcol.models.automata.deterministic;

import unalcol.models.automata.Automaton;
import unalcol.language.LanguageConsumed;

/**
 * <p>Title: DFA</p>
 * <p>Description: A Deterministic Finite Automaton</p>
 * <p>Copyright:  Copyright (c) 2008</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class DFA implements Automaton{

    /**
     * Output array. output[i] indicates the output produced by the dfa when being at state i
     */
    protected DFAState[] states = null;

    /**
     * Current state of the DFA (when the dfa is being simulated).
     */
    protected int state = 0;

    public DFA( DFAState[] states ){
        this.states = states;
    }

    /**
     * Sets the current state of the automaton
     * @param state State of the automaton
     */
    public void setState( int state ){
        this.state = state;
        if( state < 0 || state >= states.length ) state = 0;
    }

    public int simulate( int state, int s ){
        Integer targetState = states[state].target(s);
        if( targetState != null ) return (int)targetState;
        return (states[state].label()>0)?LanguageConsumed.ACCEPTED:LanguageConsumed.NOT_ACCEPTED;
    }

	@Override
	public int state(){ return state; }
}