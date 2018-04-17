package unalcol.reflect.plugin;

import unalcol.reflect.xml.XMLElement;

public interface PlugIn{
	public static final String PLUGIN = "plugin";
	public static final String LOADER = "loader";
	public static final String SRC = "src";
	public static final String ID = "id";

	public static String id(XMLElement element){
		String id = (String)element.getAttribute(PlugIn.ID); 
		return (id!=null)?id:element.getTag(); 
	}
	
	default void init(XMLElement element, PlugInSet<?> builder){};

	public String id();
	public void setId( String id );

//	default Object get( String id ){ if( is(id) ) return this; else return null; }
}