package unalcol.vc;

import unalcol.reflect.plugin.PlugIn;

public interface Controller extends PlugIn{
	public String[] id();
	public void set( View view );
	public View view();
}
