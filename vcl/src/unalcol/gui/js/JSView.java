package unalcol.gui.js;

import unalcol.gui.Controller;
import unalcol.gui.plugin.PlugInsView;

public class JSView extends PlugInsView{
	protected String unalcol_url;

	public void execute( String command ){}
	
	public JSView( String unalcol_url, String url ){ 
		super(url+"plugins/");
		manager.addRepository(unalcol_url+"plugins/");
	} 
	
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
