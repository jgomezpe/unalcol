package unalcol.language;

/**
 * <p>Title: LanguageConsumed</p>
 *
 * <p>Description: Output produced by the simulation process of a language recognition machine</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class LanguageConsumed {
	/**
	 * Indicates the input can be accepted  
	 */
	public static final int ACCEPTED = 0;
	/**
	 * Indicates the input cannot be accepted  
	 */
	public static final int NOT_ACCEPTED = -1;
	/**
	 * Indicates the input is accepted but the continuation symbol is not valid  
	 */
	public static final int ACCEPTED_BUT_WRONG_CONTINUATION_SYMBOL = -2;
	
    /**
     * Indicates how the input is considered by the language recognition machine
     */
    protected int input_accepted_as;
    
    /**
     * Maintains the number of input symbols consumed by the language recognition machine
     */
    protected int consumed_symbols;
    
    /**
     * Creates an object with the input produced by the simulation process of a language recognition machine
     * @param accepted Determines if the input was accepted by the language recognition machine or not
     * @param consumed Number of input symbols consumed by the language recognition machine
     * @param data Additional information of the recognition process
     */
    public LanguageConsumed(int accepted_as, int consumed) {
        input_accepted_as = accepted_as;
        consumed_symbols = consumed;
    }

    /**
     * Determines if the input was accepted by the language recognition machine or not
     * @return <i>true</i>If the input was accepted, <i>false</i>otherwise.
     */
    public int accepted_as() {
        return input_accepted_as;
    }

    /**
     * Obtains the number of input symbols consumed by the language recognition machine
     * @return Number of input symbols consumed by the language recognition machine
     */
    public int consumed() {
        return consumed_symbols;
    }
}