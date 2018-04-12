package unalcol.reflect.plugin;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Set;

public class PlugInLoader{
	public static PlugInException undefined( String plugin ){
		return new PlugInException("Undefined plugin "+plugin);
	}
	
	protected HashMap<String, PlugInSet> plugins = new HashMap<String,PlugInSet>();
	protected String[] types; 
	
	public PlugInLoader(){ this( new String[]{PlugIn.className} ); }
	
	public PlugInLoader( String[] type ){
		this.types = type;
		for( String t:type ) plugins.put(t, new PlugInSet());
	}
	
	public void add( PlugInManifest manifest, URLClassLoader cloader ) throws IOException{
		Set<String> mplugins = manifest.plugins();
		for( String pl : mplugins ){
			for( String t:types ){
				PlugInSet set = plugins.get(t);
				if( !set.contains(pl) ){
					PlugInDescriptor elem = manifest.get(pl);
					elem.setLoader(cloader);
					set.add(elem);
				}
			}	
		} 
	}
	
	public void add( URL plugin_url, ClassLoader mainLoader ) throws IOException{
		add( new PlugInManifest(plugin_url.toString()),	new URLClassLoader(new URL[]{plugin_url}, mainLoader) );
	}

	public String info(String plugin, String attribute){
		PlugInDescriptor e = plugins.get(types[0]).get(plugin);
		if( e==null ) return null;
		return e.getAttribute(attribute);
	}
	
	public Set<String> plugins(String type){ return plugins.get(type).plugins(); } 
	
	public Object load(String plugin, String className) throws PlugInException{
		PlugInSet set = plugins.get(className);
		if(set==null) throw undefined(plugin);
		PlugInDescriptor elem = set.get(plugin);
		if(elem==null) throw undefined(plugin);
		try{
			ClassLoader cl = elem.getLoader();
			Class<?> classToLoad = Class.forName(elem.getAttribute(className), true, cl);
			return classToLoad.newInstance();
		}catch(Exception e){
			throw new PlugInException(e.getMessage());
		}	
	}
	
	public PlugIn load(PlugInDescriptor obj, String className) throws PlugInException{
		PlugIn plugin = (PlugIn)load(obj.nick(), className);
		plugin.init(obj, this);
		return plugin;
	}	
}