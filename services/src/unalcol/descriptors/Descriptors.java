package unalcol.descriptors;

import unalcol.service.*;

/**
 * <p>Obtains a set of real value descriptors for a given object</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public abstract class Descriptors<T> {
    /**
     * Obtains the descriptors of an object
     * @param obj Object to be analyzed
     * @return An array of double values used for describing the object
     */
    public abstract double[] descriptors(T obj);
    
    
    protected static boolean defaultLoaded = false;    
    public static Descriptors<?> get(Object owner){
        if( ServiceCore.get(Object.class, Descriptors.class) == null )
            set(Object.class, new DescriptorsWrapper());
        return (Descriptors<?>)ServiceCore.get(owner, Descriptors.class);
    }
    
    public static boolean set( Object owner, Descriptors<?> service ){
        return ServiceCore.set(owner, Descriptors.class, service);
    }
    
    /**
     * Obtains the descriptors of an object
     * @param obj Object to be analyzed
     * @return An array of double values used for describing the object
     */
    @SuppressWarnings("unchecked")
	public static double[] obtain(Object obj) {
        return ((Descriptors<Object>)get(obj)).descriptors(obj);
    }    
}