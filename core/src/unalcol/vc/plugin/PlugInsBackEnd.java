package unalcol.vc.plugin;

import unalcol.reflect.plugin.PlugInSet;
import unalcol.vc.backend.BackEnd;
import unalcol.vc.backend.Controller;
import unalcol.vc.frontend.View;

public class PlugInsBackEnd extends PlugInsVCEnd<Controller,View> implements BackEnd{
	public PlugInsBackEnd(String url, PlugInSet<Controller> controllers){ super(url, controllers); }

	@Override
	public void load( String end ){
		Controller c = build( end );
		if( c != null ) c.setBackend(this);
	}
}