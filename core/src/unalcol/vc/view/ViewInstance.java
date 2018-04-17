package unalcol.vc.view;

import unalcol.reflect.plugin.PlugInInstance;
import unalcol.vc.View;

public class ViewInstance implements PlugInInstance<View>{
	@Override
	public View get(){ return new ViewTree(); }
}