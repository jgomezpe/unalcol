package unalcol.vc;

import unalcol.collection.Collection;
import unalcol.collection.KeyMap;
import unalcol.collection.keymap.HashMap;

public class KeyMapSide extends DefaultSide{
	protected boolean changed = false;
	protected KeyMap<String, Component> keymap;
	
	public KeyMapSide(String id){ this( id, new HashMap<String,Component>() ); }

	public KeyMapSide(String id, KeyMap<String, Component> keymap){
		super( id );
		init(keymap);
	}

	public void clear(){ keymap.clear(); }

	@Override
	public Component component(String id){ try{ return keymap.get(id); }catch(Exception e){ return null; } }

	public void init(KeyMap<String, Component> keymap){
		this.keymap = new HashMap<String,Component>();
		for( Component c:keymap ) register(c);	
	}
	
	public void register(Component component){
		changed = true;
		String[] ids = component.ids();
		for( String id:ids) keymap.set(id, component); 
		component.setSide(this);
	}
	
	public boolean hasChanged(){ return changed; }
	public void synchronize(){ changed = false; }

	@Override
	public Collection<Component> components(){ return keymap; }	
}