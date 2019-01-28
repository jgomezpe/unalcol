package unalcol.language.symbol;
import unalcol.types.collection.vector.*;
import unalcol.language.Encoder;
import unalcol.language.util.*;

/**
 * <p>Title: SymbolCode</p>
 *
 * <p>Description: Abstract class for managing different symbol encodings</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface TextEncoder extends Encoder{
    /**
     * Gets the number of symbols in the symbol code
     * @return Number of symbols in the symbol code
     */
    public int size();

    /**
     * Determines if the code represents an alpha character (a..z A..Z)
     * @param k code of the symbol
     * @return <i>true</i> if the code represents an alphabetic character, <i>false</i> otherwise
     */
    public abstract boolean is_alpha(int k);

    /**
     * Determines if the code represents a digit character (0..9)
     * @param k code of the symbol
     * @return <i>true</i> if the code represents a digit character, <i>false</i> otherwise
     */
    public abstract boolean is_digit(int k);

    /**
     * Determines the set of intervals representing the alpha symbols.
     * @return Set of intervals representing the alpha symbols.
     */
    public abstract Vector<Interval> alpha();

    /**
     * Determines the set of intervals representing the digit symbols.
     * @return Set of intervals representing the digit symbols.
     */
    public abstract Vector<Interval> digit();

    /**
     * Encodes the given string
     * @param str String to be encoded
     * @return Encoded version of the given string
     */
    default int[] toInt(String str) {
        int n = str.length();
        int[] input = new int[n];
        char[] cstr = str.toCharArray();
        for (int i = 0; i < n; i++) {
            input[i] = encode((int)cstr[i]);
        }
        return input;
    }

    default char get( int code ){ return (char)(int)decode(code); }

    /**
     * Decodifies a piece of code
     * @param str String to be decodified
     * @param start start point of decodification
     * @param end final point of decodification
     * @return The decodified version of the piece of code
     */
    default String toString(int[] str, int start, int end) {
        StringBuffer sb = new StringBuffer();
        for (int i = start; i < end; i++) {
            sb.append(get(str[i]));
        }
        return sb.toString();
    }
    
    /**
     * Decodifies the given code
     * @param str String to be decodified
     * @return The decodified version of the code
     */
   default String toString(int[] str){ return toString(str, 0, str.length); }

    /**
     * Decodifies a piece of code
     * @param str String to be decodified
     * @param start start point of decodification
     * @param end final point of decodification
     * @return The decodified version of the piece of code
     */
    default String toString(Vector<Integer> str, int start, int end) {
        StringBuffer sb = new StringBuffer();
        for (int i = start; i < end; i++) {
            sb.append(get(str.get(i)));
        }
        return sb.toString();
    }

    /**
     * Decodifies the given code
     * @param str String to be decodified
     * @return The decodified version of the code
     */
    default String toString(Vector<Integer> str){ return toString(str, 0, str.size()); }

}
