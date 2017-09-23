package unalcol.language.symbol;

import unalcol.types.collection.vector.*;
import unalcol.sort.*;
import unalcol.language.util.*;
import unalcol.language.symbol.util.*;

/**
 * <p>Title: SymbolSet</p>
 *
 * <p>Description: Abstract set of symbols</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public abstract class SymbolSet {

    /**
     * The universal symbol set (any symbol set into a matcher is a subset of the symbol code).
     */
    protected SymbolCode code;

    /**
     * Sorted vector of intervals that is equivalent to the symbol set.
     */
    protected Vector<Interval> intervals = null;

    /**
     * Constant used for identifying the Any symbol set
     */
    public static final int ANY = 1;

    /**
     * Constant used for identifying the Alpha symbol set
     */
    public static final int ALPHA = 2;

    /**
     * Constant used for identifying the Digit symbol set
     */
    public static final int DIGIT = 3;

    /**
     * Constant used for identifying the SingleSymbol
     */
    public static final int SINGLETON = 4;

    /**
     * Constant used for identifying the SymbolRank symbol set
     */
    public static final int RANK = 5;

    /**
     * Constant used for identifying the Complement symbol set
     */
    public static final int COMPLEMENT = 6;

    /**
     * Searching mechanism of disjoint intervals
     */
    protected static SortedVectorSearch<Interval> search = new SortedVectorSearch<Interval>();
    /**
     * Ordering mechanism of disjoint intervals
     */
    protected static DisjointIntervalOrder order = new DisjointIntervalOrder();

    /**
     * Determines if the set includes the given symbol
     * @param s symbol to analize
     * @return <i>true</i> if the set includes the symbol, <i>false</i> otherwise.
     */
    public boolean includes(int s) {
        Vector<Interval> intervals = getIntervals();
        int left = search.findLeft(intervals, new Interval(s), order);
        if( left == -1 ){
            return intervals.get(0).contains(s);
        }else{
            if (left < intervals.size() - 1 && intervals.get(left+1).contains(s) ){
                return true;
            }else{
                return intervals.get(left).contains(s);
            }
        }
    }

    /**
     * Determines if the set includes the given interval symbol
     * @param s symbol interval to analize
     * @return <i>true</i> if the set includes the symbol interval, <i>false</i> otherwise.
     */
    public boolean includes(Interval s) {
        Vector<Interval> intervals = getIntervals();
        int left = search.findLeft(intervals, s, order);
        if( left == -1 ){
            return intervals.get(0).contains(s);
        }else{
            if (left < intervals.size() - 1 && intervals.get(left+1).contains(s) ){
                return true;
            }else{
                return intervals.get(left).contains(s);
            }
        }
    }

    /**
     * Creates a symbol set over the given symbol code
     * @param code Symbol Code
     */
    public SymbolSet(SymbolCode code) {
        this.code = code;
    }

    /**
     * Retunrns a sorted set of intervals that is equivalent to the symbol set.
     * @return Sorted vector of intervals equivalent to the symbol set.
     */
    public Vector<Interval> getIntervals() {
        return intervals;
    }

    /**
     * Creates a SymbolRank set that is equivalent to the symbol set
     * @return A SymboRank, i.e., a set in the form of a sorted vector of disjoint intervals
     * that is equivalent to the symbol set
     */
    public SymbolRank convert() {
        return new SymbolRank(code, getIntervals());
    }

    /**
     * Calcultes the intersection symbol set.
     * @param second SymbolSet being intersected with the symbol set.
     * @return SymbolSet that represents the intersection of the two symbol sets.
     */
    public SymbolSet intersection(SymbolSet second) {
        SymbolRank first = convert();
        return first.intersection(second);
    }

    /**
     * Calcultes the union symbol set.
     * @param second SymbolSet being joined with the symbol set.
     * @return SymbolSet that represents the union of the two symbol sets.
     */
    public SymbolSet union(SymbolSet second) {
        SymbolRank first = convert();
        return first.union(second);
    }

    /**
     * Returns the universal symbol set (a SymbolCode object)
     * @return Symbol Code
     */
    public SymbolCode getCode() {
        return code;
    }

    /**
     * Returns the set type, i.e. some one of the predefined constant like ALPHA.
     * @return Type of the symbol set.
     */
    public abstract int type();


    public String toString(){
        return getIntervals().toString();
    }
}
