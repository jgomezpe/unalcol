package unalcol.reflect.plugin;

import java.util.HashMap;
import java.util.Set;

public class PlugInSet {
	protected HashMap<String,PlugInElement>  element = new HashMap<String,PlugInElement>();

	public String info(String plugin, String attribute){
		PlugInElement e = element.get(plugin);
		if( e==null ) return null;
		return e.getAttribute(attribute);
	}
		
	public Set<String> plugins(){ return element.keySet(); } 	
	public PlugInElement get(String plugin){ return element.get(plugin); }
	
	public boolean contains(String plugin){ return get(plugin)!=null; }
		
	protected void add(PlugInElement element){
		String id = element.getAttribute(PlugIn.nickName);
		if( id==null || id.length()==0) id = element.getAttribute(PlugIn.className); 
		this.element.put(id, element);
	}    
}