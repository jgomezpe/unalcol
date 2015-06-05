package unalcol.instance;

import unalcol.service.ServiceCore;

/**
 * <p>Instance generator.</p>
 *
 * <p>Copyright: Copyright (c) 2015</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 * @param <T> Type of objects from which instances will be generated
 */
public abstract class Instance<T> {
    /**
     * Creates an instance belonging to the class of the given object
     * @param obj Object used for determining the class from which a new instance will be generated
     * @return An instance belonging to the class of the given object if possible, <i>null</i> otherwise
     */
    public abstract T get( Object... args);
    
    public abstract Class<?> type();
    
    public static Instance<?> get(Object owner, Class<?> type){
    	Instance<?>[] opt = (Instance<?>[])ServiceCore.getAll(owner, Instance.class);
    	int i=0;
    	while( i<opt.length && opt[i].type() != type ){ i++; }
        return (i<opt.length)? opt[i] : null;
    }
    
    public static boolean set( Object owner, Instance<?> service ){
        return ServiceCore.set(owner, service.getClass(), service);
    }
    
    /**
     * Creates an instance belonging to the class of the given object
     * @param obj Object used for determining the class from which a new instance will be generated
     * @return An instance belonging to the class of the given object if possible, <i>null</i> otherwise
     */
    @SuppressWarnings("unchecked")
	public static Object create( Object owner, Class<?> type, Object... args ){
        Instance<?> service = get(owner, type);
        if( service != null ){
            return ((Instance<Object>)service).get(args);
        }
        return null;
    }    
}