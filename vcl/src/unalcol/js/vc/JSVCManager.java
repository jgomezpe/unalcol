package unalcol.js.vc;

import unalcol.js.vc.controller.JSControllerInstance;
import unalcol.js.vc.view.JSViewInstance;
import unalcol.vc.VCElement;
import unalcol.vc.plugin.PlugInsVCManager;

public class JSVCManager extends PlugInsVCManager{
	public static final String CONNECTOR = "_";
	
	protected String unalcol_url;

	public void execute( String command ){}
	
	public JSVCManager( String unalcol_url, String url ){ 
		super(new String[]{url+"plugins/",unalcol_url+"plugins/"}, new JSControllerInstance(), new JSViewInstance());
	} 
	
	public String addTimer( VCElement c, int delay ){ return null;}
	
	public String delTimer( VCElement c, int delay ){ return null; }	

}
