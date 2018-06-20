package unalcol.services;

import unalcol.types.collection.Collection;
import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.types.collection.keymap.KeyValue;

public class ProvidersSet{
	public ProvidersSet(){}
	
	protected HTKeyMap<String,Object> providers = new HTKeyMap<String,Object>();
	protected KeyValue<String,Object> provider=null;
	
	public Collection<String> options(){ return providers.keys(); }
	
	public String current_id(){ if( provider != null ) return provider.key(); else return null; }
	
	public Object current(){ if( provider != null ) return provider.value(); else return null; }
	
	public Object get( String provider ){ return providers.get(provider); }
	
	public String current( String provider ){
		if( this.provider==null){ this.provider = new KeyValue<String,Object>(null, null); }
		String id=this.provider.key();			
		this.provider.setValue( providers.get(provider) );
		this.provider.setKey(provider);
		if( this.provider.value()==null) this.provider=null;
		return id;
	}
	
	public void register(String id, Object service){
		if( provider==null ) provider = new KeyValue<String,Object>(id, service);
		providers.set(id, service); 
	}

	public void remove(String id){ providers.remove(id); }
}