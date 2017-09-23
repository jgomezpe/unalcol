package unalcol.language.symbol.util;

import unalcol.language.util.*;
import unalcol.language.symbol.*;

/**
 * <p>Title: Digit</p>
 *
 * <p>Description: The Digits Symbol Set</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Digit extends SymbolSet{
    /**
     * Creates a digit symbol set over the given symbol code
     * @param code Symbol Code
     */
    public Digit(SymbolCode code) {
        super(code);
        intervals = code.digit();
    }

    /**
     * Determines if the set includes the given symbol
     * @param s symbol to analize
     * @return <i>true</i> if the set includes the symbol, <i>false</i> otherwise.
     */
    public boolean includes(int s) {
        return code.is_digit(s);
    }

    /**
     * Returns the digit set type, i.e. SymbolSet.DIGIT
     * @return SymbolSet.DIGIT.
     */
    public int type() {
        return DIGIT;
    }
}
