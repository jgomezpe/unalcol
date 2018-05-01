package unalcol.vc.frontend;

import unalcol.reflect.plugin.PlugInInstance;

public class ViewInstance implements PlugInInstance<View>{
	@Override
	public View get(){ return new ViewTree(); }
}