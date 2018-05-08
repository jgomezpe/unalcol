package unalcol.gui.plugin;

import unalcol.reflect.plugin.PlugInInstance;
import unalcol.vc.frontend.View;

public class AWTViewInstance implements PlugInInstance<View>{
	@Override
	public View get(){ return new AWTViewTree(); }
}