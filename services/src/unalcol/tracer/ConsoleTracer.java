package unalcol.tracer;
import unalcol.io.*;

/**
 * <p>Title: ConsoleTracer</p>
 * <p>Description: A Tracer based on the java console</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class ConsoleTracer extends Tracer {
    /**
     * Determines if a new line symbol is added after tracing an object
     */
    protected boolean addNewLine = true;

    /**
     * Creates a console tracer (writes a data object per line
     */
    public ConsoleTracer( Object owner ) {
        super( owner );
    }

    /**
     * Creates a console tracer
     * @param addNewLine Determines if a new line symbol is added after tracing an object
     */
    public ConsoleTracer( Object owner, boolean addNewLine ) {
        super( owner );
        this.addNewLine = addNewLine;
    }

    /**
     * Shows the traced information sent by the source into the console
     * @param obj Traced information to be sown in the console
     */
    @Override
    public void add(Object obj) {
        if( tracing ){
            System.out.println(Write.toString(obj));
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
