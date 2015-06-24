package unalcol.tracer;

import java.io.FileWriter;
import java.io.IOException;

// import unalcol.Unalcol;

/**
 * <p>Title: ConsoleTracer</p>
 * <p>Description: A Tracer based on the java console</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class FileTracer extends OutputStreamTracer {
    /**
     * File that is associated to the tracer
     */
    protected FileWriter file;
    /**
     * File Name
     */
    protected String fileName;

    /**
     * Creates a console tracer
     * @param fileName File Name
     * @param addNewline used for determining if a new line symbol is added after tracing an object or not
     */
    public FileTracer(String fileName) {
        try {
            file = new FileWriter(fileName);
        } catch (IOException e) {
            // @TODO I have to check the tracer architecture as plugins
            //e.printStackTrace();
        }
    }

    /**
     * Creates a console tracer
     * @param fileName File Name
     * @param addNewline used for determining if a new line symbol is added after tracing an object or not
     */
    public FileTracer(String fileName, char SEPARATOR) {
    	this( fileName );
    	this.SEPARATOR = SEPARATOR;
    }

    /**
     * Creates a console tracer
     * @param fileName File Name
     * @param addNewline used for determining if a new line symbol is added after tracing an object or not
     */
    public FileTracer(String fileName, char SEPARATOR, boolean addNewline) {
    	this( fileName, SEPARATOR );
    	this.addNewLine = addNewline;
    }

    /**
     * Shows the traced information sent by the source into the console
     * @param obj Traced information to be sown in the console
     */
    public void write(String str) {
        try {
            file.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the associated file
     */
    public void close() {
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cleans the traced information
     */
    public void clean() {
        close();
        try {
            file = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}