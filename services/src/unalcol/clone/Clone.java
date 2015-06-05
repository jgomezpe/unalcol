package unalcol.clone;

import unalcol.service.ServiceCore;

/**
 * <p>Service for cloning objects</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public abstract class Clone<T> {
    /**
     * Creates a clone of a given object
     * @param toClone Object to be cloned
     * @return A clone of the object
     */
    public abstract T clone(T toClone);
    
    public static Clone<?> get(Object owner){
        if( ServiceCore.get(Object.class, Clone.class) == null )
            set(Object.class, new CloneWrapper());
        return (Clone<?>)ServiceCore.get(owner, Clone.class);
    }
    
    public static boolean set( Object owner, Clone<?> service ){
        return ServiceCore.set(owner, Clone.class, service);
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