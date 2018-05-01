package unalcol.vc;

import unalcol.types.collection.Collection;
import unalcol.types.collection.keymap.KeyMap;

public class SimpleVCEnd<T,S> implements VCEnd<T,S>{
	protected KeyMap<String,T> components;
	protected VCEnd<S,T> complement; 
	
	public SimpleVCEnd(KeyMap<String, T> components){ this.components = components; }

	@Override
	public T get(String id){ return components.get(id); }

	@Override
	public Collection<String> list(){ return components.keys(); }

	@Override
	public VCEnd<S,T> get(){ return complement; }

	@Override
	public void set(VCEnd<S,T> complement) {
		this.complement = complement; 
		if( complement.get() != this ) complement.set(this);
	}
	
	@Override
	public boolean link(S complement){ return false; }
	
	public boolean register(T component){
		complement.link(component);
		return components.add(component); 
	}
}