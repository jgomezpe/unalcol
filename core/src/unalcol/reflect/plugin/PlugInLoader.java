package unalcol.reflect.plugin;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import unalcol.reflect.xml.XMLDocument;
import unalcol.reflect.xml.XMLElement;
import unalcol.reflect.xml.XMLManifest;
import unalcol.types.collection.Collection;
import unalcol.types.collection.keymap.HTKeyMap;

public class PlugInLoader{
	public static PlugInException undefined( String plugin ){ return new PlugInException("Undefined plugin "+plugin); }

	protected HTKeyMap<String, XMLManifest> modules = new HTKeyMap<String,XMLManifest>();
	
	public PlugInLoader(){}

	public PlugInLoader( String url ){ this(new String[]{url}); }
	
	public PlugInLoader( String[] urls ){ try{ for(String url:urls) this.addRepository(url); }catch(Exception e){} }
	
	public void addPackage( String url ) throws IOException{
		URLClassLoader loader =  new URLClassLoader(new URL[]{new URL(url)}, PlugInLoader.class.getClassLoader());
		XMLDocument doc = new XMLDocument(url, PlugIn.PLUGIN);
		doc.setAttributeRecursive(PlugIn.LOADER, loader);
		for( XMLElement pl : doc.children() ){
			String module = pl.getTag();
			XMLManifest set = modules.get(module);
			if( set==null ) modules.set(module,new XMLManifest(module, doc));
			else set.merge(new XMLManifest(module, doc));
		}
	}

	public void addRepository( String url ) throws IOException{
		XMLDocument doc = new XMLDocument(url, PlugIn.PLUGIN);
		for( XMLElement elem:doc.children() ){
			addPackage( url+elem.getAttribute(PlugIn.SRC) );
		}
	}
	
	public Object load(String plugin_type, String module) throws PlugInException{
		XMLManifest m = modules.get(module);
		if( m==null ) throw undefined(plugin_type);
		XMLElement elem = (XMLElement)m.get(plugin_type);
		if(elem==null) throw undefined(plugin_type);
		try{
			ClassLoader cl = (ClassLoader)elem.getAttribute(PlugIn.LOADER);
			Class<?> classToLoad = Class.forName((String)elem.getAttribute(PlugIn.SRC), true, cl);
			return classToLoad.newInstance();
		}catch(Exception e){
			throw new PlugInException(e.getMessage());
		}	
	}
	
	public Collection<String> modules(){ return modules.keys(); }
	
	public Collection<String> module( String key ){ return modules.get(key).keys(); }
}