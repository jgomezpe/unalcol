package unalcol.tracer;

import unalcol.io.Write;

public abstract class OutputStreamTracer extends Tracer {	
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
    public OutputStreamTracer(){}

    /**
     * Creates a console tracer
     * @param SEPARATOR Symbol used for separating objects
     */
    public OutputStreamTracer( char SEPARATOR ) {
    	this.SEPARATOR = SEPARATOR;
    }

    /**
     * Creates a console tracer
     * @param addNewLine Determines if a new line symbol is added after tracing an object
     */
    public OutputStreamTracer( boolean addNewLine ) {
    	this.addNewLine = addNewLine;
    }

    /**
     * Creates an OutputStream tracer
     * @param SEPARATOR Character used for separating traced values.
     * @param addNewLine Determines if a new line symbol is added after tracing an object
     */
    public OutputStreamTracer( char SEPARATOR, boolean addNewLine ) {
        this.addNewLine = addNewLine;
    }
    
    /**
     * Writes an object (String) to the Tracer.
     * @param str Object to be traced.
     */
    public abstract void write( String str );
    
    /**
     * Shows the traced information sent by the source into the console
     * @param obj Traced information to be shown in the console
     */
    @Override
    public void add(Object... obj) {
        if( tracing && obj.length > 0 ){
    		write(SEPARATOR+Write.toString(obj[0]));
        	for( int i=1; i<obj.length; i++ )
        		write(SEPARATOR+Write.toString(obj[i]));
        	if( addNewLine ) write("\n");
        }
    }

    /**
     * Return the traced information
     * @return null since the console does not store the traced information
     */
    @Override
    public Object get() {
        return null;
    }
}