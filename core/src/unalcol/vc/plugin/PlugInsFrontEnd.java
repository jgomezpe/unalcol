package unalcol.vc.plugin;

import unalcol.reflect.plugin.PlugInSet;
import unalcol.vc.backend.Controller;
import unalcol.vc.frontend.FrontEnd;
import unalcol.vc.frontend.View;

public class PlugInsFrontEnd extends PlugInsVCEnd<View,Controller> implements FrontEnd{
	public PlugInsFrontEnd(String url, PlugInSet<View> controllers){ super(url, controllers); }
	@Override
	public void load( String end ){
		View c = build( end );
		c.setFrontend(this);
	}	
}