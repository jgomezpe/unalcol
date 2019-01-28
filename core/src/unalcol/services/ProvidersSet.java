package unalcol.services;

import unalcol.types.collection.Collection;
import unalcol.types.collection.keymap.LowLevelKeyMap;

public class ProvidersSet{
	public ProvidersSet(){}
	
	protected LowLevelKeyMap<String,Object> providers = new LowLevelKeyMap<String,Object>();
	protected String current_provider_name = null;
	protected Object current_provider = null;
	
	public Collection<String> options(){ return providers.keys(); }
	
	public String current_id(){ return current_provider_name; }
	
	public Object current(){ return current_provider; }
	
	public Object get( String provider ){ return providers.get(provider); }
	
	public String current( String provider ){
		String id=current_provider_name;			
		current_provider = providers.get(provider);
		current_provider_name = provider;
		if( current_provider==null) current_provider_name=null;
		return id;
	}
	
	public void register(String id, Object service){
		if( current_provider_name==null ){
			current_provider_name = id;
			current_provider = service;
		}
		providers.set(id, service); 
	}

	public void remove(String id){ providers.remove(id); }
}