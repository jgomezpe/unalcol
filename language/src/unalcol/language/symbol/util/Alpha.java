package unalcol.language.symbol.util;

import unalcol.language.util.*;
import unalcol.language.symbol.*;

/**
 * <p>Title: Alpha</p>
 *
 * <p>Description: Alpha Characters Set</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Alpha extends SymbolSet{
    /**
     * Creates an alpha character set over the given symbol code
     * @param code Symbol Code
     */
    public Alpha(SymbolCode code) {
        super(code);
        intervals = code.alpha();
    }

    /**
     * Determines if the set includes the given symbol
     * @param s symbol to analize
     * @return <i>true</i> if the set includes the symbol, <i>false</i> otherwise.
     */
    public boolean includes(int s) {
        return code.is_alpha(s);
    }

    /**
     * Returns the alpha set type, i.e. SymbolSet.ALPHA
     * @return SymbolSet.ALPHA.
     */
    public int type() {
        return ALPHA;
    }

}
