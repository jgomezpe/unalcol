package unalcol.reflect.plugin;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Set;

import org.w3c.dom.Element;

public class PlugInLoader {
	protected String fileName;
	protected HashMap<String,ClassLoader>  loader = new HashMap<String,ClassLoader>();
	protected HashMap<String,Element>  element = new HashMap<String,Element>();
	
	public static final String set = "set";
	public static final String plugin = "plugin";
	public static final String className = "class";
    
	public void add( URL plugin_url, ClassLoader mainLoader ) throws IOException{
		PlugInManifest manifest = new PlugInManifest(plugin_url.toString());
		URLClassLoader child = new URLClassLoader(new URL[]{plugin_url}, mainLoader);
		Set<String> plugins = manifest.plugins();
		for( String pl : plugins ){
			element.put(pl, manifest.get(pl));
			loader.put(pl, child);
		} 
	}

	public String info(String className, String attribute){
		Element e = element.get(className);
		if( e==null ) return null;
		return e.getAttribute(attribute);
	}
	
	public Set<String> plugins(){
		return element.keySet();
	} 
	
	public Object newInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		ClassLoader cl = loader.get(className);
		if(cl==null) return null;
		Class<?> classToLoad = Class.forName(className, true, cl);
		return classToLoad.newInstance();
	} 
}
