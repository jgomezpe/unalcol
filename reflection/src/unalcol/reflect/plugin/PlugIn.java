package unalcol.reflect.plugin;

public interface PlugIn{
	public static final String plugin = "plugin";
	public static final String className = "class";
	public static final String nickName = "nick";
	public static final String id = "id";

	public void init(PlugInDescriptor element, PlugInLoader builder);
	public String[] id();

	default boolean is(String id ){
		for( String s:this.id() ) if( s.equals(id) ) return true;
		return false;
	}
	
	default Object get( String id ){ if( is(id) ) return this; else return null; }
}