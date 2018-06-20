package unalcol.xml;

import unalcol.types.collection.Collection;
import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.types.collection.keymap.KeyMap;
import unalcol.types.object.basic.BasicThing;

public class XMLFilter extends BasicThing{
	protected KeyMap<String, XMLManifest> modules = new HTKeyMap<String,XMLManifest>();

	public XMLFilter( String attribute ){ super(attribute); }
	
	public void add( XMLElement element ){
		String module = (String)element.get(id());
		XMLManifest set = modules.get(module);
		if( set==null ){
			set = new XMLManifest();
			modules.set(module,set);
		}
		set.add(element);
	}
	
	public Collection<String> modules(){ return modules.keys(); }
	
	public XMLElementSet module( String id ){ return modules.get(id); }
}