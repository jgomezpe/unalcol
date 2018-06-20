package unalcol.js.vc.mode.fx;


import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;
import unalcol.js.vc.JSFrontEnd;
import unalcol.types.collection.keymap.KeyMap;
import unalcol.vc.BackEnd;
import unalcol.vc.Component;

public class FXManager extends JSFrontEnd{
	protected WebEngine webEngine;

	protected boolean ready = false;

	public FXManager( String url, KeyMap<String, Component> views ){ super(url, views); }
	
	public void setEngine( WebEngine webEngine ){ this.webEngine = webEngine; }

	protected void execute_late(String js_command ){
		FXDeamon deamon = new FXDeamon(this, js_command);
		Platform.runLater( deamon );
	}

	protected void synchronize_back(){
		BackEnd backend = backend();
		if( backend.hasChanged() ){
			JSObject win = (JSObject)webEngine.executeScript("window");
			for( Component c : backend.components() ){
				String[] ids = c.ids();
				for( String id:ids ) win.setMember(id, c);
			}
			backend.synchronize();
		}
	}
	
	@Override
	public void execute( String js_command ){
		synchronize_back();
		if(ready) try{ webEngine.executeScript(js_command); }catch( IllegalStateException e ){ execute_late(js_command); }
		else execute_late(js_command); 
	}
	
	public void ready(){
		this.ready = true; 
		synchronize_back();
	}
}