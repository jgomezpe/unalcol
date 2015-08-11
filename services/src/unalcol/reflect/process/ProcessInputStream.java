package unalcol.reflect.process;
import java.io.*;

/**
 * <p>A Class for reading the output streams used by an External Process (command).</p>
 *
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class ProcessInputStream implements Runnable{
    /**
     * OutputStream used by the External Process (command) that is going to be read
     */
    protected InputStream is;

    /**
     * Thread used for reading the OutputStream while the External Process is running
     */
    protected Thread thread;

    /**
     * External Process being executed
     */
    protected ExternalProcess process;

    /**
     * OutputStream associated to the OutputStream used by the External Process
     */
    protected PrintStream out = null;

    /**
     * Creates an object for reading the OutputStreams used by an External Process (command). 
     * @param is OutputStream used by the External Process (command) that is going to be read
     * @param process External Process being executed 
     */
    public ProcessInputStream( InputStream is, ExternalProcess process ) {
        this.is = is;
        this.process = process;
    }

    /**
     * Creates an object for reading the OutputStreams used by an External Process (command).
     * @param is OutputStream used by the External Process (command) that is going to be read
     * @param process External Process being executed
     * @param out OutputStream associated to the OutputStream used by the External Process
     */
    public ProcessInputStream( InputStream is, ExternalProcess process,
            PrintStream out ) {
        this.is = is;
        this.process = process;
        this.out = out;
    }

    /**
     * Starts the OutputStream processing
     */
    public void start () {
        thread = new Thread(this);
        thread.start ();
    }

    /**
     * Process the OutputStream used by the External Process
     */
    public void run () {
        try {
            if( out != null ){
                while(is.available() > 0 || process.is_running ) {
                    if( is.available() > 0 ){
                        out.print((char)is.read());
                    }
                }
            }else{
                while(is.available() > 0 || process.is_running ) {
                    if( is.available() > 0 ){
                        is.read();
                    }
                }
            }
            is.close();

        } catch (Exception ex) {
            ex.printStackTrace ();
        }
    }
}
