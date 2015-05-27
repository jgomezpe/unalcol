package unalcol.tracer;

import java.io.FileWriter;
import java.io.IOException;
import unalcol.io.Write;

// import unalcol.Unalcol;

/**
 * <p>Title: ConsoleTracer</p>
 * <p>Description: A Tracer based on the java console</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class FileTracer extends Tracer {
    /**
     * File that is associated to the tracer
     */
    protected FileWriter file;
    /**
     * File Name
     */
    protected String fileName;
    /**
     * Determines if a new line symbol is added after tracing an object
     */
    protected boolean addNewLine;

    /**
     * Creates a console tracer
     * @param fileName File Name
     * @param addNewline used for determining if a new line symbol is added after tracing an object or not
     */
    public FileTracer(Object owner, String fileName, boolean addNewline) {
        super( owner );
        this.fileName = fileName;
        this.addNewLine = addNewline;
        try {
            file = new FileWriter(fileName);
        } catch (IOException e) {
            // @TODO I have to check the tracer architecture as plugins
            //e.printStackTrace();
        }
    }

    /**
     * Shows the traced information sent by the source into the console
     * @param obj Traced information to be sown in the console
     */
    public void add(Object obj) {
        try {
            file.write(Write.toString(obj));
            if (addNewLine) {
                file.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the traced information
     * @return null since the console does not store the traced information
     */
    public Object get() {
        return null;
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