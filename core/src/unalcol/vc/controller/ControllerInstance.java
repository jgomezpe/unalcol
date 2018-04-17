package unalcol.vc.controller;

import unalcol.reflect.plugin.PlugInInstance;
import unalcol.vc.Controller;

public class ControllerInstance implements PlugInInstance<Controller>{
	@Override
	public Controller get(){ return new ControllerTree(); }

}