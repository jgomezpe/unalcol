package unalcol.services;

import unalcol.types.collection.Collection;
import unalcol.types.collection.keymap.LowLevelKeyMap;

public class ServicePool {
	/**
	 * Maintains a pool of services (the key is the service name) that are provided to objects/classes
	 */
	protected LowLevelKeyMap<Object,ProvidersSet> pool = new LowLevelKeyMap<Object,ProvidersSet>();
	
	protected ProvidersSet get( Class<?> caller ) throws NoSuchMethodException {
		if( caller == null ) throw new NoSuchMethodException();
		ProvidersSet cs = pool.get(caller);
		if( cs!=null ) return cs; 
		try{ return get( caller.getSuperclass() ); }catch(NoSuchMethodException e){}
		Class<?>[] superTypes = caller.getInterfaces();
		for( Class<?> c:superTypes ) try{ return get( c ); }catch(NoSuchMethodException e){};
		throw new NoSuchMethodException();
	}
	
	public ProvidersSet get( Object caller ) throws NoSuchMethodException {
		ProvidersSet cs = pool.get(caller);
		if( cs!=null ) return cs; 
		return get( caller.getClass() );
	}
	
	public Collection<String> providers( Object caller ){
		try{ return get(caller).options(); }catch(Exception e ){}
		return null;
	}
	
	public String provider( Object caller ){
		try{ return get(caller).current_id(); }catch(Exception e ){}
		return null;
	}
	
	public String use_provider( Object caller, String provider ){
		try{ return get(caller).current(provider); }catch(Exception e ){}
		return null;
	}
	
	/**
	 * registers the service <i>service</i> to the object <i>caller</i> 
	 * @param service Service to be register to the object
	 * @param caller Object that can call the service
	 */
	public void register( Object service, Object caller ){
		ProvidersSet s = pool.get(caller);
		if( s==null ){
			s = new ProvidersSet();
			pool.set(caller,s);
		}
		s.register(service.toString(), service);
	}
}
