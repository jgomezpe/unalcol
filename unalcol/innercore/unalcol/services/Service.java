package unalcol.services;

import unalcol.collection.KeyMap;
import unalcol.collection.keymap.HashMap;
import unalcol.exception.ParamsException;

public class Service{
	protected static KeyMap<Class<?>, ServicePool> services = new HashMap<Class<?>,ServicePool>();
	
	protected static void register( Class<?> service, Object provider, Object caller ){
		ServicePool sp = null;
		try{ sp = services.get(service); }
		catch(Exception e){
			sp = new ServicePool();
			services.set(service, sp);
		}
		sp.register(provider, caller);
		if( service.getSuperclass() != null ) register( service.getSuperclass(), provider, caller );
		Class<?>[] superTypes = service.getInterfaces();
		for( Class<?> c:superTypes ) register( c, provider, caller );
	}
	
	public static void register( Object provider, Object caller ){ register( provider.getClass(), provider, caller ); }
	
	public static ProvidersSet providers( Class<?> service, Object caller ) throws ParamsException{ return services.get(service).get(caller); }
	
	public static Object provider( Class<?> service, Object caller ) throws ParamsException{ return providers(service, caller).current(); }		
}