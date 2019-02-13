package unalcol.xml;

import unalcol.collection.Collection;
import unalcol.collection.KeyMap;
import unalcol.collection.keymap.HashMap;

public class XMLFilter{
	public String id;
	
	protected KeyMap<String, XMLManifest> modules = new HashMap<String,XMLManifest>();

	public XMLFilter( String attribute ){ id = attribute; }
	
	public String id(){ return id; }
	public void setId(String id){ this.id = id; }

	public void add( XMLElement element ){
		String module = null;
		try{ module = (String)element.get(id()); }catch(Exception e){}
		XMLManifest set;
		try{ set = modules.get(module); }
		catch(Exception e){
			set = new XMLManifest();
			modules.set(module,set);
		}
		set.add(element);
	}
	
	public Collection<String> modules(){ return modules.keys(); }
	
	public XMLElementSet module( String id ){ try{ return modules.get(id); }catch(Exception e){ return null; } }
}