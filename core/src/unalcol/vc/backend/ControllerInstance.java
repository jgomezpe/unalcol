package unalcol.vc.backend;

import unalcol.reflect.plugin.PlugInInstance;

public class ControllerInstance implements PlugInInstance<Controller>{
	@Override
	public Controller get(){ return new ControllerTree(); }

}