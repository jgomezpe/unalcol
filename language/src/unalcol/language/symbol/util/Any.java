package unalcol.language.symbol.util;

import unalcol.types.collection.vector.*;
import unalcol.language.util.*;
import unalcol.language.symbol.*;

/**
 * <p>Title: Any</p>
 *
 * <p>Description: The Universal Symbol Set</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Any extends SymbolSet{

    /**
     * Creates an any symbol set over the given symbol code Any = SymbolCode
     * @param code Symbol Code
     */
    public Any(SymbolCode code) {
        super(code);
        intervals = new Vector<Interval>();
        intervals.add(new Interval(0, code.size()));
    }

    /**
     * Determines if the set includes the given symbol
     * @param s symbol to analize
     * @return <i>true</i> if the set includes the symbol, <i>false</i> otherwise.
     */
    public boolean includes(int s) {
        return (0 <= s && s < code.size());
    }

    /**
     * Returns the any set type, i.e. SymbolSet.ANY
     * @return SymbolSet.ANY.
     */
    public int type() {
        return ANY;
    }
}
