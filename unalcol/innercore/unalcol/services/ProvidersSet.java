package unalcol.services;

import unalcol.collection.Collection;
import unalcol.collection.KeyMap;
import unalcol.collection.keymap.HashMap;

public class ProvidersSet{
	public ProvidersSet(){}
	
	protected KeyMap<String,Object> providers = new HashMap<String,Object>();
	protected String current_provider_name = null;
	protected Object current_provider = null;
	
	public Collection<String> options(){ return providers.keys(); };      
	
	public String current_id(){ return current_provider_name; }
	
	public Object current(){ return current_provider; }
	
	public Object get( String provider ){ try{ return providers.get(provider); }catch(Exception e ){ return null; } }
	
	public String current( String provider ){
		String id=current_provider_name;
		try{
			current_provider_name = provider;
			current_provider = providers.get(provider);
		}catch(Exception e){ 
			current_provider_name=null;
			current_provider = null;
		}
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