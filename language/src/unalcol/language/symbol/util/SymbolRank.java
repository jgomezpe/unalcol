package unalcol.language.symbol.util;

import unalcol.types.collection.vector.*;
import unalcol.language.util.*;
import unalcol.language.symbol.*;

/**
 * <p>Title: SymbolRank</p>
 *
 * <p>Description: Symbol Set defined by one or more intervals.</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class SymbolRank extends SymbolSet {

    /**
     * Creates an empty symbol rank set over the given symbol code
     * @param code Symbol Code
     */
    public SymbolRank(SymbolCode code) {
        super(code);
        intervals = new Vector<Interval>();
    }

    /**
     * Creates a symbol rank set over the given symbol code using the provided interval [inf, sup)
     * @param code Symbol Code
     * @param inf min limit of the interval
     * @param sup sup limit of the interval
     */
    public SymbolRank(SymbolCode code, int inf, int sup) {
        super(code);
        intervals = new Vector<Interval>();
        if (inf < sup) {
            intervals.add(new Interval(inf, sup));
        }
    }

    /**
     * Creates a symbol rank set over the given symbol code using the provided interval [inf, sup)
     * @param code Symbol Code
     * @param inf min limit of the interval
     * @param sup sup limit of the interval
     * @param negated If the interval is defing the symbol set or its complement. A <i>true</i>
     * value indicates that the symbol set is defined as the complement of the provided interval.
     * A <i>false</i> value indicates that the symbol set will be defnied by the interval.
     */
    public SymbolRank(SymbolCode code, int inf, int sup, boolean negated) {
        super(code);
        intervals = new Vector<Interval>();
        if (inf < sup) {
            if (negated) {
                intervals.add(new Interval(0, inf));
                intervals.add(new Interval(sup, code.size()));
            } else {
                intervals.add(new Interval(inf, sup));
            }
        }
    }

    /**
     * Creates a symbol rank set over the given symbol code using the provided
     * sorted set of intervals (according to the DisjointIntervalOrder)
     * @param code Symbol Code
     * @param intervals Disjoint vector of intervals defining the set.
     */
    public SymbolRank(SymbolCode code, Vector<Interval> intervals) {
        super(code);
        this.intervals = intervals;
    }

    /**
     * Creates a SymbolRank set that is equivalent to the symbol set. Since it is a
     * SymbolRank a reference to itself is returned (not a clone)
     * @return A reference to itself.
     */
    public SymbolRank convert() {
        return this;
    }

    /**
     * Calcultes the union symbol set.
     * @param second SymbolSet being joined with the symbol set.
     * @return SymbolSet that represents the union of the two symbol sets.
     */
    public SymbolSet union(SymbolSet other) {
        Vector<Interval> new_intervals = new Vector<Interval>();
        Vector<Interval> first = intervals;
        Vector<Interval> second = other.convert().intervals;
        int n = first.size();
        int m = second.size();
        int y = 0;
        int x = 0;
        Interval one = null;
        Interval two = null;
        while (x < n && y < m) {
            if (one == null) one = new Interval(first.get(x));
            if (two == null) two = new Interval(second.get(y));
            if (two.sup < one.inf) {
                new_intervals.add(two);
                two = null;
                y++;
            } else if (one.sup < two.inf) {
                new_intervals.add(one);
                one = null;
                x++;
            } else {
                one.inf = Math.min(one.inf, two.inf);
                one.sup = Math.max(one.sup, two.sup);
                y++;
                two = null;
            }
        }

        if (one != null) {
            new_intervals.add(one);
            x++;
        }

        while (x < n) {
            new_intervals.add(new Interval(first.get(x)));
            x++;
        }

        while (y < m) {
            new_intervals.add(new Interval(second.get(y)));
            y++;
        }
        return new SymbolRank(code, new_intervals);
    }

    /**
     * Calcultes the intersection symbol set.
     * @param second SymbolSet being intersected with the symbol set.
     * @return SymbolSet that represents the intersection of the two symbol sets.
     */
    public SymbolSet intersection(SymbolSet other) {
        Vector<Interval> new_intervals = new Vector<Interval>();
        Vector<Interval> first = intervals;
        Vector<Interval> second = other.convert().intervals;
        int n = first.size();
        int m = second.size();
        int y = 0;
        int x = 0;
        while (x < n && y < m) {
            Interval one = first.get(x);
            Interval two = second.get(y);
            if (two.sup <= one.inf) y++;
            else if (one.sup <= two.inf) x++;
            else {
                new_intervals.add(Interval.intersection(one, two));
                if (one.sup < two.sup) x++;
                else if (two.sup < one.sup) y++;
                else {
                    x++;
                    y++;
                }
            }
        }
        return new SymbolRank(code, new_intervals);
    }

    /**
     * Returns the rank set type, i.e. SymboLSet.RANK
     * @return SymboLSet.RANK.
     */
    public int type() {
        return RANK;
    }
}
