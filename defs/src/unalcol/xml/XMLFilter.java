package unalcol.xml;

import unalcol.types.collection.Collection;
import unalcol.types.collection.keymap.HashMap;
import unalcol.types.collection.keymap.KeyMap;

public class XMLFilter{
	public String id;
	
	protected KeyMap<String, XMLManifest> modules = new HashMap<String,XMLManifest>();

	public XMLFilter( String attribute ){ id = attribute; }
	
	public String id(){ return id; }
	public void setId(String id){ this.id = id; }

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