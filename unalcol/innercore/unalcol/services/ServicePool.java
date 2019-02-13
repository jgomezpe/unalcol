package unalcol.services;

import unalcol.collection.Collection;
import unalcol.collection.KeyMap;
import unalcol.collection.keymap.HashMap;
import unalcol.constants.InnerCore;
import unalcol.exception.ParamsException;

public class ServicePool {
	/**
	 * Maintains a pool of services (the key is the service name) that are provided to objects/classes
	 */
	protected KeyMap<Object,ProvidersSet> pool = new HashMap<Object,ProvidersSet>();
	
	protected ProvidersSet get( Class<?> caller ) throws ParamsException {
		try{ return pool.get(caller); }catch(Exception e){}
		try{ return get( caller.getSuperclass() ); }catch(ParamsException e){}
		Class<?>[] superTypes = caller.getInterfaces();
		for( Class<?> c:superTypes ) try{ return get( c ); }catch(ParamsException e){};
		throw new ParamsException(InnerCore.NOSUCHELEM, caller);
	}
	
	public ProvidersSet get( Object caller ) throws ParamsException{ try{ return pool.get(caller); }catch(Exception e){ return get( caller.getClass() ); } }
	
	public Collection<String> providers( Object caller ){ try{ return get(caller).options(); }catch(Exception e ){ return null; } }
	
	public String provider( Object caller ){ try{ return get(caller).current_id(); }catch(Exception e ){ return null; } }
	
	public String use_provider( Object caller, String provider ){ try{ return get(caller).current(provider); }catch(Exception e ){ return null; } }
	
	/**
	 * registers the service <i>service</i> to the object <i>caller</i> 
	 * @param service Service to be register to the object
	 * @param caller Object that can call the service
	 */
	public void register( Object service, Object caller ){
		ProvidersSet s = null; 
		try{ s = pool.get(caller); }
		catch(Exception e){
			s = new ProvidersSet();
			pool.set(caller,s);
		}
		s.register(service.toString(), service);
	}
}