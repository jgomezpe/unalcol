package unalcol.models.automata.deterministic;

import unalcol.types.collection.keymap.HTKeyMap;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class DFAState {
    protected int label;
    protected HTKeyMap<Integer,Integer> transitions;

    public DFAState( int label ) {
        this.label = label;
        this.transitions = new HTKeyMap<Integer,Integer>();
    }

    public DFAState( int label, HTKeyMap<Integer,Integer> transitions ) {
        this.label = label;
        this.transitions = transitions;
    }

    public void addTransition( int symbol, int targetState ){
        transitions.set(symbol, targetState);
    }

    public Integer target( Integer symbol ){
        return transitions.get(symbol);
    }

    public int label(){ return this.label; }

    public HTKeyMap<Integer,Integer> getTransitions(){
        return transitions;
    }
}
