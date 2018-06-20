package unalcol.js.vc;

import unalcol.types.collection.keymap.KeyMap;
import unalcol.vc.Component;
import unalcol.vc.Controller;
import unalcol.vc.FrontEnd;
import unalcol.vc.KeyMapSide;

public class JSFrontEnd extends KeyMapSide implements FrontEnd{
	protected String url;
	
	public JSFrontEnd( String url, KeyMap<String, Component> views ){
		super(FrontEnd.FRONTEND, views);
		this.url = url;
	}
	
	public void execute( String command ){}
	
	public String addTimer( Controller c, int delay ){ return null;}
	
	public String delTimer( Controller c, int delay ){ return null; }	
}