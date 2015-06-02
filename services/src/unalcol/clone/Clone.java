package unalcol.clone;

import unalcol.service.*;

/**
 * <p>Service for cloning objects</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public abstract class Clone<T> extends Service {
    /**
     * Creates a clone of a given object
     * @param toClone Object to be cloned
     * @return A clone of the object
     */
    public abstract T clone(T toClone);
    
    protected static boolean defaultLoaded = false;
    public static Clone<?> get(Object obj){
        if( !defaultLoaded ){
            Clone<Object> service = new CloneWrapper();
            register( Object.class, service );
            set(Clone.class, Object.class, service);
            defaultLoaded = true;
        }
        return (Clone<?>)get(Clone.class, obj);
    }
    
    public static Clone<?> set( Object obj, Clone<?> service ){
        return (Clone<?>)set(Clone.class, obj, service);
    }
    
    /**
     * Creates a clone of a given object
     * @param obj Object to be cloned
     * @return A clone of the object, if a cloning service is available for the given object, <i>null</i> otherwise
     */
    @SuppressWarnings("unchecked")
	public static Object create( Object obj ){
        return ((Clone<Object>)get(obj)).clone(obj);
    }    
}