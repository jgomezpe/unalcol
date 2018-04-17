package unalcol.reflect.xml;

import unalcol.types.collection.keymap.Key;

public class XMLElementKey implements Key<String, XMLElement>{
	protected String key;
	
	public XMLElementKey( String key ){ this.key = key; }

	@Override
	public String get(XMLElement obj){
		String id = (String)obj.getAttribute(key);
		if( id==null ) id = obj.getTag();
		return id.split(",")[0]; 
	}

}
