package unalcol.language.symbol.util;
import unalcol.types.collection.vector.*;
import unalcol.language.util.*;
import unalcol.language.symbol.*;

/**
 * <p>Title: SingleSymbolSet</p>
 *
 * <p>Description: A symbol set defined by just one symbol.</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class SingleSymbol extends SymbolSet {
    /**
     * Symbol defining the set
     */
    protected int symbol;

    /**
     * Creates a single symbol set over the given symbol code
     * @param code Symbol Code
     * @param symbol Symbol defining the set
     */
    public SingleSymbol(SymbolCode code, int symbol) {
        super(code);
        this.symbol = symbol;
        intervals = new Vector<Interval>();
        intervals.add(new Interval(this.symbol));
    }

    /**
     * Creates a single symbol set over the given symbol code
     * @param code Symbol Code
     * @param symbol Symbol defining the set
     */
    public SingleSymbol(SymbolCode code, char symbol) {
        super(code);
        this.symbol = code.encode(symbol);
        intervals = new Vector<Interval>();
        intervals.add(new Interval(this.symbol));
    }

    /**
     * Determines if the set includes the given symbol
     * @param s symbol to analize
     * @return <i>true</i> if the set includes the symbol, <i>false</i> otherwise.
     */
    public boolean includes(int s) {
        return (symbol == s);
    }

    /**
     * Returns the single symbol set type, i.e. SymbolSet.SINGLETON
     * @return SymbolSet.SINGLETON.
     */
    public int type() {
        return SINGLETON;
    }

    public int getSymbol(){ return symbol; }
}
