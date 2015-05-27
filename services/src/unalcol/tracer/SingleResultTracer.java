package unalcol.tracer;

/**
 * <p>Title: SingleResultTracer</p>
 * <p>Description: A tracer that is able to keep track of asingle object</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class SingleResultTracer extends Tracer {
    /**
     * Traced information
     */
    private Object object = null;

    /**
     * Creates a new SingleResultTracer
     */
    public SingleResultTracer(Object owner) {
        super( owner );
    }

    /**
     * Replaces the traced information with a new one
     * @param obj Traced information
     */
    public void add(Object obj) {
        object = obj;
    }

    /**
     * Returns the traced information
     * @return A single object representing the traced information
     */
    public Object get() {
        return object;
    }

    /**
     * Cleans the traced information
     */
    public void clean() {
        object = null;
    }

    /**
     * Closes the tracer (does nothing)
     */
    public void close() {};
}