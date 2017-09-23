package unalcol.language.symbol.util;
import unalcol.types.collection.vector.*;
import unalcol.language.util.*;
import unalcol.language.symbol.*;

/**
 * <p>Title: Complement</p>
 *
 * <p>Description: The complement set of a given symbol set.</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Complement extends SymbolSet {
    /**
     * Symbol set being complemented
     */
    protected SymbolSet set;

    /**
     * Removes double complementation operations ¬¬set = set
     * @param set SymbolSet to be anilized
     * @return SymbolSet witouhth double complementations
     */
    public static SymbolSet remove_double_complement(SymbolSet set) {
        while (set instanceof Complement &&
               ((Complement) set).set instanceof Complement) {
            set = ((Complement) ((Complement) set).set).set;
        }
        return set;
    }

    /**
     * Creates the complement sef of the given set
     * @param set Symbol set being complemented
     */
    public Complement(SymbolSet set) {
        super(set.getCode());
        this.set = remove_double_complement(set);
        if (set instanceof Complement) {
            intervals = ((Complement) set).getIntervals();
        } else {
            intervals = new Vector<Interval>();
            Vector<Interval> rank = set.getIntervals();
            int n = rank.size();
            for (int i = 1; i < n; i++) {
                intervals.add(new Interval(rank.get(i - 1).sup, rank.get(i).inf));
            }
            if (rank.get(0).inf > 0) {
                intervals.add(0, new Interval(0, rank.get(0).inf));
            }
            if (rank.get(n - 1).sup < code.size()) {
                intervals.add(new Interval(rank.get(n - 1).sup, code.size()));
            }
        }
    }

    /**
     * Returns the complement set type, i.e. SymbolSet.COMPLEMENT
     * @return SymbolSet.COMPLEMENT.
     */
    public int type() {
        return COMPLEMENT;
    }

    /**
     * Determines if the set includes the given symbol
     * @param s symbol to analize
     * @return <i>true</i> if the set includes the symbol, <i>false</i> otherwise.
     */
    public boolean includes(int s) {
        return!set.includes(s);
    }

    public SymbolSet getComplementedSet(){
        return set;
    }
}
