package unalcol.models.automata.nondeterministic;
import unalcol.language.symbol.*;
import unalcol.language.util.*;

/**
 * <p>Title:Transition</p>
 *
 * <p>Description: An NFA transition (initial_state, final_state, transition_symbols)</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Transition {
    /**
     * Initial state of the transition
     */
    protected int start;
    /**
     * Final state of the transition
     */
    protected int end;
    /**
     * Transition Symbols (symbols that can produce the transition from the
     * initial state to the final state
     */
    protected SymbolSet symbols;

    /**
     * Creates a lamba transition from the <i>_start</i> state to the <i>_end</i>
     * @param _start Initial state
     * @param _end Final state
     */
    public Transition(int _start, int _end) {
        start = _start;
        end = _end;
        symbols = null;
    }

    /**
     * Creates a transition from the <i>_start</i> state to the <i>_end</i> using
     * any of the symbols of <i>symbols</i>.
     * @param _start Initial state
     * @param _end Final state
     * @param _symbols Transition symbols
     */
    public Transition(int _start, int _end, SymbolSet symbols) {
        start = _start;
        end = _end;
        this.symbols = symbols;
    }


    /**
     * Shifts the transition states in the given amount
     * @param delta Amount of the transition states displacement
     */
    public void shift(int delta) {
        start += delta;
        end += delta;
    }

    /**
     * Determines if the transition is an empty transition, i.e., it does not require
     * symbol to be produced.
     * @return <i>true</i> if the transition is an empty transition, <i>false</i> otherwise.
     */
    public boolean isEmpty() {
        return (symbols == null);
    }

    /**
     * Determines if the transition can be applied using the input symbol <i>symbol</i>.
     * @return <i>true</i> if the transition can be applied using the input symbol
     * <i>symbol</i>, <i>false</i> otherwise.
     */
    public boolean contains(int symbol) {
        return symbols.includes(symbol);
    }

    /**
     * Determines if the transition can be applied using the input symbol interval <i>symbol</i>.
     * @return <i>true</i> if the transition can be applied using the input symbol interval
     * <i>symbol</i>, <i>false</i> otherwise.
     */
    public boolean contains(Interval symbol) {
        return symbols.includes(symbol);
    }

    /**
     * Creates a clone of a transition
     * @return Transition's clone
     */
    public Transition clone() {
        return new Transition(start, end, symbols);
    }

    /**
     * Creates a string representation of the transition.
     * @return String representation of the transition.
     */
    public String toString() {
        return "(" + start + "," + end + "," + symbols.toString() + ")";
    }
}
