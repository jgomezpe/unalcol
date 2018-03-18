package unalcol.reflect.plugin;

import java.util.HashMap;
import java.util.Set;

import org.w3c.dom.Element;

public class PlugInSet {
	protected HashMap<String,Element>  element = new HashMap<String,Element>();

	public String info(String plugin, String attribute){
		Element e = element.get(plugin);
		if( e==null ) return null;
		return e.getAttribute(attribute);
	}
		
	public Set<String> plugins(){ return element.keySet(); } 	
	public Element get(String plugin){ return element.get(plugin); }
	
	public boolean contains(String plugin){ return get(plugin)!=null; }
		
	protected void add(Element element){
		String id = element.getAttribute(PlugIn.nickName);
		if( id==null || id.length()==0) id = element.getAttribute(PlugIn.className); 
		this.element.put(id, element);
	}    
}