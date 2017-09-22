package unalcol.services;

import unalcol.types.collection.Collection;
import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.types.collection.keymap.KeyMap;

public class ServicePool implements ServiceProvider{
	protected KeyMap<String,KeyMap<Object,AbstractMicroService<?>>> pool = new HTKeyMap<String,KeyMap<Object,AbstractMicroService<?>>>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void register( AbstractMicroService<?> service, Object caller ){
		for( String name:service.provides() ){
			KeyMap<Object,AbstractMicroService<?>> s = pool.get(name);
			if( s==null ){
				s = new HTKeyMap<Object,AbstractMicroService<?>>();
				pool.set(name, s);
			}
			AbstractMicroService<?> cs = s.get(caller);
			if(cs==null && service.multiple()){
			    cs = new MicroServiceSet<Object>();
			    s.set(caller, cs);
			}
			if( cs!=null && cs instanceof MicroServiceSet ) ((MicroServiceSet)cs).add(service);
			else s.set(caller, service);
		}
	}

	protected AbstractMicroService<?> get(KeyMap<Object,AbstractMicroService<?>> service, Class<?> caller){
		AbstractMicroService<?> m = service.get(caller);
		if(m==null) m = get( service, caller.getSuperclass() );
		return m;
	}

	public AbstractMicroService<?> get(String service, Class<?> caller){
		KeyMap<Object,AbstractMicroService<?>> name = pool.get(service);
		if( name == null ) return null;
		AbstractMicroService<?> m = get(name,caller);
		if(m==null){ 
			Class<?>[] superTypes = caller.getInterfaces();
			for( int i=0; i<superTypes.length && m==null; i++ )	m=get( name, superTypes[i] );
		}
		return m;
	}

	@SuppressWarnings("unchecked")
	public AbstractMicroService<?> get(String service, Object caller){
		KeyMap<Object,AbstractMicroService<?>> name = pool.get(service);
		if( name == null ) return null;
		AbstractMicroService<?> m = name.get(caller);
		if(m==null) m = get(service, caller.getClass());
		if(m!=null){
			m.setName(service);
			((AbstractMicroService<Object>)m).setCaller(caller);			
		}
		return m;
	}

	@Override
	public Object run(String service, Object caller, Object... args) throws Exception{ return get(service,caller).run(args); }

	@Override
	public String[] provides(){
		HTKeyMap<String, String> s=new HTKeyMap<String,String>();
		String[] keys = new String[s.size()];
		int i=0;
		Collection<String> k=s.keys();
		for(String t:k) keys[i++]=t;
		return keys;
	}
}