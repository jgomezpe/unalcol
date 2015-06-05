package unalcol.io;
import java.io.IOException;
import unalcol.service.*;

/**
 * <p>Read service. Reads objects from a {@link unalcol.io.ShortTermMemoryReader}</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 * @param <T> Type of objects the service will read
 */
public abstract class Read<T>{
    /**
     * Reads an object from the given reader
     * @param obj Instance of the type of objects the service will read
     * @param reader The input stream from which the object will be read
     * @return An object, of the type the read service is attending, that is read from the input stream
     * @throws IOException IOException
     */
    public abstract T read(T obj, ShortTermMemoryReader reader) throws
            IOException;
    
    /**
     * Determines if the default service has been registered in the service infra-structure
     */
    public static Read<?> get(Object owner){
        if( ServiceCore.get(Object.class, Read.class) == null )
            set(Object.class, new ReadWrapper());
        return (Read<?>)ServiceCore.get(owner, Read.class);
    }
    
    public static boolean set( Object owner, Read<?> service ){
        return ServiceCore.set(owner, Read.class, service);
    }        
    
    /**
     * Reads an object from the given reader (The object should has a read method)
     * @param obj Object to read
     * @param reader The reader object
     * @return 
     * @throws IOException IOException
     */
    @SuppressWarnings("unchecked")
	public static Object apply(Object obj, ShortTermMemoryReader reader) throws IOException {
        return ((Read<Object>)get(obj)).read(obj,reader);
    }

    public static void readSeparator( ShortTermMemoryReader reader, 
            char separator ) throws IOException{
        try{
            char c = (char)reader.read();
            while( c!=separator && Character.isSpaceChar(c)){
                c = (char)reader.read();
            }
            if( c != separator && c != (char)-1 ){
                throw new Exception("Non available separator...");
            }
        }catch( Exception e ){
            throw reader.getException("ReadService Parser Error "+e.getMessage());
        }
    }
    
}
