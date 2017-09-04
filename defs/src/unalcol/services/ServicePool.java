package unalcol.services;

import java.util.Iterator;

import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.types.collection.keymap.KeyMap;

public class ServicePool implements ServiceProvider{
	protected KeyMap<String,KeyMap<Object,MicroService<?>>> pool = new HTKeyMap<String,KeyMap<Object,MicroService<?>>>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void register( MicroService<?> service, Object caller ){
		for( String name:service.provides() ){
			KeyMap<Object,MicroService<?>> s = pool.get(name);
			if( s==null ){
				s = new HTKeyMap<Object,MicroService<?>>();
				pool.put(name, s);
			}
			MicroService<?> cs = s.get(caller);
			if(cs==null && service.multiple()){
			    cs = new MicroServiceSet<Object>();
			    s.put(caller, cs);
			}
			if( cs!=null && cs instanceof MicroServiceSet ) ((MicroServiceSet)cs).add(service);
			else s.put(caller, service);
		}
	}
	
	protected MicroService<?> get(KeyMap<Object,MicroService<?>> service, Class<?> caller){
		MicroService<?> m = service.get(caller);
		if(m!=null) return m; 
		try{ return get( service, caller.getSuperclass() ); }catch(Exception e ){}
		return null;
	}
	
	public MicroService<?> get(String service, Class<?> caller){
		KeyMap<Object,MicroService<?>> name = pool.get(service);
		if( name == null ) return null;
		MicroService<?> m = get(name,caller);
		if(m!=null) return m; 
		Class<?>[] superTypes = caller.getInterfaces();
		for( int i=0; i<superTypes.length; i++ )
			try{ return get( name, superTypes[i] ); }catch(Exception e){}
		return null;
	}

	@SuppressWarnings("unchecked")
	public MicroService<?> get(String service, Object caller){
		KeyMap<Object,MicroService<?>> name = pool.get(service);
		if( name == null ) return null;
		MicroService<?> m = name.get(caller);
		if(m==null) m = get(service, caller.getClass());
		if(m!=null){
			m.setName(service);
			((MicroService<Object>)m).setCaller(caller);			
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
		Iterator<String> k=s.keys();
		while(k.hasNext()) keys[i++]=k.next();
		return keys;
	}
}