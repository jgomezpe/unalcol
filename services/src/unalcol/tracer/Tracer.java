package unalcol.tracer;

import unalcol.service.*;

/**
 * <p>Title: Tracer</p>
 * <p>Description: Abstract definition of a Tracer of objects</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public abstract class Tracer{
    protected boolean tracing = true;

    public Tracer(){}

    public boolean start(){
        boolean old = tracing;
        tracing = true;
        return old;
    }

    public boolean stop(){
        boolean old = tracing;
        tracing = false;
        return old;
    }

    /**
     * Adds an object sent by an object to the tracer
     * @param obj Traced information to be added
     */
    public abstract void add(Object... obj);

    /**
     * Returns the traced object
     * @return An object representing the traced information
     */
    public abstract Object get();

    /**
     * Cleans the traced information
     */
    public abstract void clean();

    /**
     * Closes the tracer
     */
    public abstract void close();

    public static boolean addTracer( Object owner, Tracer tracer ){
    	return ServiceCore.set(owner, tracer);
    }
    
    public static Tracer[] get( Object owner ){
        try{
           Object[] services = ServiceCore.getAll(owner, Tracer.class);
           Tracer[] tracers = new Tracer[services.length];
           for( int i=0; i<services.length; i++ ){
               tracers[i] = (Tracer)services[i];
           }
           return tracers;
        }catch( Exception e ){
            e.printStackTrace();
        }
        return new Tracer[0];
    }

    /**
     * Adds a data object to each tracer associated to a given object
     * @param obj Object being traced
     * @param data Object to be added to each tracer associated to the object
     */
    public static void trace(Object obj, Object... data) {
        Tracer[] services = get(obj);
        for (Tracer service : services) {
            service.add(data);
        }
    }

    /**
     * Closes each tracer associated to a given object
     * @param obj Object being traced
     */
    public static void close(Object obj) {
        Tracer[] services = get(obj);
        for (Tracer service : services) {
            service.close();
        }
    }

    /**
     * Cleans each tracer associated to a given object
     * @param obj Object being traced
     */
    public static void clean(Object obj) {
        Tracer[] services = get(obj);
        for (Tracer service : services) {
            service.clean();
        }
    }
    
}