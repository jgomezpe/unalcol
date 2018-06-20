package unalcol.vc.plugin;

import unalcol.plugin.PlugInSet;
import unalcol.reflect.Loader;
import unalcol.vc.Component;
import unalcol.vc.FrontEnd;
import unalcol.xml.XMLElement;

public class PlugInFrontEnd extends PlugInSide implements FrontEnd{
	public PlugInFrontEnd( Loader loader ){ super(FrontEnd.FRONTEND, new PlugInSet<Component>(loader, FrontEnd.VIEW, XMLElement.TAG) ); }
}
