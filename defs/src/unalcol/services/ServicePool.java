package unalcol.services;

import unalcol.types.collection.Collection;
import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.types.collection.keymap.KeyMap;

public class ServicePool implements ServiceProvider{
	protected KeyMap<String,KeyMap<Object,AbstractMicroService<?>>> pool = new HTKeyMap<String,KeyMap<Object,AbstractMicroService<?>>>();

	public void register( AbstractMicroService<?> service, Object caller ){
		for( String name:service.provides() ){
			KeyMap<Object,AbstractMicroService<?>> s;
			if( pool.valid(name) ){ s = pool.get(name); }
			else{
				s = new HTKeyMap<Object,AbstractMicroService<?>>();
				pool.set(name, s);
			}
			if( service.multiple() ){
				MicroServiceSet<?> cs;
				if( s.valid(caller) ){ cs = (MicroServiceSet<?>)s.get(caller); }
				else{
					cs = new MicroServiceSet<Object>();
					s.set(caller, cs);
				}
				cs.register(service);
			}else s.set(caller, service);			
		}
	}
	
	protected AbstractMicroService<?> get(KeyMap<Object,AbstractMicroService<?>> service, Class<?> caller) throws NoSuchMethodException{
		if(caller == null) throw new NoSuchMethodException();
		if(service.valid(caller)) return service.get(caller);
		try{ return get( service, caller.getSuperclass() ); }catch(NoSuchMethodException e){}
		Class<?>[] superTypes = caller.getInterfaces();
		for( Class<?> c:superTypes ) try{ return get( service, c ); }catch(NoSuchMethodException e){};
		throw new NoSuchMethodException();
	}

	public AbstractMicroService<?> get(String service, Class<?> caller) throws NoSuchMethodException{
		if( pool.valid(service) ) try{ return get(pool.get(service),caller); }catch( NoSuchMethodException e ){}
		throw new NoSuchMethodException(service);
	}

	@SuppressWarnings("unchecked")
	public AbstractMicroService<?> get(String service_name, Object caller) throws NoSuchMethodException{
		if( pool.valid(service_name) && caller != null ){
			KeyMap<Object,AbstractMicroService<?>> service = pool.get(service_name);
			AbstractMicroService<?> m;
			if( service.valid(caller) ) m = service.get(caller);
			else m = get(service, caller.getClass()); 
			m.setName(service_name);
			((AbstractMicroService<Object>)m).setCaller(caller);
			return m;
		}
		throw new NoSuchMethodException(service_name);
	}

	@Override
	public Object run(String service, Object caller, Object... args) throws Exception{ return get(service,caller).run(args); }

	@Override
	public Collection<String> provides(){ return pool.keys(); }
}