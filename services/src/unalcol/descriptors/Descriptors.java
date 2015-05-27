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
public abstract class Descriptors extends Service {
    /**
     * Obtains the descriptors of an object
     * @param obj Object to be analyzed
     * @return An array of double values used for describing the object
     */
    public abstract double[] descriptors(Object obj);
    
    
    protected static boolean defaultLoaded = false;    
    public static Descriptors get(Object obj){
        if( !defaultLoaded ){
            Descriptors service = new DescriptorsWrapper();
            register(Object.class, service);
            set(Descriptors.class, Object.class, service);
            defaultLoaded = true;
        }
        return (Descriptors)get(Descriptors.class, obj);
    }
    
    public static Descriptors set( Object obj, Descriptors service ){
        return (Descriptors)set(Descriptors.class, obj, service);
    }
    
    /**
     * Obtains the descriptors of an object
     * @param obj Object to be analized
     * @return An array of double values used for describing the object
     */
    public static double[] obtain(Object obj) {
        return get(obj).descriptors(obj);
    }    
}