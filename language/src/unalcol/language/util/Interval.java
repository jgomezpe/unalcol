package unalcol.language.util;
import unalcol.types.collection.vector.*;

import unalcol.types.integer.*;
import unalcol.types.integer.array.*;

/**
 * <p>Title: Interval</p>
 *
 * <p>Description: Semi-open interval in the integer space [inf,sup)</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Interval {
    /**
     * Min value of the interval
     */
    public int inf;
    /**
     * Sup value of the interval
     */
    public int sup;
    /**
     * Creates an empty interval
     */
    public Interval() {
        inf = 0;
        sup = 0;
    }

    /**
     * Creates an interval that just includes inf
     * @param inf Min value of the interval
     */
    public Interval(int inf) {
        this.inf = inf;
        this.sup = inf + 1;
    }

    /**
     * Creates an interval [inf, sup)
     * @param inf Min value of the interval
     * @param sup Sup value of the interval
     */
    public Interval(int inf, int sup) {
        this.inf = inf;
        this.sup = sup;
    }

    /**
     * Creates a clone of an interval
     * @param source Interval to be cloned
     */
    public Interval(Interval source) {
        inf = source.inf;
        sup = source.sup;
    }

    /**
     * Determines if the interval contains the given value
     * @param x Value to be analized
     * @return <i>true</i> if <i>x</i> falls into the interval, <i>false</i> otherwise
     */
    public boolean contains(int x) {
        return (inf <= x && x < sup);
    }

    /**
     * Determines if the interval contains the given interval
     * @param x Interval to be analized
     * @return <i>true</i> if <i>x</i> falls into the interval, <i>false</i> otherwise
     */
    public boolean contains(Interval x) {
        return (inf <= x.inf && x.sup <= sup);
    }

    /**
     * Calculates the interval intersection
     * @param two Interval to be intersected
     */
    public void intersection(Interval two) {
        inf = Math.max(inf, two.inf);
        sup = Math.min(sup, two.sup);
    }

    /**
     * Determines if the interval is not empty
     * @return <i>true</i> if the interval is not empty, <i>false</i> otherwise
     */
    public boolean non_empty() {
        return (inf < sup);
    }

    /**
     * Determines if the interval is empty
     * @return <i>true</i> if the interval is empty, <i>false</i> otherwise
     */
    public boolean empty() {
        return (sup <= inf);
    }

    /**
     * Calculates the interval intersection
     * @param one First interval to be intersected
     * @param two Second interval to be intersected
     * @return The intersection of intervals one and two
     */
    public static Interval intersection(Interval one, Interval two) {
        Interval interval = new Interval(one.inf, one.sup);
        interval.intersection(two);
        return interval;
    }

    /**
     * Creates a sorted set of disjoint intervals that are equivalent to the given set of intervals.
     * For example if the rpvided intervals are [2,10), [7,15) and [20, 30) then the reduced intervals
     * are [2,7), [7,10), [10,15) and [20, 30).
     * @param intervals set of intervals that will be reduced to a sorted set of disjoint intervals.
     * @return Sorted Vector of disjoint intervals that is equivalent to the given set of intervals.
     */
    public static Vector<Interval> reduce(Vector<Interval> intervals) {
        int n = intervals.size();
        int[] limits = new int[2 * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            limits[k] = intervals.get(i).inf;
            k++;
            limits[k] = intervals.get(i).sup;
            k++;
        }
        IntArraySort.merge(limits);
        Vector<Interval> new_intervals = new Vector<Interval>();
        int m = 2 * n - 1;
        for (int i = 0; i < m; i++) {
            if (limits[i] < limits[i + 1]) {
                new_intervals.add(new Interval(limits[i], limits[i + 1]));
            }
        }
        return new_intervals;
    }

    /**
     * Creates a string representation of an interval.
     * @return String representation of the interval.
     */
    public String toString() {
        return "[" + inf + "," + sup + ")";
    }
}
