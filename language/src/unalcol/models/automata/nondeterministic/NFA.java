package unalcol.models.automata.nondeterministic;
import unalcol.types.collection.array.ArraySearch;
import unalcol.types.collection.vector.*;

import unalcol.*;
import unalcol.clone.*;
import unalcol.models.automata.*;
import unalcol.models.automata.deterministic.*;
import unalcol.language.symbol.*;
import unalcol.language.symbol.util.*;
import unalcol.types.integer.*;
import unalcol.types.object.BinarySearch;
import unalcol.sort.*;
import unalcol.language.util.*;

/**
 * <p>Title: NFA</p>
 *
 * <p>Description: A Non-deterministic finite automaton. Although, a nfa can have
 * more than one initial (final) state, in this implementation a nfa has just
 * one initial state (zero) and one final state. It is possible since empty transitions
 * can be added to/from the set of initial/final states from/to a new initial/final state.</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class NFA implements Automaton{
    /**
     * Automaton transitions
     */
    protected Vector<Transition> transitions = null;
    /**
     * Automaton empty transitions
     */
    protected Vector<Transition> empty_transitions = null;

    /**
     * Final state of the nfa
     */
    protected int final_state = Integer.MIN_VALUE;

    /**
     * The current state of the NFA (when simulated)
     */
    protected Vector<Integer> state = new Vector<Integer>();
    /**
     * A search method for integers (used for set of states).
     */
    protected static ArraySearch<Integer> search = new ArraySearch<Integer>();
    /**
     * An order for integers (used for set of states).
     */
    protected static IntegerOrder order = new IntegerOrder();
    /**
     * A sorted insert method for integers (used for set of states)
     */
    protected static SortedVectorInsert<Integer> insert = new SortedVectorInsert<Integer>();
    /**
     * A search method for transitions.
     */
    protected static ArraySearch<Transition> tSearch = new ArraySearch<Transition>();
    /**
     * An order for transitions.
     */
    protected static TransitionOrder tOrder = new TransitionOrder();

    /**
     * Initializes the nfs using the given set of transitions, initial states and
     * final states. This method produces the nfs with just one initial and one
     * final state. It is possible since empty transitions can be added from (to)
     * the set of initial (final) states to (from) a new initial (final) state.
     * @param transitions Vector ot transitions defining the nfa
     * @param empty_transitions Vector ot empty transitions defining the nfa
     * @param initial_states Vector of initial states
     * @param final_states Vector of final states
     */
    public void init(Vector<Transition> transitions,
                     Vector<Transition> empty_transitions,
                     Vector<Integer> initial_states,
                     Vector<Integer> final_states) {
        this.transitions = transitions;
        this.empty_transitions = empty_transitions;
        Vector<Integer> states = states();
        IntegerOrder order = new IntegerOrder();
        ArraySearch<Integer> search = new ArraySearch<Integer>(states,order);
        int size = states.size();
        int index;
        int init_n = initial_states.size();
        int start = Integer.MIN_VALUE;
        // If there is just one staring state, it will be indexed with zero
        if (init_n == 1) {
            start = initial_states.get(0);
        }
        int final_n = final_states.size();
        int end = Integer.MIN_VALUE;
        // If there is just one ending state, it will be indexed with the last possible index
        if (final_n == 1) {
            end = final_states.get(0);
        }
        int n = transitions.size();
        for (int i = 0; i < n; i++) {
            Transition t = transitions.get(i);
            // An state will be indexed according to its position in the sorted list of state + 1
            // since zero is reserved for the initial state
            if (t.start == start) {
                index = 0;
            } else {
                if (t.start == end) {
                    index = size + 1;
                } else {
                    index = search.find(t.start) + 1;
                }
            }
            t.start = index;
            if (t.end == start) {
                index = 0;
            } else {
                if (t.end == end) {
                    index = size + 1;
                } else {
                    index = search.find(t.end) + 1;
                }
            }
            t.end = index;
        }
        // Setting the final state
        if (final_n > 1) {
            for (int i = 0; i < final_n; i++) {
                int state = final_states.get(i);
                if (state == start) {
                    index = 0;
                } else {
                    index = search.find(state) + 1;
                }
                empty_transitions.add(
                        new Transition(index, end));
            }
        } else {
            final_state = size + 1;
        }

        // Setting the initial state
        if (init_n > 1) {
            for (int i = 0; i < init_n; i++) {
                int state = initial_states.get(i);
                if (state == end) {
                    index = size + 1;
                } else {
                    index = search.find(state) + 1;
                }
                empty_transitions.add(
                        new Transition(0, index));
            }
        }
        // sorting the transitions
        VectorSort<Transition> sort = new VectorSort<Transition>();
        TransitionOrder torder = new TransitionOrder();
        sort.apply(transitions, torder);

    }

    /**
     * Creates an automaton that accepts any symbol (just one).
     */
    protected NFA() {
        accept_all();
    }

    /**
     * Creates an automaton for parsing the given string.
     * @param code Symbol code used by the automaton.
     * @param word String used for creating the automaton.
     */
    public NFA(SymbolCode code, String word) {
        int[] input = code.toInt(word);
        empty_transitions = new Vector<Transition>();
        transitions = new Vector<Transition>();
        final_state = input.length;
        for (int i = 0; i < final_state; i++) {
            int k = 0;
            while (k < i && input[k] != input[i]) {
                k++;
            }
            SymbolSet set = null;
            if (k == i) {
                set = new SingleSymbol(code, input[i]);
            } else {
                set = transitions.get(k).symbols;
            }
            transitions.add(new Transition(i, i + 1, set));
        }
    }

    /**
     * Creates an automaton for parsing any of the given set of symbos.
     * @param set Symbol set that will be parsed by the automaton.
     */
    public NFA(SymbolSet set) {
        empty_transitions = new Vector<Transition>();
        transitions = new Vector<Transition>();
        final_state = 1;
        transitions.add(new Transition(0, 1, set));
    }


    /**
     * Constructor, creates a nfa using the given set of transitions, initial state and final state
     * @param _transitions Vector of transitions defining the nfa
     * @param initial_state initial state of the nfa
     * @param final_state final state of the nfa
     */
    public NFA(Vector<Transition> transitions,
               Vector<Transition> empty_transitions,
               int initial_state, int final_state) {
        Vector<Integer> initial_states = new Vector<Integer>();
        initial_states.add(initial_state);
        Vector<Integer> final_states = new Vector<Integer>();
        final_states.add(final_state);

        init(transitions, empty_transitions, initial_states, final_states);
    }

    /**
     * Constructor, creates a nfa using the given set of transitions, initial states and final states
     * @param _transitions Vector of transitions defining the nfa
     * @param initial_states initial states of the nfa
     * @param final_states final states of the nfa
     */
    public NFA(Vector<Transition> transitions,
               Vector<Transition> empty_transitions,
               Vector<Integer> initial_states, Vector<Integer> final_states) {
        init(transitions, empty_transitions, initial_states, final_states);
    }

    /**
     * Obtains the set of states defining the nfa
     * @return Vector of integers with the states defining the nfa
     */
    protected Vector<Integer> states() {
        IntegerOrder order = new IntegerOrder();
        Vector<Integer> s = new Vector<Integer>();
        SortedVectorInsert<Integer> insert = new SortedVectorInsert<Integer>();
        int n = transitions.size();
        for (int i = 0; i < n; i++) {
            Transition t = transitions.get(i);
            insert.apply(s, t.start, order, false);
            insert.apply(s, t.end, order, false);
        }
        return s;
    }


    /**
     * Shits the states in the given amount
     * @param delta Displacement being applied to the states of the nfa
     */
    protected void shift(int delta) {
        int size = transitions.size();
        for (int i = 0; i < size; i++) {
            transitions.get(i).shift(delta);
        }
        size = empty_transitions.size();
        for (int i = 0; i < size; i++) {
            empty_transitions.get(i).shift(delta);
        }
        final_state += delta;
    }

    /**
     * Creates a clone of the nfa
     * @return A clone of the nfa
     */
    public Object clone() {
        try{
            NFA nfa = new NFA();
            nfa.final_state = final_state;
            nfa.transitions = (Vector<Transition>) Unalcol.cloner().clone(
                    transitions);
            nfa.empty_transitions = (Vector<Transition>) Unalcol.cloner().clone(
                    empty_transitions);
            return nfa;
        }catch( Exception e ){
            return null;
        }
    }

    /**
     * Converts the automaton to one that accepts any symbol (just one)
     */
    public void accept_all() {
        transitions = new Vector<Transition>();
        empty_transitions = new Vector<Transition>();
        empty_transitions.add(new Transition(0, 1));
        final_state = 1;
    }

    /**
     * Concats the given automaton <i>second</i> to the end of the automaton. Since
     * an nfa using this implementation has one initial state an one final state,
     * the result of the concat operation is to add an empty transition from the
     * final state of <i>this</i> nfa to the initial state of a shifted
     * (1 + final_state of <i>this</i>) clone of <i>second</i>. This operation
     * is represented using a dot  <b>.</b> symbol.
     * @param second NFA to be concatenated to the automaton.
     */
    public void concat(NFA second) {
        second = (NFA) second.clone();
        second.shift(final_state + 1);
        empty_transitions.add(new Transition(final_state, final_state + 1));
        int m = second.transitions.size();
        for (int i = 0; i < m; i++) {
            transitions.add(second.transitions.get(i));
        }
        m = second.empty_transitions.size();
        for (int i = 0; i < m; i++) {
            empty_transitions.add(second.empty_transitions.get(i));
        }

        final_state = second.final_state;
    }

    /**
     * Concats the given automaton <i>second</i> in an optional manner to the automaton. Since
     * an nfa using this implementation has one initial state an one final state,
     * this operation shifts <i>this</i> nfa in 1 and shifts nfa <i>second</i> in
     * (2 + final_state of <i>this</i>), then adds two new states <i>new_init</i> and
     * <i>new_fin</i> and finally adds empty transitions from the <i>new_ini</i> state
     * to the initial states of nfa <i>this</i> and nfa <i>second</i> and from the
     * final states of nfa <i>this</i> and nfa <i>second</i> to state <i>new_fin</i>.
     * This operation is represented using a vertical bar <b>|</b> symbol.
     * @param second NFA to be concatenated in an optional manner to the automaton.
     */
    public void option(NFA second) {
        second = (NFA) second.clone();
        shift(1);
        second.shift(final_state + 1);
        empty_transitions.add(0, new Transition(0, 1));
        empty_transitions.add(1, new Transition(0, final_state + 1));
        empty_transitions.add(new Transition(final_state,
                                             second.final_state + 1));
        int m = second.transitions.size();
        for (int i = 0; i < m; i++) {
            transitions.add(second.transitions.get(i));
        }
        m = second.empty_transitions.size();
        for (int i = 0; i < m; i++) {
            empty_transitions.add(second.empty_transitions.get(i));
        }
        empty_transitions.add(new Transition(second.final_state,
                                             second.final_state + 1));
        final_state = second.final_state + 1;
        System.out.println(empty_transitions);
        System.out.println("FS=" + final_state);
    }

    /**
     * Converts the automaton to its star of Kleene version. This operation is
     * represented using the * symbol.
     */
    public void KleeneStar() {
        shift(1);
        empty_transitions.add(0, new Transition(0, final_state + 1));
        empty_transitions.add(0, new Transition(0, 1));
        empty_transitions.add(new Transition(final_state, final_state + 1));
        empty_transitions.add(new Transition(final_state+1, 0));
        final_state++;
    }

    /**
     * Converts the automaton to its plus of Kleene version, i.e., <i>this</i> | <i>this</i>*.
     * This operation is represented using the + symbol.
     */
    public void KleenePlus() {
        NFA second = (NFA)this.clone();
        second.KleeneStar();
        concat(second);
    }

    /**
     * Transform the NFA automaton to this[inf,sup]. If sup is negative then the returned
     * automaton is this[inf,oo].
     * @param one NFA to be ranked
     * @param inf minimal repetitions of automaton.
     * @param sup maximal number of repititions of automaton.
     */
    public void rank(int inf, int sup) {
        if (inf == 0 && sup < 0) {
            KleeneStar();
        } else {
            if (inf == 1 && sup < 0) {
                KleenePlus();
            } else {
                NFA clone = (NFA) clone();
                accept_all();
                for (int i = 0; i < inf; i++) {
                    this.concat(clone);
                }
                if (sup < 0) {
                    this.concat(KleeneStar(clone));
                } else {
                    for (int i = inf; i < sup; i++) {
                        this.concat(option(new NFA(), clone));
                    }
                }
            }
        }
    }

    /**
     * Concats nfa <i>two</i> to the end of nfa <i>one</i>. Since
     * an nfa using this implementation has one initial state an one final state,
     * the result of the concat operation is to add an empty transition from the
     * final state of <i>one</i> nfa to the initial state of a shifted
     * (1 + final_state of <i>one</i>) clone of <i>two</i>. This operation
     * is represented using a dot  <b>.</b> symbol.
     * @param one First NFA of the concatenation.
     * @param two Second NFA of the concatenation.
     * @return <i>one</i> . <i>two</i>
     */
    public static NFA concat(NFA one, NFA two) {
        one = (NFA)one.clone();
        one.concat(two);
        return one;
    }

    /**
     * Concats nfa <i>one</i> in an optional manner to automaton <i>two</i>. Since
     * an nfa using this implementation has one initial state an one final state,
     * this operation clones the automata, shifts <i>one</i> nfa in 1 and nfa <i>two</i> in
     * (2 + final_state of <i>one</i>). then adds two new states <i>new_init</i> and
     * <i>new_fin</i> and finally adds empty transitions from the <i>new_ini</i> state
     * to the initial states of clone nfa <i>this</i> and nfa <i>second</i> and from the
     * final states of clone nfa <i>one</i> and nfa <i>two</i> to state <i>new_fin</i>.
     * This operation is represented using a vertical bar <b>|</b> symbol.
     * @param one First NFA in the concatenation.
     * @param two Second NFA in the concatenation.
     * @return <i>one</i> | <i>two</i>
     */
    public static NFA option(NFA one, NFA two) {
        one = (NFA) one.clone();
        one.option(two);
        return one;
    }

    /**
     * Creates a star of Kleene version of the <i>one</i> nfa. This operation is
     * represented using the * symbol.
     * @param one NFA
     * @return <i>one</i>*
     */
    public static NFA KleeneStar(NFA one) {
        one = (NFA) one.clone();
        one.KleeneStar();
        return one;
    }

    /**
     * Creates a plus of Kleene version of the <i>one</i> nfa. This operation is
     * represented using the + symbol.
     * @param one NFA
     * @return <i>one</i>+ = <i>one</i> | <i>one</i>*
     */
    public static NFA KleenePlus(NFA one) {
        one = (NFA) one.clone();
        one.KleenePlus();
        return one;
    }

    /**
     * Computes the NFA automaton one[inf,sup]. If sup is negative then the returned
     * automaton is one[inf,oo].
     * @param one NFA to be ranked
     * @param inf minimal repetitions of automaton one.
     * @param sup maximal number of repititions of automaton one.
     * @return The NFA automaton one[inf,sup]. If sup is negative then the returned
     * automaton is one[inf,oo].
     */
    public static NFA rank(NFA one, int inf, int sup) {
        one = (NFA) one.clone();
        one.rank(inf, sup);
        return one;
    }

    /**
     * Returns the position of the firs transition (in a sorted set of transitions {@link TransitionOrder})
     * with the start state given.
     * @param transitions Set of transitions to be analized.
     * @param start Start state of the transition being located.
     * @return Position of the first transition starting at the given state.
     */
    public static int locate(Vector<Transition> transitions, int start) {

        Transition tStart = new Transition(start, -1);
        return tSearch.findLeft(transitions, tStart, tOrder) + 1;

        /*
                 int s = transitions.size();
                 int j = 0;
                 while(j < s && transitions.get(j).start < start) {
            j++;
                 }
                return j;
         */
    }

    /**
     * Determines the set of states that can be reached from the <i>set</i> of states
     * through empty transitions.
     * it is represented using the word <i>cl</i>.
     * @param set Set of states being closed
     * @return Set of states reachable from the <i>set</i> of states given.
     */
    protected Vector<Integer> empty_closure(Vector<Integer> set) {
//        System.out.print( "cl(" + set + ") = " );
        Vector<Integer> closed_set = new Vector<Integer>();
        Vector<Integer> queue = new Vector<Integer>();
        int n = set.size();
        for (int i = 0; i < n; i++) {
            queue.add(new Integer(set.get(i)));
        }
        int s = empty_transitions.size();
        int head = 0;
        while (head < n) {
            if (!search.contains(closed_set, queue.get(head), order)) {
                insert.apply(closed_set, queue.get(head), order, false);
                int j = locate(empty_transitions, queue.get(head));
                while (j < s &&
                       empty_transitions.get(j).start == queue.get(head)) {
                    if (!search.contains(closed_set,
                                         empty_transitions.get(j).end, order)) {
                        queue.add(empty_transitions.get(j).end);
                        n++;
                    }
                    j++;
                }
            }
            head++;
        }
//        System.out.println( closed_set );
        return closed_set;
    }

    /**
     * Determines the set of states reachable from the <i>set</i> of states given
     * through transitions using symbol <i>symbol</i>. This method does not takes
     * into account empty transitions at the initial state (it is supposed that
     * such empty transtions were taken into consideration previous a calling of this method),
     * but takes into consideration empty transitions at the ending set of state
     * (as can be noticed the final set of states satisfied the supposition).
     * This method is represented as <i>cl(symbol)</i>.
     * @param set Set of states being closed under symbol <i>symbol</i>.
     * @param symbol Symbol being used for determining the transition
     * @return Set of states reachable from the <i>set</i> of states given through
     * transitions using symbol <i>symbol</i>.
     */
    protected Vector<Integer> closure(Vector<Integer> set, int symbol) {
//        System.out.print( "cl(" + set + "," + (char)symbol + ") = " );
        Vector<Integer> closed_set = new Vector<Integer>();
        int n = set.size();
        int s = transitions.size();
        int i = 0;
        while (i < n) {
            int j = locate(transitions, set.get(i));
            while (j < s && transitions.get(j).start == set.get(i)) {
                if (transitions.get(j).contains(symbol)) {
                    insert.apply(closed_set, transitions.get(j).end, order, false);
                }
                j++;
            }
            i++;
        }
        return empty_closure(closed_set);
    }

    /**
     * Determines the set of states reachable from the <i>set</i> of states given
     * through transitions using symbol <i>symbol</i>. This method does not takes
     * into account empty transitions at the initial state (it is supposed that
     * such empty transtions were taken into consideration previous a calling of this method),
     * but takes into consideration empty transitions at the ending set of state
     * (as can be noticed the final set of states satisfied the supposition).
     * This method is represented as <i>cl(symbol)</i> and is used by the transform method.
     * @param set Set of states being closed under symbol <i>symbol</i>.
     * @param symbol Symbols set being used for determining the transition.
     * @return Set of states reachable from the <i>set</i> of states given through
     * transitions using symbol <i>symbol</i>.
     */
    protected Vector<Integer> closure(Vector<Integer> set, Interval symbol) {
        Vector<Integer> closed_set = new Vector<Integer>();
        int n = set.size();
        int s = transitions.size();
        int i = 0;
//        System.out.println("NFA::symbol"+symbol);
        while (i < n) {
//            System.out.println("NFA::set("+i+")="+set.get(i));
            int j = locate(transitions, set.get(i));
            while (j < s && transitions.get(j).start == set.get(i)) {
//                System.out.println( "NFA::transitions("+ j + "):" +transitions.get(j) );
                if (transitions.get(j).contains(symbol)) {
//                    System.out.println( "Entering here..." );
                    insert.apply(closed_set, transitions.get(j).end, order, false);
                }
                j++;
            }
            i++;
        }
        return empty_closure(closed_set);
    }

    /**
     * Computes the disjoint family of symbol intervals used by the nfa. This method
     * is used by the transform method.
     * @return disjoint family of symbol intervals used by the nfa.
     */
    public Vector<Interval> symbols() {
        Vector<Interval> s = new Vector<Interval>();
        int n = transitions.size();
        for (int i = 0; i < n; i++) {
            Transition t = transitions.get(i);
            if (!t.isEmpty()) {
                Vector<Interval> t_intervals = t.symbols.getIntervals();
                for (int k = 0; k < t_intervals.size(); k++) {
                    s.add(t_intervals.get(k));
                }
            }
        }
        return Interval.reduce(s);
    }

    /**
     * Determines if two set of states (sorted integers) are the same or not. This
     * method is used by the transform method.
     * @param set1 First set of states to be compared
     * @param set2 Second set of statess to be compared
     * @return <i>true</i> if the set of states are the same, <i>false</i> otherwise.
     */
    protected boolean equals(Vector<Integer> set1, Vector<Integer> set2) {
        int n = set1.size();
        if (n == set2.size()) {
            int i = 0;
            while (i < n && set1.get(i).equals(set2.get(i))) {
                i++;
            }
            return (i == n);
        }
        return false;
    }

    /**
     * Inserts, without repetitions, a set of states in a family of set of states.
     * Notice, that the set of states is added if it is not in the family. This
     * method is used by the transform method.
     * @param states Family of set of states.
     * @param state Set of states to be added.
     * @return The position of the set of states in the family after being added.
     * If the set of states is not in the family the position is the last of the
     * family, otherwise the actual set of states position is returned.
     */
    protected int insert(Vector<Vector<Integer>> states, Vector<Integer> state) {
        int i = 0;
        while (i < states.size() && !equals(states.get(i), state)) {
            i++;
        }
        if (i == states.size()) {
            states.add(state);
        }
        return i;
    }


    /**
     * Transfors the nfa to an equivalent deterministic finite automaton
     * @return A DFA that is equivalent to the NFA.
     */
    public DFA transform() {
        Vector<int[]> new_transitions = new Vector<int[]>();
        Vector<Integer> acceptation_states = new Vector<Integer>();
        Vector<Interval> symbols = symbols();
//        System.out.println("THE SYMBOLS:"+symbols);
        int m = symbols.size();
        Vector<Vector<Integer>> states = new Vector<Vector<Integer>>();
        Vector<Integer> set = new Vector<Integer>();
        set.add(0);
        IntegerOrder order = new IntegerOrder();
        ArraySearch<Integer> search = new ArraySearch<Integer>(set,order);

        set = empty_closure(set);
//        System.out.println( "Initial state =" + set );
        states.add(set);
//        System.out.println( "Initial state =" + states );
        int counter = 1;
        int i = 0;
        while (i < counter) {
            set = states.get(i);
//            System.out.print( "STATE("+i+")="+set);
            if (search.find(final_state) >= 0) {
                acceptation_states.add(i);
                //                System.out.print( "  is an acceptation state");
            }
//            System.out.println();
            for (int c = 0; c < m; c++) {
//                System.out.println("NFA::"+c+"="+symbols.get(c));
                Vector<Integer> adjacent_state = closure(set, symbols.get(c));
                if (adjacent_state.size() > 0) {
                    int j = insert(states, adjacent_state);
//                    System.out.println( "("+i+","+j+","+c+")");
                    new_transitions.add(new int[] {i, j, c});
                    if (j == counter) {
                        counter++;
                    } else {
                        adjacent_state.clear();
                    }
                }
            }
            i++;
        }

        // setting the acceptation states
        int[] output = new int[counter];
        for (i = 0; i < counter; i++) {
            output[i] = 0;
        }
        for (i = 0; i < acceptation_states.size(); i++) {
            output[acceptation_states.get(i)] = 1;
        }

        SymbolCode code = this.transitions.get(0).symbols.getCode();
        SymbolSet any = new Any(code);
        SymbolSetMatcher matcher = new SymbolSetMatcher(code, symbols, any);

        DFAState[] dfaStates = new DFAState[counter];
        for (i = 0; i < counter; i++) {
            dfaStates[i] = new DFAState(output[i] == 1);
        }
        for (i = 0; i < new_transitions.size(); i++) {
            int[] tuple = new_transitions.get(i);
            dfaStates[tuple[0]].addTransition(symbols.get(tuple[2]), tuple[1]);
        }
        return new DFA( dfaStates);
    }

    /**
     * Determines if the automaton accept the last provided input (after a simulation) or not
     * @return <i>true</i> if the automaton accepted the last provided input, <i>false</i> otherwise
     */
    public boolean accepted(){
    	search.set(state);
    	search.set(new IntegerOrder());
        return search.contains(final_state);
    }

    /**
     * Sets the current state of the automaton
     * @param state State of the automaton
     */
    public void setState(int state) {
        this.state.clear();
        this.state.add(state);
        this.state = this.empty_closure(this.state);
    }

    /**
     * Simulates the behaviour of the automaton (according to the current state) for the given input symbol.
     * @param s Input symbol for simulating the automaton.
     */
    public boolean simulate(int s) {
        Vector<Integer> new_state = closure(state, s);
        if (new_state.size() != 0) {
            state = new_state;
            return true;
        }
        return false;
    }
}
