package unalcol.js.vc.mode.fx;

import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;
import unalcol.js.vc.JSVCManager;
import unalcol.vc.Controller;
import unalcol.vc.VCElement;
import unalcol.vc.controller.ControllerTree;

public class FXManager extends JSVCManager{
	protected WebEngine webEngine;


	public FXManager( WebEngine webEngine, String unalcol_url, String url ){
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
			VCElement toR = toRegister.get(i);
			if( toR != null && toR instanceof Controller && !(toR instanceof ControllerTree) ){
				Controller x = (Controller)toR;
				if( x.manager() != this ) x.set(this);
				String[] ids = x.id().split(",");
				for( String s:ids ){
					System.out.print(CONNECTOR+s+" ");
					win.setMember(CONNECTOR+s, x);
				}
				System.out.println();
			}
		}
		super.register();
	}
}