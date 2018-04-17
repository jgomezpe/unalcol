package unalcol.reflect.xml;

import unalcol.reflect.plugin.PlugIn;

public class XMLManifest extends XMLElementSet{
	
	public XMLManifest( String module, XMLDocument doc ){
		super(PlugIn.ID);
		for(XMLElement e:doc.children()) if( module.equals(e.getTag()) ) add(e);
	}
	
	public void merge( XMLManifest other ){ for( XMLElement e:other ) add(e); }
	
	public void setAttribute( String key, Object value ){ for( XMLElement e:elements ) e.setAttributeRecursive(key, value); }
}