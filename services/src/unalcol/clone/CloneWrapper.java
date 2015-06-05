package unalcol.clone;

import java.lang.reflect.Method;
import java.lang.reflect.Array;

/**
 * <p>Cloning service wrapper for the clone method. Used for classes that
 * already define a clone method</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class CloneWrapper extends Clone<Object> {
    /**
     * Creates a clone wrapped method for objects
     */
    protected String method_name = "clone";
    
    
    public Object clonePrimitiveArray( Object obj ){
        if( obj instanceof int[] ){
            return ((int[])obj).clone();
        }
        if( obj instanceof double[] ){
            return ((double[])obj).clone();
        }
        if( obj instanceof char[] ){
            return ((char[])obj).clone();
        }
        if( obj instanceof byte[] ){
            return ((byte[])obj).clone();
        }
        if( obj instanceof long[] ){
            return ((long[])obj).clone();
        }
        if( obj instanceof short[] ){
            return ((short[])obj).clone();
        }
        if( obj instanceof float[] ){
            return ((float[])obj).clone();
        }
        return ((boolean[])obj).clone();
    }
    
    public Object cloneArray( Object obj ){
        Class<?> cl = obj.getClass().getComponentType();
        if( cl.isPrimitive() ){
            return clonePrimitiveArray(obj);
        }
        int n = Array.getLength(obj);
        Object clone = Array.newInstance(cl, n);
        for( int i=0; i<n; i++ ){
            Array.set(clone, i, Clone.get(Array.get(obj, i)));
        }
        return clone;
    }
    
    /**
     * Creates a clone of the given object if a clone method is provided by the object
     * @param obj Object from which the CloneService will be retrieved
     * @return A Clone of the object  if a clone method is provided by the object
     * <i>null</i> otherwise
     */
    @Override
    public Object clone(Object obj){
        if( obj instanceof String ) return ""+obj;
        
        if( obj instanceof Double ){
            return (Double)obj;
        }
        
        if( obj instanceof Integer ){
            return (Integer)obj;
        }
        
        if( obj.getClass().isArray() ){
            return cloneArray(obj);
        }
        try{
           Method m = obj.getClass().getMethod(method_name) ;
           return m.invoke(obj);
        }catch( Exception e ){            
            e.printStackTrace();
           return null;
        }
    }
}