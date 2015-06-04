package unalcol.reflect;

public class Core {
	protected static UnalcolHierarchy hierarchy = new UnalcolHierarchy();
	
    public static Object get( Class<?> owner, Class<?> type ){
    	return hierarchy.get(owner, type);
    }
	
    public static Object get( Object owner, Class<?> type ){
    	return hierarchy.get(owner, type);
    }
	
    public static boolean set( Object owner, Class<?> type, Object instance ){
    	return hierarchy.set(owner, type, instance);
    }    	
}
