package unalcol.plugin;

public class PlugInTreeInstance implements PlugInInstance<PlugIn>{
	@Override
	public PlugIn get(){ return new DefaultPlugInTree(); }
}