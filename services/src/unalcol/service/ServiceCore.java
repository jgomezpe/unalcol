package unalcol.service;


public class ServiceCore {
	protected static ServicePool hierarchy = new ServicePool();
	
    public static Object[] getAll( Class<?> owner, Class<?> type ){
    	return hierarchy.getAll(owner, type);
    }
	
    public static Object[] getAll( Object owner, Class<?> type ){
    	return hierarchy.getAll(owner, type);
    }
	
    public static Object get( Class<?> owner, Class<?> type ){
    	return hierarchy.get(owner, type);
    }
	
    public static Object get( Object owner, Class<?> type ){
    	return hierarchy.get(owner, type);
    }
	
    public static boolean set( Object owner, Class<?> type, Object instance ){
    	return hierarchy.set(owner, type, instance);
    }    	

    public static boolean set( Object owner, Object instance ){
    	return hierarchy.set(owner, instance.getClass(), instance);
    } 
    
    public static boolean remove( Object owner ){ 
    	return hierarchy.remove(owner);
    }
}
