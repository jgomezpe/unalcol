package unalcol.io;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import unalcol.service.*;

/**
 * <p>Title: Write</p>
 *
 * <p>Description: Writing methods for a given object</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public abstract class Write<T>{
    /**
     * Writes an object to the given writer
     * @param obj Object to write
     * @param writer The writer object
     * @throws IOException IOException
     */
    public abstract void write(T obj, Writer writer) throws Exception;
    
    /**
     * Determines if the default service has been registered in the service infra-structure
     */
    public static Write<?> get(Object owner){
        if( ServiceCore.get(Object.class, Write.class) == null )
            set(Object.class, new WriteWrapper());
        return (Write<?>)ServiceCore.get(owner, Write.class);
    }
    
    public static boolean set( Object owner, Write<?> service ){
        return ServiceCore.set(owner, Write.class, service);
    }
        
    /**
     * Writes an object to the given writer (The object should has a write method)
     * @param obj Object to write
     * @param writer The writer object
     * @throws IOException IOException
     */
    @SuppressWarnings("unchecked")
	public static void apply(Object obj, Writer writer) throws Exception {
        Write<?> service = (Write<?>)get(obj);
        ((Write<Object>)service).write(obj, writer);
    }


    /**
     * Gets the persistent version of an object in String version. The Class which the
     * object belongs to should have associated a ClassPersistence object in the
     * Persistence class
     * @param obj Object that will be stored in an string
     * @return String containing the persistent version of the object
     */
    public static String toString(Object obj) {
        try {
            StringWriter sw = new StringWriter();
            apply(obj, sw);
            sw.close();
            return sw.toString();
        } catch (Exception e) {}
        return obj.toString();
    }       
}
