package unalcol.language;
import unalcol.io.ShortTermMemoryReader;
import java.io.IOException;

/**
 * <p>Title: LanguageErrorHandler</p>
 *
 * <p>Description: A class representing the process used for dealing with errors in the recognition process</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public interface LanguageErrorHandler {
        /**
         * A retry message for the language recognition machine
         */
        public static final int RETRY = 1;
        /**
         * A cancel message for the language recognition machine
         */
        public static final int CANCEL = 0;
        /**
         * Aply the error hanling process to the given input, actual recognition process information and language recognition machine
         * @param input ShortTermMemoryReader  Set of symbols that will be used for simulating the language recognition machine.
         * @param consumed LanguageConsumed Output produced by the simulation process of a language recognition machine
         * @param acceptator LanguageAcceptator Language recognition machine
         * @return LanguageConsumed Output produced by the simulation process of a language recognition machine when using the erro handler process
         * @throws IOException
         */
        public LanguageConsumed apply( ShortTermMemoryReader<?> input,
                                   LanguageConsumed consumed,
                                   LanguageAcceptator acceptator ) throws IOException;
}
