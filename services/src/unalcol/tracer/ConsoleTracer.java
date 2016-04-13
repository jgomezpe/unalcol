package unalcol.tracer;

/**
 * <p>Title: ConsoleTracer</p>
 * <p>Description: A Tracer based on the java console</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class ConsoleTracer extends OutputStreamTracer {
    /**
     * Determines if a new line symbol is added after tracing an object
     */
    protected boolean addNewLine = true;

    /**
     * Determines if a new line symbol is added after tracing an object
     */
    protected char SEPARATOR = ' ';
    
    /**
     * Creates a console tracer (writes a data object per line
     */
    public ConsoleTracer() {
    	super();
    }

    /**
     * Creates a console tracer
     * @param SEPARATOR Symbol used for separating objects
     */
    public ConsoleTracer( char SEPARATOR ) {
    	super(SEPARATOR );        
    }

    /**
     * Creates a console tracer
     * @param addNewLine Determines if a new line symbol is added after tracing an object
     */
    public ConsoleTracer( boolean addNewLine ) {
    	super( addNewLine );        
    }

    /**
     * Creates a console tracer
     * @param SEPARATOR Character used for separating traced values.
     * @param addNewLine Determines if a new line symbol is added after tracing an object
     */
    public ConsoleTracer( char SEPARATOR, boolean addNewLine ) {
    	super( SEPARATOR, addNewLine );        
    }

    /**
     * Shows the traced information sent by the source into the console
    * @param str Traced information to be shown in the console
     */
    @Override
    public void write(String str) {
   		System.out.print(str);
    }

    /**
     * Cleans the traced information
     */
    @Override
    public void clean() {}
    
    /**
     * Closes the console (does nothing)
     */
    @Override
    public void close() {};
}