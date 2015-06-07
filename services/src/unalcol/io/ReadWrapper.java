package unalcol.io;

import java.io.IOException;
import java.lang.reflect.Method;

public class ReadWrapper extends Read<Object> {
    /**
     * Creates a clone wrapped method for objects
     */
    protected String method_name = "clone";
    
    protected Method method = null;
    
    public ReadWrapper( Class<?> type ) {
        try{
            method = type.getMethod(method_name) ;
         }catch( Exception e ){            
             e.printStackTrace();
         }
	}
    
    public boolean available(){ return method!=null; }

	@Override
	public Object read(ShortTermMemoryReader reader) throws IOException {
        try{
            return method.invoke(reader);
         }catch( Exception e ){            
            throw new IOException(e.getMessage());
         }
	}

}
