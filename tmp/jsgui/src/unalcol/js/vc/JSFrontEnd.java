package unalcol.js.vc;

import unalcol.vc.Controller;
import unalcol.vc.FrontEnd;
import unalcol.vc.KeyMapSide;

public class JSFrontEnd extends KeyMapSide implements FrontEnd{
	protected String url;
	
	public JSFrontEnd(){ super(FrontEnd.FRONTEND); }
	
	public void execute( String command ){}
	
	public String addTimer( Controller c, int delay ){ return null;}
	
	public String delTimer( Controller c, int delay ){ return null; }	
}