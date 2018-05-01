package unalcol.gui;

import unalcol.reflect.plugin.PlugInInstance;
import unalcol.vc.View;

public class AWTViewInstance implements PlugInInstance<View>{
	@Override
	public View get(){ return new AWTViewTree(); }
}