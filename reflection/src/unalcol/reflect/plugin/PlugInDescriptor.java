package unalcol.reflect.plugin;

public interface PlugInDescriptor {
	public String nick();
	public String getAttribute( String key );
	public void setLoader( ClassLoader loader );
	public ClassLoader getLoader();
	public PlugInDescriptor child( int k );
	public int children();
}