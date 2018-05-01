package unalcol.js.vc.mode.fx;


import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;
import unalcol.js.vc.JSFrontEnd;
import unalcol.types.collection.keymap.KeyMap;
import unalcol.types.collection.vector.Vector;
import unalcol.vc.SimpleVCEnd;
import unalcol.vc.backend.Controller;
import unalcol.vc.backend.ControllerTree;
import unalcol.vc.frontend.View;

public class FXManager extends SimpleVCEnd<View, Controller> implements JSFrontEnd{
	protected WebEngine webEngine;

	protected boolean ready = false;
	protected Vector<Controller> toRegister = new Vector<Controller>();

	public FXManager( KeyMap<String, View> views ){ super(views); }
	
	public void setEngine( WebEngine webEngine ){ this.webEngine = webEngine; }

	public void execute( String js_command ){
		try{ webEngine.executeScript(js_command); }
		catch( IllegalStateException e ){
			FXDeamon deamon = new FXDeamon(this, js_command);
			Platform.runLater( deamon );
		}
	}
	
	public void ready(){
		this.ready = true;
		JSObject win = (JSObject)webEngine.executeScript("window");
		while( toRegister.size() > 0 ){
			int k = toRegister.size();
			Controller toR = toRegister.get(k-1);
			if( toR != null && !(toR instanceof ControllerTree) ){
				Controller x = (Controller)toR;
				if( x.frontend() != this ) x.setFrontend(this);
				String[] ids = x.id().split(",");
				for( String s:ids ) win.setMember(s, x);
			}
			toRegister.remove(k-1);
		}
	}

	@Override 
	public boolean link( Controller controller ){
		toRegister.add(controller);
		if( ready ) ready();
		return ready;
	}
}