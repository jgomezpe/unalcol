package unalcol.vc.backend;

import unalcol.reflect.plugin.PlugIn;
import unalcol.vc.VCElement;
import unalcol.vc.frontend.FrontEnd;

public interface Controller extends VCElement, PlugIn{
	public static final String TAG="controller"; 	
	default void setBackend( BackEnd backend ){ if( backend() != backend ) frontend().link(this); }
	default void setFrontend( FrontEnd frontend ){ if( frontend() != frontend ) frontend.link(this); }
}