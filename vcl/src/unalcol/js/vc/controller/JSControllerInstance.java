package unalcol.js.vc.controller;
import unalcol.reflect.plugin.PlugInInstance;
import unalcol.vc.Controller;;

public class JSControllerInstance implements PlugInInstance<Controller>{
	@Override
	public Controller get(){ return new JSControllerTree(); }
}