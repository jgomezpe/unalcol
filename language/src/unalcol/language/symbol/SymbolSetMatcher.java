package unalcol.language.symbol;

import unalcol.types.collection.vector.*;
import unalcol.language.util.*;
import unalcol.language.symbol.*;
import unalcol.language.symbol.util.*;

/**
 * <p>Title: SymbolSetMatcher</p>
 *
 * <p>Description: Maintains a family of (non-disjoint) set of symbols in order
 * to match symbols (to determine if a symbol belongs to a set) against them.</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class SymbolSetMatcher {
    /**
     * Set of disjoint intervals if the matcher was defined by such disjoint intervals.
     * It is null if not disjoint intervals defined the matcher.
     */
    protected Vector<Interval> intervals = null;
    /**
     * Family of Set of symbols used for the matcher.
     */
    protected Vector<SymbolSet> sets = null;

    /**
     * Creates a symbol set matcher using the provided symbols sets.
     * @param sets Family of Set of symbols used for the matcher.
     */
    public SymbolSetMatcher(Vector<SymbolSet> sets) {
        this.sets = sets;
        int n = this.sets.size();
        for (int i = 0; i < n; i++) {
            SymbolSet set = this.sets.get(i);
            set = Complement.remove_double_complement(set);
            this.sets.set(i, set);
        }
    }

    /**
     * Creates a symbol set matcher using the provided disjoint intervals of symbols.
     * @param intervals Set of disjoint intervals if the matcher was defined by such disjoint intervals.
     */
    public SymbolSetMatcher(SymbolCode code, Vector<Interval> intervals) {
        sets = new Vector<SymbolSet>();
        for (int i = 0; i < intervals.size(); i++) {
            Vector<Interval> rank = new Vector<Interval>();
            rank.add(intervals.get(i));
            sets.add(new SymbolRank(code, rank));
        }
        this.intervals = intervals;
    }

    /**
     * Creates a symbol set matcher using the provided disjoint intervals of symbols.
     * @param intervals Set of disjoint intervals if the matcher was defined by such disjoint intervals.
     * @param any A symbol set used for representing the universal set (symbolcode as symbol set).
     */
    public SymbolSetMatcher(SymbolCode code, Vector<Interval> intervals,
                            SymbolSet any) {
        this(code, intervals);

        if (any != null) {
            sets.add(any);
        }
    }

    /**
     * Creates a matcher with no associated symbol sets
     */
    public SymbolSetMatcher() {
        this.sets = new Vector<SymbolSet>();
    }

    /**
     * Determines the Subfamily of symbol sets that matches the given symbol, i.e., the
     * subfamily of symbol sets that includes the symbol (<i>X</i> belongs to match(<i>s</i>)
     * iff <i>s</i> belongs to <i>X</i>.
     * @param s Symbol to be matched).
     * @return Subfamily of symbol sets that matches the given symbol.
     */
    public Vector<SymbolSet> match(int s) {
        Vector<SymbolSet> sub_set = new Vector<SymbolSet>();
        if (intervals != null) {
            Interval is = new Interval(s);
            int left = SymbolSet.search.findLeft(intervals, is, SymbolSet.order);
            if (left >= 0) {
                intervals.get(left).contains(s);
                sub_set.add(sets.get(left));
            }
            // ANY SYMBOL
            if (intervals.size() != sets.size()) {
                sub_set.add(sets.get(sets.size() - 1));
            }
        } else {
            SymbolSet set;
            for (int i = 0; i < sets.size(); i++) {
                set = sets.get(i);
                if (set.includes(s)) {
                    sub_set.add(set);
                }
            }
        }
        return sub_set;
    }

    /**
     * Determines the Subfamily of symbol sets that matches the given interval symbol, i.e., the
     * subfamily of symbol sets that includes the interval symbol
     * ( <i>X</i> belongs to match(<i>s</i>) iff <i>s</i> falls into <i>X</i>).
     * @param s Symbol to be matched.
     * @return Subfamily of symbol sets that matches the given symbol.
     */
    public Vector<SymbolSet> match(Interval s) {
        Vector<SymbolSet> sub_set = new Vector<SymbolSet>();
        if (intervals != null) {
            int left = SymbolSet.search.findLeft(intervals, s, SymbolSet.order);
            if (left >= 0) {
                intervals.get(left).contains(s);
                sub_set.add(sets.get(left));
            }
            // ANY SYMBOL
            if (intervals.size() != sets.size()) {
                sub_set.add(sets.get(sets.size() - 1));
            }
        } else {
            SymbolSet set;
            for (int i = 0; i < sets.size(); i++) {
                set = sets.get(i);
                if (set.includes(s)) {
                    sub_set.add(set);
                }
            }
        }
        return sub_set;
    }

    /**
     * Determines if there is some symbol set that matches the symbol.
     * @param s Symbol to match
     * @return <i>|match(s)| > 0</i>.
     */
    public boolean matches(int s) {
        return (match(s).size() > 0);
    }

    /**
     * Determines if there is some symbol set that matches the interval symbol.
     * @param s Interval symbol to match.
     * @return <i>|match(s)| > 0</i>.
     */
    public boolean matches(Interval s) {
        return (match(s).size() > 0);
    }

    /**
     * Returns the symbol set having the given index.
     * @param index Index of the symbol set to be returned.
     * @return Symbol set having the given index.
     */
    public SymbolSet get(int index) {
        return sets.get(index);
    }

    /**
     * Returns the index associated to the given symbol set.
     * @param set Symbol Set to be analized.
     * @return Index associated to the given symbol set.
     */
    public int getIndex(SymbolSet set) {
        int i = 0;
        while (i < sets.size() && set != sets.get(i)) {
            i++;
        }
        return i;
    }

    /**
     * Returns the number of symbols sets defining the matcher.
     * @return Number of symbols sets defining the matcher.
     */
    public int size() {
        return sets.size();
    }

    /**
     * Determines if the matcher is defined by disjoint intervals.
     * @return <i>true</i> if the matcher is defined by disjoint intervals, <i>false</i> otherwise.
     */
    public boolean disjoint_sets() {
        return intervals != null;
    }


    public Vector<SymbolSet> matchedSets(){ return sets; }
}
