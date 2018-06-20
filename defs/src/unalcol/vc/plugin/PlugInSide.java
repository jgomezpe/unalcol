package unalcol.vc.plugin;

import unalcol.plugin.PlugInSet;
import unalcol.vc.Component;
import unalcol.vc.DefaultSide;
import unalcol.xml.XMLElement;

public class PlugInSide extends DefaultSide{
	protected PlugInSet<Component> plugins;
	
	public PlugInSide(String id, PlugInSet<Component> plugins){
		super(id);
		this.plugins = plugins; 
	}
	
	protected void register( XMLElement element ){
		plugins.get(element);
		for( Object e:element.children() ) register((XMLElement)e ); 
	}
	
	public void init( XMLElement doc ){
		clear();
		register(doc);
	}
	
	@Override
	public void clear(){ plugins.clear(); }

	@Override
	public Component component(String id){ return plugins.get(id); }

	@Override
	public void register(Component component){ plugins.set(component.id(), component); }
}