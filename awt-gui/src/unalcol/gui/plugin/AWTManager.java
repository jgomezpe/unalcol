package unalcol.gui.plugin;

import java.io.IOException;

import unalcol.reflect.plugin.PlugInInstance;
import unalcol.reflect.xml.XMLDocument;
import unalcol.vc.backend.Controller;
//import unalcol.vc.plugin.PlugInsVCManager;
//import unalcol.vc.plugin.XMLController;

public class AWTManager{ /* extends PlugInsVCManager{

	public AWTManager(String url, PlugInInstance<Controller> defaultController) {
		super(url, defaultController, new AWTViewInstance());
	}

	public AWTManager( String[] urls, PlugInInstance<Controller> defaultController ){
		super( urls, defaultController, new AWTViewInstance() );
		this.ready();
		this.register();
	}
	
	@Override
	protected void register(){ super.register(); }
	
	public void init( String url, String xml ) throws IOException{
		XMLDocument doc = new XMLDocument(url, xml);
		XMLController xmlc = (XMLController)this.controller("xml");
		xmlc.init(doc, view);
	}*/
}