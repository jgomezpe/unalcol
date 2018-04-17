package unalcol.vc;

import unalcol.reflect.plugin.PlugIn;

public interface VCElement extends PlugIn{
	public void set( VCManager manager );
	public VCManager manager();
}