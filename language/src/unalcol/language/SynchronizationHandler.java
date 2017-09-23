package unalcol.language;

import unalcol.io.ShortTermMemoryReader;
import unalcol.language.symbol.*;
import java.io.IOException;
import java.util.Hashtable;
import unalcol.language.symbol.*;

/**
 * <p>Title: SynchronizationHandler</p>
 *
 * <p>Description: An error hanling process based on synchronization characters</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class SynchronizationHandler implements LanguageErrorHandler{

    protected String unexpectedSymbols;

    protected LanguageErrorMessage message = null;

    int c = 0;
    SymbolCode code;
    Hashtable<Integer, Integer> synchronization_chars =
            new Hashtable<Integer,Integer>();
    public SynchronizationHandler( SymbolCode code, int[] synch) {
        this.code = code;
        for( int i=0; i<synch.length; i++ ){
            synchronization_chars.put(synch[i], synch[i]);
        }
    }

    public int getCode(ShortTermMemoryReader<?> input) throws IOException{
        int s = input.read();
        if( s != -1 ){
           if( synchronization_chars.get(s) == null ){
               unexpectedSymbols += (char) code.encode(s);
               return LanguageErrorHandler.RETRY;
           }else{
               input.back();
               return LanguageErrorHandler.CANCEL;
           }
        }else{
            return LanguageErrorHandler.CANCEL;
        }
    }

    public LanguageConsumed apply( ShortTermMemoryReader<?> input,
                                   LanguageConsumed consumed,
                                   LanguageAcceptator acceptator ) throws IOException{
        unexpectedSymbols = "";
        LanguageConsumed c = consumed;
        if( c.accepted_as() < 0 ){
            c.set(new LanguageErrorMessage(input.getRow(), input.getColumn(),
                                           c.consumed(), 0));
        }
        while (!c.accepted() && getCode(input) == LanguageErrorHandler.RETRY) {
            c = acceptator.simulate(input);
            consumed.consumed_symbols += c.consumed();
        }
        consumed.consumed_symbols += unexpectedSymbols.length();
        return consumed;
    }
}
