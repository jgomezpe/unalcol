package unalcol.vc.plugin;

import unalcol.plugin.PlugInSet;
import unalcol.reflect.Loader;
import unalcol.types.object.Thing;
import unalcol.vc.BackEnd;
import unalcol.vc.Component;

public class PlugInBackEnd extends PlugInSide implements BackEnd{
	public PlugInBackEnd( Loader loader ){ super(BackEnd.BACKEND, new PlugInSet<Component>(loader, BackEnd.CONTROLLER, Thing.ID) ); }
}