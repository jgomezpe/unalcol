package unalcol.reflect.plugin;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Set;

import org.w3c.dom.Element;

public class PlugInLoader extends PlugInSet{
	protected String fileName;
	protected HashMap<String,ClassLoader>  loader = new HashMap<String,ClassLoader>();
	
	public void add( PlugInManifest manifest, URLClassLoader cloader ) throws IOException{
		Set<String> plugins = manifest.plugins();
		for( String pl : plugins ){
			if( element.get(pl) == null ){ 
				element.put(pl, manifest.get(pl));
				loader.put(pl, cloader);
			}	
		} 
	}
	
	public void add( URL plugin_url, ClassLoader mainLoader ) throws IOException{
		add( new PlugInManifest(plugin_url.toString()),	new URLClassLoader(new URL[]{plugin_url}, mainLoader) );
	}

	public String info(String plugin, String attribute){
		PlugInElement e = element.get(plugin);
		if( e==null ) return null;
		return e.getAttribute(attribute);
	}
	
	public Set<String> plugins(){ return element.keySet(); } 
	
	public Object load(String plugin) throws PlugInException{
		ClassLoader cl = loader.get(plugin);
		if(cl==null) throw new PlugInException("Undefined plugin "+plugin);
		try{
			Class<?> classToLoad = Class.forName(element.get(plugin).getAttribute(PlugIn.className), true, cl);
			return classToLoad.newInstance();
		}catch(Exception e){
			throw new PlugInException(e.getMessage());
		}	
	}
	
	public PlugIn load(Element obj) throws PlugInException{
		PlugIn plugin = (PlugIn)load(obj.getTagName());
		plugin.init(obj, this);
		return plugin;
	}	
}