package unalcol.language.symbol;

import unalcol.types.collection.vector.*;
import unalcol.language.util.*;


/**
 * <p>Title: UniCode16</p>
 *
 * <p>Description: The UniCode16 symbol encoding</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public  class UniCode16 extends SymbolCode {
    /**
     * Using UNICODE16 code for the input symbols
     */
    public static final int UNI_CODE_16 = 65536;

    /**
     * Number of symbols in the code
     * @return number of different symbols
     */
    public int size() {
        return UNI_CODE_16;
    }

    /**
     * Encodes the given symbol.
     * @param c Original symbol code.
     * @return Encoding of the symbol.
     */
    public int encode(int c) {
        return c;
    }

    /**
     * Decodes the given symbol.
     * @param k Encoded symbol.
     * @return Decodification of the symbol.
     */
    public int decode(int k) {
        return k;
    }

    /**
     * Determines if an encoded symbol is an alpha symbol.
     * @param k Encoded symbol.
     * @return <i>true</i> if the encoded symbol is alpha, <i>false</i> otherwise.
     */
    public boolean is_alpha(int k) {
        return k == 241 || k == 209 ||
                (65 <= k && k <= 90) || (97 <= k && k <= 122);
    }

    /**
     * Determines if an encoded symbol is a digit symbol.
     * @param k Encoded symbol.
     * @return <i>true</i> if the encoded symbol is a digit, <i>false</i> otherwise.
     */
    public boolean is_digit(int k) {
        return (48 <= k && k <= 57);
    }

    /**
     * Determines the set of intervals representing the alpha symbols.
     * @return Set of intervals representing the alpha symbols.
     */
    public Vector<Interval> alpha() {
        Vector<Interval> intervals = new Vector<Interval>();
        intervals.add(new Interval(65, 91));
        intervals.add(new Interval(97, 123));
        intervals.add(new Interval(209, 210));
        intervals.add(new Interval(241, 242));

        return intervals;
    }

    /**
     * Determines the set of intervals representing the digit symbols.
     * @return Set of intervals representing the digit symbols.
     */
    public Vector<Interval> digit() {
        Vector<Interval> intervals = new Vector<Interval>();
        intervals.add(new Interval(48, 58));
        return intervals;
    }

}
