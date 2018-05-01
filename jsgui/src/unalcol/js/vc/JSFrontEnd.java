package unalcol.js.vc;

import unalcol.vc.backend.Controller;
import unalcol.vc.frontend.FrontEnd;

public interface JSFrontEnd extends FrontEnd{
	default void execute( String command ){}
	
	default String addTimer( Controller c, int delay ){ return null;}
	
	default String delTimer( Controller c, int delay ){ return null; }	
}