package unalcol.vc.js;

import unalcol.gui.Controller;
import unalcol.gui.plugin.PlugInsView;
import unalcol.reflect.plugin.PlugInManager;

public class JSView extends PlugInsView{
	protected String unalcol_url;

	public Object execute( String command ){ return null; }
	
	protected static PlugInManager controlManager( String unalcol_url, String url ){
		PlugInManager manager = new PlugInManager(url+"plugins/");
		manager.addRepository(unalcol_url+"plugins/");
		return manager;
	}
	
	protected static PlugInManager guiManager( String unalcol_url, String url ){
		PlugInManager manager = new PlugInManager(url+"plugins/js/");
		manager.addRepository(unalcol_url+"plugins/js/");
		return manager;
	}
	
	public JSView( String unalcol_url, String url ){ super( controlManager(unalcol_url, url), guiManager(unalcol_url, url) );	} 
	
	public String addTimer( Controller c, int delay ){
		StringBuilder sb = new StringBuilder();
		sb.append("addTimer('");
		sb.append(c.toString());
		sb.append("',");
		sb.append(delay);
		sb.append(')');
		return sb.toString();
	}
	
	public String delTimer( Controller c, int delay ){
		StringBuilder sb = new StringBuilder();
		sb.append("delTimer('");
		sb.append(c.toString());
		sb.append("',");
		sb.append(delay);
		sb.append(')');
		return sb.toString();
	}	

}
