package unalcol.gui.js.mode.fx;

import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;
import unalcol.gui.Controller;
import unalcol.gui.js.JSView;

public class FXView extends JSView{
	protected WebEngine webEngine;


	public FXView( WebEngine webEngine, String unalcol_url, String url ){
		super(unalcol_url, url);
		this.webEngine = webEngine;
	}
	

	public void execute( String js_command ){
		try{ webEngine.executeScript(js_command); }
		catch( IllegalStateException e ){
			FXDeamon deamon = new FXDeamon(this, js_command);
			Platform.runLater( deamon );
		}
	}
		
	public void register(){
		JSObject win = (JSObject)webEngine.executeScript("window");
		for( int i=registered; i<toRegister.size(); i++ ){
			Controller x = toRegister.get(i);
			if( x != null ){
				if( x.view() != this ) x.set(this);
				for( String s:x.id() ) win.setMember(s, x);
			}
		}
		super.register();
	}
}