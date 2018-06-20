package unalcol.services;


import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.types.collection.keymap.KeyMap;

public class Service{
	protected static KeyMap<Class<?>, ServicePool> services = new HTKeyMap<Class<?>,ServicePool>();
	
	protected static void register( Class<?> service, Object provider, Object caller ){
		ServicePool sp = services.get(service);
		if( sp==null ){
			sp = new ServicePool();
			services.set(service, sp);
		}
		sp.register(provider, caller);
		if( service.getSuperclass() != null ) register( service.getSuperclass(), provider, caller );
		Class<?>[] superTypes = service.getInterfaces();
		for( Class<?> c:superTypes ) register( c, provider, caller );
	}
	
	public static void register( Object provider, Object caller ){
		register( provider.getClass(), provider, caller );
	}
	
	public static ProvidersSet providers( Class<?> service, Object caller ) throws NoSuchMethodException{
		ServicePool sp = services.get(service);
		if( sp != null ) return sp.get(caller);
		throw new NoSuchMethodException();
	}
	
	public static Object provider( Class<?> service, Object caller ) throws NoSuchMethodException{
		return providers(service, caller).current();
	}		
}

/* 
 * import java.lang.reflect.ParameterizedType;
 *     default void register(){
    	ParameterizedType parameterizedType = (ParameterizedType)getClass().getGenericSuperclass();
    	Class<?> cl = (Class<?>)parameterizedType.getActualTypeArguments()[0];	
    	Service.register(this, cl);
    }
*/    
