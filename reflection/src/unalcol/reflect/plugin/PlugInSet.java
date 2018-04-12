package unalcol.reflect.plugin;

import java.util.HashMap;
import java.util.Set;

public class PlugInSet {
	protected HashMap<String,PlugInDescriptor>  element = new HashMap<String,PlugInDescriptor>();

	public String info(String plugin, String attribute){
		PlugInDescriptor e = element.get(plugin);
		if( e==null ) return null;
		return e.getAttribute(attribute);
	}
		
	public Set<String> plugins(){ return element.keySet(); } 	
	public PlugInDescriptor get(String plugin){ return element.get(plugin); }
	
	public boolean contains(String plugin){ return get(plugin)!=null; }
		
	protected void add(PlugInDescriptor element){
		String id = element.getAttribute(PlugIn.nickName);
		String[] ids = id.split(",");
		for( String s:ids )	this.element.put(s, element);
	}    
}