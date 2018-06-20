package unalcol.vc.plugin;

import unalcol.reflect.Loader;
import unalcol.vc.VCModel;

public class PlugInVCModel extends VCModel implements PlugInModel{
	public PlugInVCModel(Loader loader){ this( new PlugInBackEnd(loader), new PlugInFrontEnd(loader)); }
	
	public PlugInVCModel(PlugInBackEnd backend, PlugInFrontEnd frontend){ super(backend, frontend); }
}