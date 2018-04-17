package unalcol.reflect.plugin;

import unalcol.reflect.xml.XMLElement;
import unalcol.reflect.xml.XMLElementKey;
import unalcol.types.collection.keymap.MultiIdCollection;

public class PlugInSet<T> extends MultiIdCollection<T>{
	protected PlugInLoader loader;
	protected String module;
	protected PlugInInstance<T> defaultPlugIn;
	protected XMLElementKey xmlKey;
	
	public PlugInSet( PlugInLoader loader, String module ){ this( loader, module, XMLElement.TAG); }

	public PlugInSet( PlugInLoader loader, String module, String xmlKey ){ this( loader, module, xmlKey, null); }
	
	public PlugInSet( PlugInLoader loader, String module, String xmlKey, PlugInInstance<T> defaultPlugIn ){
		super( new PlugInKey<T>() );
		this.module = module;
		this.loader = loader;
		this.xmlKey = new XMLElementKey(xmlKey);
		this.defaultPlugIn = defaultPlugIn;
	}
	
	@SuppressWarnings("unchecked")
	public T get(XMLElement element){
		String id = (String)element.getAttribute(PlugIn.ID);
		T p = get( id );
		if( p!=null ) return p;
		if( defaultPlugIn!=null ) p = defaultPlugIn.get(); 
		try{ p = (T)loader.load(xmlKey.get(element), module); } catch (PlugInException e) {}
		if( p instanceof PlugIn ) ((PlugIn)p).init(element, this); 
		add(p);
		return p; 
	}
	
	public boolean valid(XMLElement key){ return defaultPlugIn!=null || get(key)!=null; }
}