package unalcol.reflect.plugin;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

public class PlugInLoader extends PlugInSet{
	public void add( PlugInManifest manifest, URLClassLoader cloader ) throws IOException{
		Set<String> plugins = manifest.plugins();
		for( String pl : plugins ){
			if( element.get(pl) == null ){
				PlugInDescriptor elem = manifest.get(pl);
				elem.setLoader(cloader);
				element.put(pl, elem);
			}	
		} 
	}
	
	public void add( URL plugin_url, ClassLoader mainLoader ) throws IOException{
		add( new PlugInManifest(plugin_url.toString()),	new URLClassLoader(new URL[]{plugin_url}, mainLoader) );
	}

	public String info(String plugin, String attribute){
		PlugInDescriptor e = element.get(plugin);
		if( e==null ) return null;
		return e.getAttribute(attribute);
	}
	
	public Set<String> plugins(){ return element.keySet(); } 
	
	public Object load(String plugin) throws PlugInException{
		PlugInDescriptor elem = element.get(plugin);
		if(elem==null) throw new PlugInException("Undefined plugin "+plugin);
		try{
			ClassLoader cl = elem.getLoader();
			Class<?> classToLoad = Class.forName(element.get(plugin).getAttribute(PlugIn.className), true, cl);
			return classToLoad.newInstance();
		}catch(Exception e){
			throw new PlugInException(e.getMessage());
		}	
	}
	
	public PlugIn load(PlugInDescriptor obj) throws PlugInException{
		PlugIn plugin = (PlugIn)load(obj.nick());
		plugin.init(obj, this);
		return plugin;
	}	
}