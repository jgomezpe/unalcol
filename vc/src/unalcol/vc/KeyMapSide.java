package unalcol.vc;

import unalcol.types.collection.Collection;
import unalcol.types.collection.keymap.KeyMap;

public class KeyMapSide extends DefaultSide{
	protected boolean changed = false;
	protected KeyMap<String, Component> keymap;
	
	public KeyMapSide(String id, KeyMap<String, Component> keymap){
		super( id );
		this.keymap = keymap;
		for( Component c:keymap ) c.setSide(this);
	}

	public void clear(){ keymap.clear(); }

	@Override
	public Component component(String id){ return keymap.get(id); }

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